<!DOCTYPE html>
<html><head><title>Admin Login</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="card form-card"><h2>Admin Login</h2>
<% if(request.getParameter("error") != null){ %><div class="error"><%=request.getParameter("error")%></div><% } %>
<form method="post" action="../admin-login"><input name="username" placeholder="Username" required><input type="password" name="password" placeholder="Password" required><button class="btn">Login</button></form>
<p><a href="../index.jsp">Home</a></p></div></body></html>
