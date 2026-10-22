<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<!DOCTYPE html>
<html>
<head>
<script
  src="https://code.jquery.com/jquery-4.0.0.min.js"
  integrity="sha256-OaVG6prZf4v69dPg6PhVattBXkcOWQB62pdZ3ORyrao="
  crossorigin="anonymous"></script>
  <meta charset="UTF-8">
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
  		<tbody id="tbody">
  		<c:if test="${matieres.isEmpty()}">
  			<tr><td align="center" colspan="3">Aucune matiere</td></tr>
  		</c:if>
		<c:forEach items="${matieres}" var="m">
			<tr>
  				<td>${m.id}</td>
  				<td>${m.libelle}</td>
  				<td>
  					<a class="btn btn-warning" href="matiere/${m.id}">Modifier</a>
  					<a class="btn btn-danger" href="matiere/${m.id}/delete">Supprimer</a>
  				</td>
  			</tr>
			
		</c:forEach>
  	</tbody>
  	</table>
  		<c:choose>
  			<c:when test="${matiere.id==null}">
  				<c:set var="operation" value="ajout"/>
  				<c:set var="submitText" value="Ajouter"/>
  				<c:set var="chemin" value="matiere"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="operation" value="update"/>
  				<c:set var="submitText" value="Modifier"/>
  				<c:set var="chemin" value="matiere/${matiere.id}"/>
  			</c:otherwise>
  		</c:choose>
  	
  		<div class="message-form">Formulaire d'${operation}</div>

  	<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="matiere">
  	  <form:hidden path="id"/>

  	  <form:label path="libelle">libelle</form:label>
  	  <form:input required="required" type="text" path="libelle" placeholder="Saisir libelle"/>


  	  <div class="form-actions">
  	    <input type="submit" value="${submitText}" class="btn btn-success"/>
  	    <a href="matiere" class="btn btn-primary">Annuler</a>
  	  </div>
  	</form:form>

  	<br><br>
  	<a class="btn btn-info" href="home">Retour</a>
  </content>

</body>
</html>

<script>


inputFilter.oninput = function()
{

	  $.ajax("matiere/filter", {
		     type: "GET",
		     data: {
		       texte: inputFilter.value
		     },
		     success: function (resp) {
		    	 tbody.innerHTML=resp;
		     }
		   });
}


</script>
