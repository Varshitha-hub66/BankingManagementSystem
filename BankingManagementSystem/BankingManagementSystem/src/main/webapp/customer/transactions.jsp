<%@ page import="java.util.List,com.banking.model.Transaction" %>
<% if(session.getAttribute("customer")==null){response.sendRedirect("login.jsp");return;} %>
<!DOCTYPE html><html><head><title>Transactions</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="container"><h1>Transaction History</h1>
<table><tr><th>ID</th><th>Type</th><th>Amount</th><th>Related Account</th><th>Description</th><th>Date</th></tr>
<% for(Transaction t:(List<Transaction>)request.getAttribute("transactions")){ %>
<tr><td><%=t.getTransactionId()%></td><td><%=t.getTransactionType()%></td><td>₹ <%=t.getAmount()%></td><td><%=t.getRelatedAccount()==null?"-":t.getRelatedAccount()%></td><td><%=t.getDescription()%></td><td><%=t.getTransactionDate()%></td></tr>
<% } %></table><p><a href="dashboard.jsp">Back to dashboard</a></p></div></body></html>
