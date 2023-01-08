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

@WebServlet(name = "ResumeServlet", value = "/resume")
public class ResumeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = response.getWriter();
        Storage storage = Config.get().storage;
        List<Resume> allSorted = storage.getAllSorted();
        System.out.println(allSorted);
        for (Resume resume : allSorted) {
            String uuid = resume.getUuid();
            String fullname = resume.getFullName();
            printWriter.write("  <tr>\n" +
                    "    <th>uuid</th>\n" +
                    "    <th>Full name</th>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>" + uuid + "</td>\n" +
                    "    <td>"+ fullname +" </td>\n" +
                    "  </tr>\n");
        }

//        printWriter.println("<table>\n" +
//                "  <tr>\n" +
//                "    <th>uuid</th>\n" +
//                "    <th>Full name</th>\n" +
//                "  </tr>\n" +
//                "  <tr>\n" +
//                "    <td>234234234</td>\n" +
//                "    <td>Vasya</td>\n" +
//                "  </tr>\n" +
//                "</table>");
//        printWriter.write("<td>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
