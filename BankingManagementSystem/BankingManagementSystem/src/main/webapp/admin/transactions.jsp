<%@ page import="java.sql.*,com.banking.util.DBConnection" %>
<% if(session.getAttribute("admin")==null){response.sendRedirect("admin-login.jsp");return;} %>
<!DOCTYPE html><html><head><title>Transactions</title><link rel="stylesheet" href="../css/style.css"></head><body><div class="container"><h1>Transactions</h1>
<table><tr><th>ID</th><th>Account ID</th><th>Type</th><th>Amount</th><th>Related Account</th><th>Date</th></tr>
<%
try(Connection con=DBConnection.getConnection(); PreparedStatement ps=con.prepareStatement("SELECT transaction_id,account_id,transaction_type,amount,related_account,transaction_date FROM transactions ORDER BY transaction_id DESC"); ResultSet rs=ps.executeQuery()){
while(rs.next()){%><tr><td><%=rs.getLong(1)%></td><td><%=rs.getLong(2)%></td><td><%=rs.getString(3)%></td><td>₹ <%=rs.getBigDecimal(4)%></td><td><%=rs.getString(5)==null?"-":rs.getString(5)%></td><td><%=rs.getTimestamp(6)%></td></tr><%}}catch(Exception e){%><tr><td colspan="6">Unable to load transactions.</td></tr><%}%>
</table><p><a href="dashboard.jsp">Back</a></p></div></body></html>
