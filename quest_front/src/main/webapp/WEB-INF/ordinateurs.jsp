<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Gestion des ordinateurs</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css">
	
</head>

<body>

	<content>
		<table>
			<tr>
				<th>Numero</th>
				<th>Marque</th>
				<th>Ram</th>
				<th>Utilisateur</th>
				<th>Actions</th>
			</tr>
			 <c:if test="${ordinateurs.isEmpty()}">
  				<tr><td align="center" colspan="5">Aucun ordinateur</td></tr>
  			</c:if>
  	
  			<c:forEach items="${ordinateurs}" var="o">
				<tr>
					<td>${o.numero}</td>
					<td>${o.marque}</td>
					<td>${o.ram}</td>
					 <c:choose>
		        		<c:when test="${o.utilisateur==null}">
			        		<td>Pas d'utilisateur</td>
			        	</c:when>
			        	<c:otherwise>
			        		<td>${o.utilisateur.infos}</td>
			        	</c:otherwise>
		       		 </c:choose>
					<td>
						<a class="btn btn-warning" href="ordinateur?id=${o.numero}">Modifier</a>
						<a class="btn btn-danger"  href="ordinateur?id=${o.numero}&delete">Supprimer</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<div class="message-form">Formulaire d'ajout / update</div>

		<form action="ordinateur" method="post" class="form-clean">
			<input type="hidden" name="numero" value="${ordinateur.numero}">

			<label for="marque">Marque</label>
			<input required="required" id="marque" type="text" name="marque" placeholder="Saisir la marque" value="${ordinateur.marque}">

			<label for="ram">Ram</label>
			<input required="required" id="ram" type="number"  min="0" name="ram" placeholder="Saisir la ram" value="${ordinateur.ram}">



			<label for="utilisateur">Utilisateur</label>
              <select id="utilisateur" name="utilisateur.id">
                <option value="">Pas d'utilisateur</option>
                 <c:forEach items="${utilisateurs}" var="u">
                     <option <c:if test="${u.id==ordinateur.utilisateur.id}">selected</c:if> value="${u.id}">${u.infos}</option>
                 </c:forEach>
              </select>


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
