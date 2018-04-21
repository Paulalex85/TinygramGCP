<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.datastore.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>Connect user</title>
</head>
<body>
	<h1>Connexion</h1>
		<form method="post">
			<p>
				<label>Pseudo : <input type="text" name="pseudo" /></label>
			</p>
			<p>
				<label>Mot de passe : <input type="text" name="mdp" /></label>
			</p>
			<p>
				<input type="submit" />
			</p>
		</form>
</body>
</html>