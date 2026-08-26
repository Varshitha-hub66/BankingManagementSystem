package com.banking.controller;

import com.banking.util.DBConnection;
import com.banking.util.PasswordUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/admin-login")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String sql = "SELECT admin_id, username FROM admins WHERE username=? AND password_hash=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, request.getParameter("username"));
            ps.setString(2, PasswordUtil.sha256(request.getParameter("password")));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    request.getSession().setAttribute("admin", rs.getString("username"));
                    response.sendRedirect("admin/dashboard.jsp");
                } else {
                    response.sendRedirect("admin/admin-login.jsp?error=Invalid credentials");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("admin/admin-login.jsp?error=Database error");
        }
    }
}
