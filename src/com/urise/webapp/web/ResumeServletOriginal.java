package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResumeServlet", value = "/resume")
public class ResumeServletOriginal extends HttpServlet {
    Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Config.get().storage;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes1", storage.getAllSorted());
            request.getRequestDispatcher("\\WEB-INF\\jsp\\list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                break;
            default:
                throw new IllegalStateException("Action" + action + "is  illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                        "view".equals(action) ? "\\WEB-INF\\jsp\\view.jsp" : "\\WEB-INF\\jsp\\edit.jsp").
                forward(request, response);


//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        Writer writer = response.getWriter();
//        writer.write(
//                "<html>\n" +
//                        "<head>\n" +
//                        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
//                        "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
//                        "    <title>Список всех резюме</title>\n" +
//                        "</head>\n" +
//                        "<body>\n" +
//                        "<section>\n" +
//                        "<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\">\n" +
//                        "    <tr>\n" +
//                        "        <th>Имя</th>\n" +
//                        "        <th>Email</th>\n" +
//                        "    </tr>\n");
//        for (Resume resume : storage.getAllSorted()) {
//            writer.write(
//                    "<tr>\n" +
//                            "     <td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>\n" +
//                            "     <td>" + resume.getContact(EMAIL) + "</td>\n" +
//                            "</tr>\n");
//        }
//        writer.write("</table>\n" +
//                "</section>\n" +
//                "</body>\n" +
//                "</html>\n");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType contactType : ContactType.values()) {
            String value = request.getParameter(contactType.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(contactType, value);
            } else {
                r.getContacts().remove(contactType);
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }
}
