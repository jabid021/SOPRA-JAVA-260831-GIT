<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 

<%@include file="/WEB-INF/securityAdmin.jsp" %>

<!DOCTYPE html>
<html>
<head>

  <meta charset="UTF-8">
  <title>Gestion des Modules</title>
</head>
<body>
  <content>
  
  <h1 align="center">Gestion des modules de la filiere ${param.filiere}</h1>
  
  <br>
    <table>
      <tr>
        <th>Id</th>
        <th>Quest</th>
        <th>Debut</th>
        <th>Fin</th>
        <th>Matiere</th>
        <th>Formateur</th>
        <th>Actions</th>
      </tr>
      
      <c:if test="${modules.isEmpty()}">
  			<tr><td align="center" colspan="7">Aucun module dans cette filiere</td></tr>
  		</c:if>
  	
  	<c:forEach items="${modules}" var="m">
	      <tr>
	        <td>${m.id}</td>
	        <td>${m.quest}</td>
	        <td>${m.debut}</td>
	        <td>${m.fin}</td>
	        <td>${m.matiere.infos}</td>
	        <c:choose>
	        	<c:when test="${m.formateur==null}">
	        		<td>Pas de formateur</td>
	        	</c:when>
	        	<c:otherwise>
	        		<td>${m.formateur.infos}</td>
	        	</c:otherwise>
	        </c:choose>
	        <td>
	          <a class="btn btn-warning" href="module?id=${m.id}&filiere=${m.filiere.id}">Modifier</a>
	          <a class="btn btn-danger" href="module?id=${m.id}&filiere=${m.filiere.id}&delete">Supprimer</a>
	        </td>
	      </tr>
      </c:forEach>
  
        </table>
          <div class="message-form">Formulaire d'ajout / update</div>

          <form action="module" method="post" class="form-clean">
            <input type="hidden" name="id" value="${module.id}">
            <input type="hidden" name="filiere.id" value="${param.filiere}">


            <label for="quest">Quest</label>
            <input required="required" id="quest" type="number" name="quest" placeholder="Saisir le quest" value="${module.quest}">

            <label for="debut">Debut</label>
            <input required="required" id="debut" type="date" name="debut" value="${module.debut}">

            <label for="fin">Fin</label>
            <input required="required" id="fin" type="date" name="fin" value="${module.fin}">


            <label for="matiere">Matiere</label>
                  <select id="matiere" required="required" name="matiere.id">
                    <option value="">Choisir une matiere</option>
                    <c:forEach items="${matieres}" var="m">
                    	<c:choose>
                    	<c:when test="${m.id==module.matiere.id}">
                    		<option selected value="${m.id}">${m.infos}</option>
                    	</c:when>
                    	<c:otherwise>
                    		<option value="${m.id}">${m.infos}</option>
                    	</c:otherwise>
                    	</c:choose>
                     	
                    </c:forEach>
                  </select>

              <label for="formateur">Formateur</label>
              <select id="formateur" name="formateur.id">
                <option value="">Pas de formateur</option>
                 <c:forEach items="${formateurs}" var="f">
                     <option <c:if test="${f.id==module.formateur.id}">selected</c:if> value="${f.id}">${f.infos}</option>
                 </c:forEach>
              </select>

              <div class="form-actions">
                <input type="submit" value="Sauvegarder" class="btn btn-success">
                <a href="module?filiere=${param.filiere}" class="btn btn-primary">Annuler</a>
              </div>
            </form>

            <br><br>
            <a class="btn btn-info" href="filiere">Retour</a>
          </content>

        </body>
        </html>