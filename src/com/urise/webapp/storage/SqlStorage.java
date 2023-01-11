package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.*;
import com.urise.webapp.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final Logger LOG = Logger.getLogger(SqlStorage.class.getName());
    private final SqlHelper sqlHelper;

    public SqlStorage(SqlHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clear() {
        LOG.info("cleared");
        sqlHelper.execute("DELETE FROM resume", PreparedStatement::execute);
    }

    @Override
    public void save(Resume r) {
        LOG.info("save " + r);
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContacts(r, conn);
                    insertSections(r, conn);
                    return null;
                }
        );
    }

    @Override
    public void update(Resume r) {
        LOG.info("update " + r);
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            deleteContacts(r, conn);
            deleteSections(r, conn);
            insertContacts(r, conn);
            insertSections(r, conn);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        LOG.info("delete " + uuid);
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid =?", preparedStatement -> {
            preparedStatement.setString(1, uuid);
            if (preparedStatement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("get " + uuid);
        return sqlHelper.execute(""
                        + "SELECT * FROM resume r " +
                        "   LEFT JOIN contact c " +
                        "      ON r.uuid = c.resume_uuid " +
                        "  WHERE r.uuid =? ",
                preparedStatement -> {
                    preparedStatement.setString(1, uuid);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        addContactToResume(rs, r);
                    } while (rs.next());
                    return r;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();

            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name, uuid")) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addContactToResume(rs, r);
                }
            }
            try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM section")) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addSectionToResume(rs, r);
                }
            }
            return new ArrayList<>(resumes.values());
        });
    }

//            Map<String, Resume> map = new LinkedHashMap<>();
//            return sqlHelper.execute("" +
//                    "SELECT * FROM resume r " +
//                    "   LEFT JOIN contact c ON r.uuid = c.resume_uuid " +
//                    "   LEFT JOIN section s on r.uuid = s.resume_uuid" +
//                    "       ORDER BY full_name, uuid", preparedStatement -> {
//                ResultSet resultSet = preparedStatement.executeQuery();
//                while (resultSet.next()) {
//                    String uuid = resultSet.getString("uuid");
//                    Resume resume = map.get(uuid);
//                    if (resume == null) {
//                        resume = new Resume(uuid, resultSet.getString("full_name"));
//                        map.put(uuid, resume);
//                    }
//                    addContactToResume(resultSet, resume);
//                    addSectionToResume(resultSet, resume);
//                }
//                return new ArrayList<>(map.values());
//            });
//        });
//    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(resume) from resume", preparedStatement -> {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        });
    }

    private void deleteContacts(Resume r, Connection conn) {
        sqlHelper.execute("DELETE FROM contact WHERE resume_uuid=?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    private void deleteSections(Resume r, Connection conn) throws SQLException {
        sqlHelper.execute("DELETE FROM section WHERE resume_uuid=?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    public void insertContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (EnumMap.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, content) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> entry : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, entry.getKey().name());
                SectionType type = entry.getKey();
                switch (type) {
                    case OBJECTIVE, PERSONAL -> ps.setString(3, ((TextSection) entry.getValue()).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        List<String> list = ((ListSection) entry.getValue()).getItems();
                        ps.setString(3, String.join("\n", list));
                    }
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private static void addContactToResume(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            r.addContact(type, value);
        }
    }

    private void addSectionToResume(ResultSet rs, Resume r) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            switch (type) {
                case PERSONAL, OBJECTIVE -> {
                    r.addSection(SectionType.valueOf(String.valueOf(type)), new TextSection(content));
                }
                case ACHIEVEMENT, QUALIFICATIONS -> {
                    r.addSection(SectionType.valueOf(String.valueOf(type)), new ListSection(new ArrayList<>(List.of(content.split("\n")))));

//                    ListSection listSection = new ListSection();
//                    List<String> list = new ArrayList<>();
//                    StringBuilder stringBuilder = new StringBuilder();
//                    StringBuilder stringBuilder1 = new StringBuilder();
//                    //тут косяк разбить контент на 2 части разделителем n и 2 стринга добавить в лист стрингов
//
//                    int index = content.indexOf("\n");
//
//                    stringBuilder.append(content, 0, index);
//                    stringBuilder1.append(content, index + 1, content.length());
//
//                    list.add(stringBuilder.toString());
//                    list.add(stringBuilder1.toString());
//
//                    listSection.setItems(list);
//                    r.addSection(type, listSection);
                }
            }
        }
    }
}
