<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>

<html>

<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css">

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