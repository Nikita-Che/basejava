package com.urise.webapp.web.javaEEalishev;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletTestMy", value = "/ServletTestMy")
public class ServletTestMy extends HttpServlet {
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
        response.setContentType("text/html; charset=UTF-8");
        List<Resume> allSorted = storage.getAllSorted();
        request.setAttribute("users", allSorted);
        request.getRequestDispatcher("/WEB-INF/jsp/servletTestMy.jsp").forward(request, response);

//        HttpSession session = request.getSession();
//        Integer counter = (Integer) session.getAttribute("counter");
//        if (counter == null) {
//            session.setAttribute("counter", 1);
//            counter = 1;
//        } else {
//            session.setAttribute("counter", counter+1);
//        }
//        PrintWriter writer = response.getWriter();
//        writer.println(counter);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
