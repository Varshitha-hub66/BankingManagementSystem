package com.banking.controller;

import com.banking.model.Customer;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/transactions")
public class TransactionServlet extends HttpServlet {
    private final AccountService accountService = new AccountService();
    private final TransactionService transactionService = new TransactionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("customer/login.jsp");
            return;
        }
        try {
            long accountId = Long.parseLong(request.getParameter("accountId"));
            boolean owns = accountService.getCustomerAccounts(customer.getCustomerId())
                    .stream().anyMatch(a -> a.getAccountId() == accountId);
            if (!owns) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
            request.setAttribute("transactions", transactionService.getAccountTransactions(accountId));
            request.getRequestDispatcher("/customer/transactions.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
