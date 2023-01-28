package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.ResumeTestData;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "ResumeServlet", value = "/resume")
public class ResumeServlet extends HttpServlet {
    Storage storage;

    @Override
    public void init() throws ServletException {
        super.init();
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
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
            case "addNewResume":
                storage.save(resume = ResumeTestData.createEmptyResume(UUID.randomUUID().toString(), "Please FILL Resume"));
                request.setAttribute("resume", resume);
                request.getRequestDispatcher("\\WEB-INF\\jsp\\edit.jsp").
                        forward(request, response);

            default:
                throw new IllegalStateException("Action" + action + "is  illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                        "view".equals(action) ? "\\WEB-INF\\jsp\\view.jsp" : "\\WEB-INF\\jsp\\edit.jsp").
                forward(request, response);
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
