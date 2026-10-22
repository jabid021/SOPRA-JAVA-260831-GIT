<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
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
	          <a class="btn btn-warning" href="module/${m.id}/filiere-${m.filiere.id}">Modifier</a>
	          <a class="btn btn-danger" href="module/${m.id}/delete/filiere-${m.filiere.id}">Supprimer</a>
	        </td>
	      </tr>
      </c:forEach>
  
        </table>
          <div class="message-form">Formulaire d'ajout / update</div>

		<c:choose>
  			<c:when test="${module.id==null}">
  				<c:set var="chemin" value="module/filiere-${filiereId}"/>
  			</c:when>
  			<c:otherwise>
  				<c:set var="chemin" value="module/${module.id}/filiere-${filiereId}"/>
  			</c:otherwise>
  		</c:choose>
          <form:form action="${chemin}" method="post" class="form-clean" modelAttribute="module">
            <form:hidden  path="id"/>
            <input type="hidden" name="filiere.id" value="${filiereId}">


            <form:label path="quest">Quest</form:label>
            <form:input required="required" path="quest" type="number" placeholder="Saisir le quest"/>

            <form:label path="debut">Debut</form:label>
            <form:input required="required" path="debut" type="date" />

            <form:label path="fin">Fin</form:label>
            <form:input required="required" path="fin" type="date"/>


            <form:label path="matiere.id">Matiere</form:label>
                  <form:select path="matiere.id" required="required">
                    <form:option value="">Choisir une matiere</form:option>
                    <form:options items="${matieres}" itemValue="id" itemLabel="infos"/>
                  </form:select>
             
              <form:label path="formateur.id">Formateur</form:label>
              <form:select path="formateur.id" >
                <form:option value="">Pas de formateur</form:option>
                 <form:options items="${formateurs}" itemValue="id" itemLabel="infos"/>
              </form:select>

              <div class="form-actions">
                <input type="submit" value="Sauvegarder" class="btn btn-success">
                <a href="module?filiere=${param.filiere}" class="btn btn-primary">Annuler</a>
              </div>
            </form:form>

            <br><br>
            <a class="btn btn-info" href="filiere">Retour</a>
          </content>

        </body>
        </html>