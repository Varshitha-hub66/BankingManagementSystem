<!DOCTYPE html>
<html><head><title>Register</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="card form-card"><h2>Create Customer Account</h2>
<% if(request.getParameter("error") != null){ %><div class="error"><%=request.getParameter("error")%></div><% } %>
<form method="post" action="../register">
<input name="fullName" placeholder="Full name" required>
<input type="email" name="email" placeholder="Email" required>
<input name="phone" placeholder="Phone" required>
<input name="address" placeholder="Address">
<input type="password" name="password" placeholder="Password (min 6 characters)" required>
<button class="btn" type="submit">Register</button>
</form><p><a href="login.jsp">Back to login</a></p></div></body></html>
