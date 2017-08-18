
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
${inst}
<form action="formhandler" method="post">
    First Name: <input type="text" name="fName"><br>
    Last Name: <input type="text" name="lName"><br>
    Email: <input type="text" name="email"><br>
    Phone: <input type="text" name="phone"><br>
    Password: <input type="password" name="password"><br>
    Re-Enter Password: <input type = "password" name= "password2" >

    <input type="submit" name="submit" value="Submit">
</form>
</body>
</html>
