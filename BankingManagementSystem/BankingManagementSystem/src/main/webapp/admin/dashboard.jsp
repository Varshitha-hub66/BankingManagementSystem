<%
if(session.getAttribute("admin")==null){response.sendRedirect("admin-login.jsp");return;}
%>
<!DOCTYPE html><html><head><title>Admin Dashboard</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="container"><div class="topbar"><h1>Admin Dashboard</h1><a href="../index.jsp">Home</a></div>
<div class="grid">
<div class="card"><h3>Customers</h3><p>Customer management module.</p><a class="btn" href="customers.jsp">View Customers</a></div>
<div class="card"><h3>Accounts</h3><p>Account overview module.</p><a class="btn" href="accounts.jsp">View Accounts</a></div>
<div class="card"><h3>Transactions</h3><p>Transaction overview module.</p><a class="btn" href="transactions.jsp">View Transactions</a></div>
</div></div></body></html>
