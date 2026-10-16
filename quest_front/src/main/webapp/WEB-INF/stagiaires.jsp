<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<%@include file="/WEB-INF/securityAdmin.jsp" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
   
  <title>Gestion des Stagiaires</title>
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
			<th>Email</th>
			<th>Adresse</th>
  			<th>Filiere</th>
			<th>ACTION</th>

    		</tr>
  		<c:if test="${stagiaires.isEmpty()}">
  			<tr><td align="center" colspan="10">Aucun Stagiaire</td></tr>
  		</c:if>
		<c:forEach items="${stagiaires}" var="s">
  	   <tr>
  				<td>${s.id}</td>
  				<td>${s.login}</td>
  				<td>${s.password}</td>
  				<td>${s.nom}</td>
  				<td>${s.prenom}</td>
  				<td>${s.genre}</td>
  				<td>${s.email}</td>
  				<td>${s.adresse.fullAdresse}</td>
  				<td>${s.filiere.infos}</td>
  				<td>
  					<a class="btn btn-warning" href="stagiaire?id=${s.id}">Modifier</a>
  					<a class="btn btn-danger" href="stagiaire?id=${s.id}&delete">Supprimer</a>
  				</td>
  			</tr>
		</c:forEach>
  	</table>
  		<div class="message-form">Formulaire d'ajout / update</div>

  	<form action="stagiaire" method="post" class="form-clean">
  	  <input type="hidden" name="id" value="${stagiaire.id}">

  	  <h2>Personne</h2>

  	  <label for="nom">Nom</label>
  	  <input required="required" id="nom" type="text" name="nom" placeholder="Saisir nom" value="${stagiaire.nom}">

	  <label for="prenom">Prenom</label>
  	  <input required="required" id="prenom" type="text" name="prenom" placeholder="Saisir prenom" value="${stagiaire.prenom}">

	  <label for="login">Login</label>
  	  <input required="required" id="login" type="text" name="login" placeholder="Saisir login" value="${stagiaire.login}">

	  <label for="password">Password</label>
  	  <input required="required" id="password" type="password" name="password" placeholder="Saisir password" value="${stagiaire.password}">

  	  <label>Civilité</label>
  		<div class="choice-group">
  			<div class="choice-item">
  				<c:forEach items="${genres}" var="civ">
  					<c:choose>
  						<c:when test="${stagiaire.genre==civ}">
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


		
	  <label for="email">Email</label>
  	  <input required="required" id="email" type="email" name="email" placeholder="Saisir email" value="${stagiaire.email}">
  	  
  	  <h2>Adresse</h2>
  	  <label for="numero">Numero</label>
  	  <input required="required" id="numero" type="text" name="adresse.numero" placeholder="Saisir le numero" value="${stagiaire.adresse.numero}">

  	   <label for="voie">Voie</label>
  	  <input required="required" id="voie" type="text" name="adresse.voie" placeholder="Saisir la voie" value="${stagiaire.adresse.voie}">

  	   <label for="ville">Ville</label>
  	  <input required="required" id="ville" type="text" name="adresse.ville" placeholder="Saisir la ville" value="${stagiaire.adresse.ville}">

  	   <label for="cp">CP</label>
  	  <input required="required" id="cp" type="text" name="adresse.cp" placeholder="Saisir le cp" value="${stagiaire.adresse.cp}">
  	  
  	   <label for="filiere">Matiere</label>
                  <select id="filiere" required="required" name="filiere.id">
                    <option value="">Choisir une filiere</option>
                    <c:forEach items="${filieres}" var="f">
                    	<c:choose>
                    	<c:when test="${f.id==stagiaire.filiere.id}">
                    		<option selected value="${f.id}">${f.infos}</option>
                    	</c:when>
                    	<c:otherwise>
                    		<option value="${f.id}">${f.infos}</option>
                    	</c:otherwise>
                    	</c:choose>
                     	
                    </c:forEach>
                  </select>
  	  
  	  
  	  
  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="stagiaire" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>

</body>
</html>
