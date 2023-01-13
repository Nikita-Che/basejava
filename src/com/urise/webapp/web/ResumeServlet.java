package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "MyResumeServlet", value = "/myresume")
public class ResumeServlet extends HttpServlet {
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

        PrintWriter printWriter = response.getWriter();

        List<Resume> allSorted = storage.getAllSorted();
        printWriter.write("<h1>Resume Table</h1>");
        printWriter.write("<img src=\"https://cdn.freecodecamp.org/curriculum/cat-photo-app/relaxing-cat.jpg\" alt=\"A cute orange cat lying on its back.\">");
        for (Resume resume : allSorted) {
            printWriter.write("<html> " +
                    "<table>\n" +
                    "<tr>\n" +
                    "   <td>" + "<b>" + resume.getFullName() + "</b>" + "</td>" + "\n" +
                    " <table border=11>" +
                    "   <td>" + "<small>" + resume.getUuid() + "<small/>" + "</td>\n" + "     " +
                    " </tr>\n" +
                    "   </table>" +
                    "<hr/>" +
                    "</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
