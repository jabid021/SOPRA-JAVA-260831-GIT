<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html>

<head>
	<meta charset="UTF-8">
	<title>Accueil</title>
</head>

<body>

	<content>

		<form method="POST" action="home" >
			<tr>
				<td>Login</td>
				<td><input name="login" type="text" placeholder="Saisir votre login"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input name="password" type="password"   placeholder="Saisir votre password"></td>
			</tr>
			<c:if test="${param.error!=null}"> 
				<span style="color:red">Identifiants invalides</span>
			</c:if>
			<input type="submit" value="Se Connecter" class="btn btn-success">
		</form>

	</content>

</body>

</html>