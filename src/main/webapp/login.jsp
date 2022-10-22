<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Login</title>
  <link rel = "stylesheet" href="css/formStyle.css">
</head>
<body>
<div class="center">
  <form action="LoginServlet" method="post">
    <div class="txt_field">
      <input type="text" name="login" required>
      <span></span>
      <label>Login</label>
    </div>
    <div class="txt_field">
      <input type="password" name="password" required>
      <span></span>
      <label>Password</label>
    </div>
    <input type="submit" value="Login">
    <div class="signup_link">
      Not a member? <a href="${pageContext.request.contextPath}/registration.jsp">Signup</a>
    </div>
  </form>
</div>
</body>
</html>

