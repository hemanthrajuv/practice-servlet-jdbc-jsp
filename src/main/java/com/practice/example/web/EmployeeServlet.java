package com.practice.example.web;

import com.practice.example.dao.EmployeeDAO;
import com.practice.example.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private EmployeeDAO employeeDAO;

    public void init(){
        employeeDAO = new EmployeeDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertEmployee(request, response);
                    break;
                case "/delete":
                    deleteEmployee(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateEmployee(request, response);
                    break;
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getServletPath();
//
//        if (action == "/list"){
//            try {
//                listEmployee(req,resp);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getServletPath();
//
//        try {
//            if (action == "new"){
//                showNewForm(req,resp);
//            }
//            if (action == "insert"){
//                insertEmloyee();
//            }
//        }
//    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<Employee> employeeList = employeeDAO.selectAllEmployees();
        request.setAttribute("employeeList", employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        RequestDispatcher dispatcher= request.getRequestDispatcher("employee-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        Employee oldEmployee = employeeDAO.selectEmployee(id);
        RequestDispatcher dispatcher= request.getRequestDispatcher("employee-form.jsp");
        request.setAttribute("employee", oldEmployee);
        dispatcher.forward(request, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        Employee newEmployee = new Employee(name, role, email);
        employeeDAO.insertEmployee(newEmployee);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        Employee editEmployee = new Employee(id, name, role, email);
        employeeDAO.updateEmployee(editEmployee);
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDAO.deleteEmployee(id);
        response.sendRedirect("list");
    }

}
