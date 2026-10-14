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
  	<table>
  		<tr>
  			<th>Id</th>
  			<th>libelle</th>
        	<th>Actions</th>
  		</tr>
  		<c:if test="${matieres.isEmpty()}">
  			<tr><td align="center" colspan="3">Aucune matiere</td></tr>
  		</c:if>
  		
		<c:forEach items="${matieres}" var="matiere">
			<tr>
  				<td>${matiere.id}</td>
  				<td>${matiere.libelle}</td>
  				<td>
  					<a class="btn btn-warning" href="">Modifier</a>
  					<a class="btn btn-danger" href="">Supprimer</a>
  				</td>
  			</tr>
			
		</c:forEach>
  
  	</table>
  		<div class="message-form">Formulaire d'ajout / update</div>

  	<form action="" method="post" class="form-clean">
  	  <input type="hidden" name="id" value="">

  	  <label for="libelle">libelle</label>
  	  <input required="required" id="libelle" type="text" name="libelle" placeholder="Saisir libelle" value="">


  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form>

  	<br><br>
  	<a class="btn btn-info" href="menuAdmin.html">Retour</a>
  </content>

</body>
</html>
