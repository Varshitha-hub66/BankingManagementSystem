<!DOCTYPE html>
<html><head><title>Transfer</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="card form-card"><h2>Transfer Money</h2>
<% if(request.getParameter("error") != null){ %><div class="error"><%=request.getParameter("error")%></div><% } %>
<% if(request.getParameter("success") != null){ %><div class="success"><%=request.getParameter("success")%></div><% } %>
<form method="post" action="../transfer"><input name="fromAccount" placeholder="From account" required><input name="toAccount" placeholder="To account" required><input type="number" step="0.01" min="0.01" name="amount" placeholder="Amount" required><button class="btn">Transfer</button></form>
<p><a href="../customer/dashboard.jsp">Dashboard</a></p></div></body></html>
