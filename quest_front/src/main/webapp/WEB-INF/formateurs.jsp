<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 


<%@include file="/WEB-INF/securityAdmin.jsp" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gestion des Formateurs</title>
</head>
<body>

  <content>
  	<table>
  		<tr>
  			<th>Id</th>
  			<th>Login</th>
  			<th>Password</th>
			<th>Nom</th>
			<th>Prenom</th>
			<th>Civilité</th>
  			<th>Admin</th>
			<th>ACTION</th>

    		</tr>
  		<c:if test="${formateurs.isEmpty()}">
  			<tr><td align="center" colspan="8">Aucun Formateur</td></tr>
  		</c:if>
		<c:forEach items="${formateurs}" var="f">
  	   <tr>
  				<td>${f.id}</td>
  				<td>${f.login}</td>
  				<td>${f.password}</td>
  				<td>${f.nom}</td>
  				<td>${f.prenom}</td>
  				<td>${f.genre}</td>
  				<td>${f.admin}</td>
  				<td>
  					<a class="btn btn-warning" href="formateur?id=${f.id}">Modifier</a>
  					<a class="btn btn-danger" href="formateur?id=${f.id}&delete">Supprimer</a>
  				</td>
  			</tr>
		</c:forEach>
  	</table>
  		<div class="message-form">Formulaire d'ajout / update</div>

  	<form action="formateur" method="post" class="form-clean">
  	  <input type="hidden" name="id" value="${formateur.id}">

  	  <h2>Personne</h2>

  	  <label for="nom">Nom</label>
  	  <input required="required" id="nom" type="text" name="nom" placeholder="Saisir nom" value="${formateur.nom}">

	  <label for="prenom">Prenom</label>
  	  <input required="required" id="prenom" type="text" name="prenom" placeholder="Saisir prenom" value="${formateur.prenom}">

	  <label for="login">Login</label>
  	  <input required="required" id="login" type="text" name="login" placeholder="Saisir login" value="${formateur.login}">

	  <label for="password">Password</label>
  	  <input required="required" id="password" type="password" name="password" placeholder="Saisir password" value="${formateur.password}">

  	  <label>Civilité</label>
  		<div class="choice-group">
  			<div class="choice-item">
  				<c:forEach items="${genres}" var="civ">
  					<c:choose>
  						<c:when test="${formateur.genre==civ}">
  							<input checked required="required" type="radio" id="${civ}" name="civilite" value="${civ}">
	  						<label for="${civ}">${civ}</label>
  						</c:when>
  						<c:otherwise>
	  						<input required="required" type="radio" id="${civ}" name="civilite" value="${civ}">
	  						<label for="${civ}">${civ}</label>
  						</c:otherwise>
  					</c:choose>
  				</c:forEach>
  				
               
  			</div>
  		</div>

	<label>Admin</label>
  		<div class="choice-group">
  				<div class="choice-item">
  					<input <c:if test="${formateur.admin==true}"> checked </c:if>type="checkbox" id="admin" name="admin"><label for="admin">Yes</label>
      			</div>
  		</div>



  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="formateur" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>

</body>
</html>
