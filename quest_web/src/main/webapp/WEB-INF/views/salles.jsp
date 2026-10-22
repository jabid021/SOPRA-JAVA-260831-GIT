<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/securityAdmin.jsp" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Gestion des salles</title>
</head>
<body>
	<content>
  	<table>
  		<tr>
  			<th>Id</th>
  			<th>Nom</th>
  			<th>Adresse</th>
  			<th>Actions</th>
  		</tr>
  		<c:if test="${salles.isEmpty()}">
  			<tr><td align="center" colspan="4">Aucune salle</td></tr>
  		</c:if>
  	
  		<c:forEach items="${salles}" var="salle">
  			<tr>
  				<td>${salle.id}</td>
  				<td>${salle.nom}</td>
  				<td>${salle.adresse.numero} ${salle.adresse.voie}, ${salle.adresse.cp} ${salle.adresse.ville}</td>
  				<td>
  					<a class="btn btn-warning" href="salle/${salle.id}">Modifier</a>
  					<a class="btn btn-danger" href="salle/${salle.id}/delete">Supprimer</a>
  				</td>
  			</tr>

        	
  		</c:forEach>
  	  	
  	</table>
  		<div class="message-form">Formulaire d'ajout / update</div>

		<c:choose>
  			<c:when test="${salle.id==null}">
  				<c:set var="chemin" value="salle"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="chemin" value="salle/${salle.id}"/>
  			</c:otherwise>
  		</c:choose>
  		
  	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="salle">
  	  <form:hidden path="id"/>
	  <form:hidden path="version" />
  	  <form:label path="nom">Nom</form:label>
  	  <form:input required="required" path="nom" type="text" placeholder="Saisir nom" />


  	  <h2>Adresse</h2>
  	  <form:label path="adresse.numero">Numero</form:label>
  	  <form:input required="required" type="text" path="adresse.numero" placeholder="Saisir le numero" />

  	   <form:label path="adresse.voie">Voie</form:label>
  	  <form:input required="required" type="text" path="adresse.voie" placeholder="Saisir la voie" />

  	   <form:label path="adresse.ville">Ville</form:label>
  	  <form:input required="required" type="text" path="adresse.ville" placeholder="Saisir la ville" />

  	   <form:label path="adresse.cp">CP</form:label>
  	  <form:input required="required" type="text" path="adresse.cp" placeholder="Saisir le cp" />

  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form:form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>
</body>
</html>