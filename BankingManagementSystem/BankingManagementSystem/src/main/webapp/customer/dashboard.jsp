<%@ page import="com.banking.model.Customer,com.banking.service.AccountService,com.banking.model.Account,java.util.List" %>
<%
Customer customer=(Customer)session.getAttribute("customer");
if(customer==null){response.sendRedirect("login.jsp");return;}
List<Account> accounts=new AccountService().getCustomerAccounts(customer.getCustomerId());
%>
<!DOCTYPE html><html><head><title>Dashboard</title><link rel="stylesheet" href="../css/style.css"></head>
<body><div class="container">
<div class="topbar"><h1>Welcome, <%=customer.getFullName()%></h1><a href="../logout">Logout</a></div>
<% if(request.getParameter("success")!=null){ %><div class="success"><%=request.getParameter("success")%></div><% } %>
<div class="grid">
<div class="card"><h3>Your Profile</h3><p><b>Email:</b> <%=customer.getEmail()%></p><p><b>Phone:</b> <%=customer.getPhone()%></p></div>
<div class="card"><h3>Quick Actions</h3><a class="btn" href="../account/create-account.jsp">Create Account</a>
<a class="btn secondary" href="../transaction/deposit.jsp">Deposit</a>
<a class="btn secondary" href="../transaction/withdraw.jsp">Withdraw</a>
<a class="btn secondary" href="../transaction/transfer.jsp">Transfer</a></div>
</div>
<h2>Your Accounts</h2>
<table><tr><th>Account Number</th><th>Type</th><th>Balance</th><th>Status</th><th>History</th></tr>
<% for(Account a:accounts){ %><tr><td><%=a.getAccountNumber()%></td><td><%=a.getAccountType()%></td><td>₹ <%=a.getBalance()%></td><td><%=a.getStatus()%></td><td><a href="../transactions?accountId=<%=a.getAccountId()%>">View</a></td></tr><% } %>
</table>
</div></body></html>
