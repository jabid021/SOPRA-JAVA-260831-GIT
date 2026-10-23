<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
 <%@include file="/WEB-INF/securityAdmin.jsp" %>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Gestion des ordinateurs</title>
	
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
						<a class="btn btn-warning" href="ordinateur/${o.numero}">Modifier</a>
						<a class="btn btn-danger"  href="ordinateur/${o.numero}/delete">Supprimer</a>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<div class="message-form">Formulaire d'ajout / update</div>

		<c:choose>
  			<c:when test="${ordinateur.numero==null}">
  				<c:set var="chemin" value="ordinateur"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="chemin" value="ordinateur/${ordinateur.numero}"/>
  			</c:otherwise>
  		</c:choose>
  		
		<form:form action="${chemin}" method="post" class="form-clean" modelAttribute="ordinateur">
			<form:hidden path="numero"/>

			<form:label path="marque">Marque</form:label>
			<form:input required="required" path="marque" type="text"  placeholder="Saisir la marque" />

			<form:label path="ram">Ram</form:label>
			<form:input required="required" path="ram" type="number"  min="0"  placeholder="Saisir la ram" />



			<form:label path="utilisateur.id">Utilisateur</form:label>
              <form:select path="utilisateur.id">
                <option value="">Pas d'utilisateur</option>
                 <form:options items="${utilisateurs}" itemValue="id" itemLabel="infos"/>
              </form:select>


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
