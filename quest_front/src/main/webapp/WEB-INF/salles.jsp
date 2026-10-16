<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


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
  					<a class="btn btn-warning" href="salle?id=${salle.id}">Modifier</a>
  					<a class="btn btn-danger" href="salle?id=${salle.id}&delete">Supprimer</a>
  				</td>
  			</tr>

        	
  		</c:forEach>
  	  	
  	</table>
  		<div class="message-form">Formulaire d'ajout / update</div>

  	<form action="salle" method="post" class="form-clean">
  	  <input type="hidden" name="id" value="${salle.id}">
	  <input type="hidden" name="version" value="${salle.version}">
  	  <label for="nom">Nom</label>
  	  <input required="required" id="nom" type="text" name="nom" placeholder="Saisir nom" value="${salle.nom}">


  	  <h2>Adresse</h2>
  	  <label for="numero">Numero</label>
  	  <input required="required" id="numero" type="text" name="adresse.numero" placeholder="Saisir le numero" value="${salle.adresse.numero}">

  	   <label for="voie">Voie</label>
  	  <input required="required" id="voie" type="text" name="adresse.voie" placeholder="Saisir la voie" value="${salle.adresse.voie}">

  	   <label for="ville">Ville</label>
  	  <input required="required" id="ville" type="text" name="adresse.ville" placeholder="Saisir la ville" value="${salle.adresse.ville}">

  	   <label for="cp">CP</label>
  	  <input required="required" id="cp" type="text" name="adresse.cp" placeholder="Saisir le cp" value="${salle.adresse.cp}">

  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>
</body>
</html>