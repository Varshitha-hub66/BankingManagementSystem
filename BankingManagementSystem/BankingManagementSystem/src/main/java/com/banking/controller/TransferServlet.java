package com.banking.controller;

import com.banking.service.AccountService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    private final AccountService service = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("customer") == null) {
            response.sendRedirect("customer/login.jsp");
            return;
        }
        try {
            service.transfer(request.getParameter("fromAccount"),
                    request.getParameter("toAccount"),
                    new BigDecimal(request.getParameter("amount")));
            response.sendRedirect("transaction/transfer.jsp?success=Transfer successful");
        } catch (Exception e) {
            response.sendRedirect("transaction/transfer.jsp?error=" +
                    java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8));
        }
    }
}
