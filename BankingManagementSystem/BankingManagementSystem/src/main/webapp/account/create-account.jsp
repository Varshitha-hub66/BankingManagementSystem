<!DOCTYPE html>
<html><head><title>Create Account</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="card form-card"><h2>Create Bank Account</h2>
<% if(request.getParameter("error") != null){ %><div class="error"><%=request.getParameter("error")%></div><% } %>
<form method="post" action="../create-account">
<select name="accountType"><option value="SAVINGS">Savings</option><option value="CURRENT">Current</option></select>
<input type="number" step="0.01" min="0" name="openingBalance" placeholder="Opening balance" required>
<button class="btn" type="submit">Create Account</button>
</form><p><a href="../customer/dashboard.jsp">Back to dashboard</a></p></div></body></html>
