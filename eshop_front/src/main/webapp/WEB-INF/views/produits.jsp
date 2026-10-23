<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gestion des Produits</title>
</head>
<body>

  <content>
  	<table>
  		<tr>
  			<th>Id</th>
  			<th>Libelle</th>
  			<th>Prix</th>
			<th>Fournisseur</th>
			<th>ACTION</th>

    		</tr>
  		<c:if test="${produits.isEmpty()}">
  			<tr><td align="center" colspan="5">Aucun Produit</td></tr>
  		</c:if>
		<c:forEach items="${produits}" var="p">
  	   <tr>
  				<td>${p.id}</td>
  				<td>${p.libelle}</td>
  				<td>${p.prix}€</td>
  				<td>${p.fournisseur.infos}</td>
  				<td>
  					<a class="btn btn-warning" href="produit/${p.id}">Modifier</a>
  					<a class="btn btn-danger" href="produit/${p.id}/delete">Supprimer</a>
  				</td>
  			</tr>
		</c:forEach>
  	</table>
  		<div class="message-form">Formulaire d'ajout / update</div>

 		<c:choose>
  			<c:when test="${produit.id==null}">
  				<c:set var="chemin" value="produit"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="chemin" value="produit/${produit.id}"/>
  			</c:otherwise>
  		</c:choose>
  		
  	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="produit">
  	  <form:hidden path="id"/>

  	  <form:label path="libelle">Libelle</form:label>
  	  <form:input required="required" path="libelle" type="text" placeholder="Saisir libelle" />
	  <form:errors style="color:red" path="libelle"/>
	
	
	  <form:label path="prix">Prix</form:label>
  	  <form:input required="required" path="prix" type="number" step="0.01" placeholder="Saisir prix" />
	  <form:errors  path="prix"><span style="color:red">error custom</span></form:errors>
	  
 	<form:select required="required" path="fournisseur.id">
          <form:option value="">Choisir un fournisseur</form:option>
          <form:options items="${fournisseurs}" itemValue="id" itemLabel="infos" />
      </form:select>
  
  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="produit" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form:form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>

</body>
</html>
