<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>



<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="style.css">
  <title>Gestion des matières</title>
</head>
<body>

  <content>
  <form class="form-clean"><input type="text" id="inputFilter" placeholder="Filtrer tableau"></form>
  	<table>
  		<tr>
  			<th>Id</th>
  			<th>libelle</th>
        	<th>Actions</th>
  		</tr>
  		<c:if test="${matieres.isEmpty()}">
  			<tr><td align="center" colspan="3">Aucune matiere</td></tr>
  		</c:if>
		<c:forEach items="${matieres}" var="m">
			<tr>
  				<td>${m.id}</td>
  				<td>${m.libelle}</td>
  				<td>
  					<a class="btn btn-warning" href="matiere?id=${m.id}">Modifier</a>
  					<a class="btn btn-danger" href="matiere?id=${m.id}&delete">Supprimer</a>
  				</td>
  			</tr>
			
		</c:forEach>
  
  	</table>
  		<c:choose>
  			<c:when test="${matiere==null}">
  				<c:set var="operation" value="ajout"/>
  				<c:set var="submitText" value="Ajouter"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="operation" value="update"/>
  				<c:set var="submitText" value="Modifier"/>
  			</c:otherwise>
  		</c:choose>
  	
  		<div class="message-form">Formulaire d'${operation}</div>

  	<form action="matiere" method="post" class="form-clean">
  	  <input type="hidden" name="id" value="${matiere.id}">

  	  <label for="libelle">libelle</label>
  	  <input required="required" id="libelle" type="text" name="libelle" placeholder="Saisir libelle" value="${matiere.libelle}">


  	  <div class="form-actions">
  	    <input type="submit" value="${submitText}" class="btn btn-success">
  	    <a href="matiere" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form>

  	<br><br>
  	<a class="btn btn-info" href="menuAdmin.html">Retour</a>
  </content>

</body>
</html>
