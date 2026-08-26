<!DOCTYPE html>
<html><head><title>Deposit</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="card form-card"><h2>Deposit Money</h2>
<% if(request.getParameter("error") != null){ %><div class="error"><%=request.getParameter("error")%></div><% } %>
<% if(request.getParameter("success") != null){ %><div class="success"><%=request.getParameter("success")%></div><% } %>
<form method="post" action="../deposit"><input name="accountNumber" placeholder="Account number" required><input type="number" step="0.01" min="0.01" name="amount" placeholder="Amount" required><button class="btn">Deposit</button></form>
<p><a href="../customer/dashboard.jsp">Dashboard</a></p></div></body></html>
