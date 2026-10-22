<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

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
  					<a class="btn btn-warning" href="stagiaire/${s.id}">Modifier</a>
  					<a class="btn btn-danger" href="stagiaire/${s.id}/delete">Supprimer</a>
  				</td>
  			</tr>
		</c:forEach>
  	</table>
  		<div class="message-form">Formulaire d'ajout / update</div>
 		<c:choose>
  			<c:when test="${stagiaire.id==null}">
  				<c:set var="chemin" value="stagiaire"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="chemin" value="stagiaire/${stagiaire.id}"/>
  			</c:otherwise>
  		</c:choose>
  		
  	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="stagiaire">
  	  <form:hidden path="id"/>
  	  <h2>Personne</h2>

  	  <form:label path="nom">Nom</form:label>
  	  <form:input required="required" path="nom" type="text" placeholder="Saisir nom"/>

	  <form:label path="prenom">Prenom</form:label>
  	  <form:input required="required" path="prenom" type="text" placeholder="Saisir prenom"/>

	  <form:label path="login">Login</form:label>
  	  <form:input required="required" path="login" type="text" placeholder="Saisir login"/>

	  <form:label path="password">Password</form:label>
  	  <form:password required="required" path="password" placeholder="Saisir password"/>

  	  <form:label path="genre">Civilité</form:label>
  		<div class="choice-group">
  			<div class="choice-item">
  				<form:radiobuttons required="required" items="${genres}" path="genre"/>
  			</div>
  		</div>


		
	  <form:label path="email">Email</form:label>
  	  <form:input required="required" path="email" type="email" placeholder="Saisir email" />
  	  
  	  <h2>Adresse</h2>
  	  <form:label path="adresse.numero">Numero</form:label>
  	  <form:input required="required" path="adresse.numero" type="text" placeholder="Saisir le numero" />

  	   <form:label path="adresse.voie">Voie</form:label>
  	  <form:input required="required" path="adresse.voie" type="text"  placeholder="Saisir la voie" />

  	   <form:label path="adresse.ville">Ville</form:label>
  	  <form:input required="required" path="adresse.ville" type="text"  placeholder="Saisir la ville" />

  	   <form:label path="adresse.cp">CP</form:label>
  	  <form:input required="required" path="adresse.cp" type="text"  placeholder="Saisir le cp" />
  	  
  	   <form:label path="filiere.id">Matiere</form:label>
       <form:select required="required" path="filiere.id">
          <form:option value="">Choisir une filiere</form:option>
          <form:options items="${filieres}" itemValue="id" itemLabel="infos"/>
       </form:select>
  	  
  	  
  	  
  	  <div class="form-actions">
  	    <input type="submit" value="Sauvegarder" class="btn btn-success">
  	    <a href="stagiaire" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form:form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>

</body>
</html>
