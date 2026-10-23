<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

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
  					<a class="btn btn-warning" href="formateur/${f.id}">Modifier</a>
  					<a class="btn btn-danger" href="formateur/${f.id}/delete">Supprimer</a>
  				</td>
  			</tr>
		</c:forEach>
  	</table>
  		<div class="message-form">Formulaire d'ajout / update</div>


		<c:choose>
  			<c:when test="${formateur.id==null}">
  				<c:set var="chemin" value="formateur"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="chemin" value="formateur/${formateur.id}"/>
  			</c:otherwise>
  		</c:choose>
  	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="formateur">
  	  <form:hidden path="id"/>

  	  <h2>Personne</h2>

  	  <form:label path="nom">Nom</form:label>
  	  <form:input required="required" path="nom" type="text" placeholder="Saisir nom" />

	  <form:label path="prenom">Prenom</form:label>
  	  <form:input required="required" path="prenom" type="text"  placeholder="Saisir prenom" />

	  <form:label path="login">Login</form:label>
  	  <form:input required="required" path="login" type="text" placeholder="Saisir login" />

	  <form:label path="password">Password</form:label>
  	  <form:input required="required" path="password" type="password" placeholder="Saisir password" />

  	  <label>Civilité</label>
  		<div class="choice-group">
  			<div class="choice-item">
  				<form:radiobuttons path="genre" items="${genres}"/>              
  			</div>
  		</div>

	<label>Admin</label>
  		<div class="choice-group">
  				<div class="choice-item">
  					<form:checkbox path="admin" id="admin"/><form:label path="admin">Admin</form:label>
      			</div>
  		</div>



  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="formateur" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form:form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>

</body>
</html>
