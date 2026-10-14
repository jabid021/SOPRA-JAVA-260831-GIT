<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib prefix="tpt" uri="jakarta.tags.core"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<tpt:forEach begin="0" end="10" step="1" var="i">

<p>${i}</p>
	<tpt:if test="${i==3}">
		<h1>I vaut 3</h1>
	</tpt:if>
	
	
	<tpt:choose>
		<tpt:when test=""></tpt:when>
		<tpt:when test=""></tpt:when>
		<tpt:when test=""></tpt:when>
		<tpt:otherwise></tpt:otherwise>
	</tpt:choose>
	

</tpt:forEach>

<table border>

<tr><th>Id</th><th>Nom</th><th>Adresse</th></tr>

<tpt:if test="${listeDesSalles.isEmpty()}">
	<tr><td colspan="3">Pas de salle</td></tr>
</tpt:if>

<tpt:forEach items="${listeDesSalles}" var="s">
	<tr><th>${s.id}</th><th>${s.nom}</th><th>${s.adresse.numero} ${s.adresse.voie}, ${s.adresse.ville} ${s.adresse.cp}</th></tr>
</tpt:forEach>



</body>
</html>