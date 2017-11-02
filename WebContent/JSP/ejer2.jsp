<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p> <%= (String)session.getAttribute("error") %></p>

	<form method="post" action="?action=toLog">
		
		<p style="display: inline;">Usuario   :</p>
		<input style="display: inline; margin-left: 2px" type="text" name="user">
		</br>
		<p style="display: inline;">password :</p>
		<input style="display: inline;" type="password" name="pass">
		</br>		
		<input type="submit" value="logIn">
	</form>
</body>
</html>