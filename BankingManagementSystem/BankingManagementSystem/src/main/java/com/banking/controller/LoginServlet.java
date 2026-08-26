package com.banking.controller;

import com.banking.model.Customer;
import com.banking.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final CustomerService service = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Customer customer = service.login(request.getParameter("email"), request.getParameter("password"));
            if (customer == null) {
                response.sendRedirect("customer/login.jsp?error=Invalid email or password");
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("customer", customer);
            response.sendRedirect("customer/dashboard.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
