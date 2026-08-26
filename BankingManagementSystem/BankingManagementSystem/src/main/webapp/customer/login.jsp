<!DOCTYPE html>
<html><head><title>Customer Login</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="card form-card"><h2>Customer Login</h2>
<% if(request.getParameter("error") != null){ %><div class="error"><%=request.getParameter("error")%></div><% } %>
<% if(request.getParameter("success") != null){ %><div class="success"><%=request.getParameter("success")%></div><% } %>
<form method="post" action="../login">
<input type="email" name="email" placeholder="Email" required>
<input type="password" name="password" placeholder="Password" required>
<button class="btn" type="submit">Login</button>
</form>
<p>New customer? <a href="register.jsp">Create account</a></p></div></body></html>
