<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Stagiaire</title>
</head>
<body>

<h1>Welcome ${connected.login}</h1>

<h1>SITE EN COURS DE CONSTRUCTION (Espace Stagiaire)</h1>


<h2>Modifier Login / Password</h2>

<form action="stagiaire/modifLogin" method="post" class="form-clean">
  	  <input type="hidden" name="id" value="${connected.id}">
	  <label for="login">Login</label>
  	  <input required="required" id="login" type="text" name="login" placeholder="Saisir nouveau login">

	  <label for="password">Password</label>
  	  <input required="required" id="password" type="password" name="password" placeholder="Saisir nouveau password" >
	  <input type="submit" value="Modifier" class="btn btn-success">
</form>

</body>
</html>