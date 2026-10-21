<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 


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
  			<c:when test="${produit==null}">
  				<c:set var="chemin" value="produit"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="chemin" value="produit/${produit.id}"/>
  			</c:otherwise>
  		</c:choose>
  	<form action="${chemin}" method="post" class="form-clean">
  	  <input type="hidden" name="id" value="${produit.id}">

  	  <label for="libelle">Libelle</label>
  	  <input required="required" id="libelle" type="text" name="libelle" placeholder="Saisir libelle" value="${produit.libelle}">

	  <label for="prix">Prix</label>
  	  <input required="required" id="prix" type="number" step="0.01" name="prix" placeholder="Saisir prix" value="${produit.prix}">

 	<select id="fournisseur" required="required" name="fournisseur.id">
          <option value="">Choisir un fournisseur</option>
          <c:forEach items="${fournisseurs}" var="f">
               <option <c:if test="${f.id==produit.fournisseur.id}">selected</c:if> value="${f.id}">${f.infos}</option>
          </c:forEach>
      </select>
  
  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="produit" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>

</body>
</html>
