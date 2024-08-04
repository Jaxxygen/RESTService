package application.servlets;

import application.data.dao.StudentDAO;
import application.data.dto.StudentsDTO;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebServlet("/Students")
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

        @Override
    public void init() {
        this.studentDAO = new StudentDAO();
   }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            resp.setContentType("text/hml");
            resp.getWriter().print("<h2>" + studentDAO.get(id) + "</h2>");
        } catch (IOException ex) {
            log.error("Writer I/O error: " + ex);
        } catch (NumberFormatException ex) {
            log.error("Couldn't parse request parameter to integer: " + ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StudentsDTO student = new StudentsDTO();
        String name = req.getParameter("name");
        student.setName(name);
        studentDAO.create(student);

        try {
            resp.getWriter().print("<h2>" + resp.getStatus() + "</h2>");
        } catch (IOException ex) {
            log.error("Writer I/O error: " + ex);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StudentsDTO student = new StudentsDTO();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");

        student.setId(id);
        student.setName(name);
        studentDAO.update(student);

        try {
            resp.getWriter().print("<h2>" + resp.getStatus() + "</h2>");
        } catch (IOException ex) {
            log.error("Writer I/O error: " + ex);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            resp.setContentType("text/hml");
            studentDAO.deleteById(id);
            resp.getWriter().print("<h2>" + resp.getStatus() + "</h2>");
        } catch (IOException ex) {
            log.error("Writer I/O error: " + ex);
        } catch (NumberFormatException ex) {
            log.error("Couldn't parse request parameter to integer: " + ex);
        }
    }
}
