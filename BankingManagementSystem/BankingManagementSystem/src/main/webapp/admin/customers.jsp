<%@ page import="java.sql.*,com.banking.util.DBConnection" %>
<% if(session.getAttribute("admin")==null){response.sendRedirect("admin-login.jsp");return;} %>
<!DOCTYPE html><html><head><title>Customers</title><link rel="stylesheet" href="../css/style.css"></head><body><div class="container"><h1>Customers</h1>
<table><tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Created</th></tr>
<%
try(Connection con=DBConnection.getConnection(); PreparedStatement ps=con.prepareStatement("SELECT customer_id,full_name,email,phone,created_at FROM customers ORDER BY customer_id DESC"); ResultSet rs=ps.executeQuery()){
while(rs.next()){%><tr><td><%=rs.getInt(1)%></td><td><%=rs.getString(2)%></td><td><%=rs.getString(3)%></td><td><%=rs.getString(4)%></td><td><%=rs.getTimestamp(5)%></td></tr><%}}catch(Exception e){%><tr><td colspan="5">Unable to load customers.</td></tr><%}%>
</table><p><a href="dashboard.jsp">Back</a></p></div></body></html>
