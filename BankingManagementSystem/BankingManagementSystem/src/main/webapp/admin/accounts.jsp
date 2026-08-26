<%@ page import="java.sql.*,com.banking.util.DBConnection" %>
<% if(session.getAttribute("admin")==null){response.sendRedirect("admin-login.jsp");return;} %>
<!DOCTYPE html><html><head><title>Accounts</title><link rel="stylesheet" href="../css/style.css"></head><body><div class="container"><h1>Accounts</h1>
<table><tr><th>ID</th><th>Customer</th><th>Account</th><th>Type</th><th>Balance</th><th>Status</th></tr>
<%
String sql="SELECT a.account_id,a.customer_id,a.account_number,a.account_type,a.balance,a.status FROM accounts a ORDER BY a.account_id DESC";
try(Connection con=DBConnection.getConnection(); PreparedStatement ps=con.prepareStatement(sql); ResultSet rs=ps.executeQuery()){
while(rs.next()){%><tr><td><%=rs.getLong(1)%></td><td><%=rs.getInt(2)%></td><td><%=rs.getString(3)%></td><td><%=rs.getString(4)%></td><td>₹ <%=rs.getBigDecimal(5)%></td><td><%=rs.getString(6)%></td></tr><%}}catch(Exception e){%><tr><td colspan="6">Unable to load accounts.</td></tr><%}%>
</table><p><a href="dashboard.jsp">Back</a></p></div></body></html>
