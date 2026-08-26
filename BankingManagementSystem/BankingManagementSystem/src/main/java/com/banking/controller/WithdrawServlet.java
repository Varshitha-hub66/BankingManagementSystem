package com.banking.controller;

import com.banking.service.AccountService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/withdraw")
public class WithdrawServlet extends HttpServlet {
    private final AccountService service = new AccountService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("customer") == null) {
            response.sendRedirect("customer/login.jsp");
            return;
        }
        try {
            service.withdraw(request.getParameter("accountNumber"),
                    new BigDecimal(request.getParameter("amount")));
            response.sendRedirect("transaction/withdraw.jsp?success=Withdrawal successful");
        } catch (Exception e) {
            response.sendRedirect("transaction/withdraw.jsp?error=" +
                    java.net.URLEncoder.encode(e.getMessage(), java.nio.charset.StandardCharsets.UTF_8));
        }
    }
}
