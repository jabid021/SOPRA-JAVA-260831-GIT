<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <%@include file="/WEB-INF/securityAdmin.jsp" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des filières</title>

</head>
<body>
    <content>
        <table>
            <tr>
                <th>Id</th>
                <th>Libelle</th>
                <th>Debut</th>
                <th>Fin</th>
                <th>Salle</th>
                <th>Actions</th>
            </tr>
            <c:if test="${modules.isEmpty()}">
  				<tr><td align="center" colspan="6">Aucune filiere</td></tr>
  			</c:if>

	<c:forEach items="${filieres}" var="f">
            <tr>
                <td>${f.id}</td>
                <td>${f.libelle}</td>
                <td>${f.debut}</td>
                <td>${f.fin}</td>
                <c:choose>
	        		<c:when test="${f.salle==null}">
		        		<td>Pas de salle</td>
		        	</c:when>
		        	<c:otherwise>
		        		<td>${f.salle.infos}</td>
		        	</c:otherwise>
		        </c:choose>
                <td>
                    <a class="btn btn-info" href="module/filiere-${f.id}">Modules</a>
                    <a class="btn btn-warning" href="filiere/${f.id}">Modifier</a>
  					<a class="btn btn-danger" href="filiere/${f.id}/delete">Supprimer</a>
                </td>
            </tr>
	</c:forEach>
           
        </table>
        
        <c:choose>
  			<c:when test="${filiere.id==null}">
  				<c:set var="chemin" value="filiere"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="chemin" value="filiere/${filiere.id}"/>
  			</c:otherwise>
  		</c:choose>
  		
        <div class="message-form">Formulaire d'ajout / update</div>

        <form:form action="${chemin}" method="post" class="form-clean" modelAttribute="filiere">
            <form:hidden path="id" />

            <form:label path="libelle">Libellé</form:label>
            <form:input required="required" path="libelle" type="text" placeholder="Saisir libellé"/>
            <form:errors style="color:red" path="libelle"/>

            <form:label path="debut">Date début</form:label>
            <form:input type="date" path="debut" />

            <form:label path="fin">Date fin</form:label>
            <form:input type="date" path="fin" />
   

            <form:label path="salle">Salle</form:label>
            <form:select path="salle.id">
                <form:option value="">Pas de salle</form:option>
                 <form:options items="${salles}" itemValue="id" itemLabel="infos"/>
              </form:select>

            <div class="form-actions">
                <input type="submit" value="Sauvegarder" class="btn btn-success">
                <a href="filiere" class="btn btn-primary">Annuler</a>
            </div>
        </form:form>

        <br><br>
        <a class="btn btn-info" href="home">Retour</a>
    </content>
</body>
</html>
