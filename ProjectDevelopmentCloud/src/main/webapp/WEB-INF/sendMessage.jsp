<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="com.cloud.User" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Send Message</title>
</head>
<body>
	<h1>Envoie Message</h1>
	<h3>Bonjour <%
	HttpSession s = request.getSession(true);
	User u = (User) s.getAttribute("user");%>
	<%=u.getPseudo()%>
	</h3>
		<form method="post">
			<p>
				<label>Message : <textarea type="text" name="msg"> </textarea></label>
			</p>
			<p>
				<input type="submit" />
			</p>
		</form>
</body>
</html>