package com.banking.controller;

import com.banking.model.Customer;
import com.banking.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/create-account")
public class AccountServlet extends HttpServlet {
    private final AccountService service = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("customer/login.jsp");
            return;
        }
        try {
            service.createAccount(customer.getCustomerId(),
                    request.getParameter("accountType"),
                    new BigDecimal(request.getParameter("openingBalance")));
            response.sendRedirect("customer/dashboard.jsp?success=Account created successfully");
        } catch (Exception e) {
            response.sendRedirect("account/create-account.jsp?error=" +
                    java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8));
        }
    }
}
