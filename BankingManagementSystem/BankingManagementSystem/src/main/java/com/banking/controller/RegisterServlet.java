package com.banking.controller;

import com.banking.service.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final CustomerService service = new CustomerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            boolean created = service.register(
                    request.getParameter("fullName"),
                    request.getParameter("email"),
                    request.getParameter("phone"),
                    request.getParameter("address"),
                    request.getParameter("password")
            );
            if (created) response.sendRedirect("customer/login.jsp?success=Registration successful. Please login.");
            else response.sendRedirect("customer/register.jsp?error=Registration failed");
        } catch (Exception e) {
            response.sendRedirect("customer/register.jsp?error=" + java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8));
        }
    }
}
