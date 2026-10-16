<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
  
  
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
                    <a class="btn btn-info" href="module?filiere=${f.id}">Modules</a>
                    <a class="btn btn-warning" href="filiere?id=${f.id}">Modifier</a>
  					<a class="btn btn-danger" href="filiere?id=${f.id}&delete">Supprimer</a>
                </td>
            </tr>
	</c:forEach>
           
        </table>
        <div class="message-form">Formulaire d'ajout / update</div>

        <form action="filiere" method="post" class="form-clean">
            <input type="hidden" name="id" value="${filiere.id}">

            <label for="libelle">Libellé</label>
            <input required="required" id="libelle" type="text" name="libelle" placeholder="Saisir libellé" value="${filiere.libelle}">

            <label for="debut">Date début</label>
            <input type="date" id="debut" name="debut" value="${filiere.debut}">

            <label for="fin">Date fin</label>
            <input type="date" id="fin" name="fin" value="${filiere.fin}">
   

            <label for="salle">Salle</label>
            <select id="salle" name="salle.id">
                <option value="">Pas de salle</option>
                 <c:forEach items="${salles}" var="s">
                     <option <c:if test="${s.id==filiere.salle.id}">selected</c:if> value="${s.id}">${s.infos}</option>
                 </c:forEach>
              </select>

            <div class="form-actions">
                <input type="submit" value="Sauvegarder" class="btn btn-success">
                <a href="filiere" class="btn btn-primary">Annuler</a>
            </div>
        </form>

        <br><br>
        <a class="btn btn-info" href="home">Retour</a>
    </content>
</body>
</html>
