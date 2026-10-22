 <%@include file="/WEB-INF/securityAdmin.jsp" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Menu Admin</title>
</head>

<body>

<h1>Welcome ${connected.login} (${connected.id} - ${connected.prenom} ${connected.nom})</h1>
	<content>

		<a class="btn btn-info" href="formateur">Formateurs</a>
		<a class="btn btn-info" href="stagiaire">Stagiaires</a>
		<a class="btn btn-info" href="ordinateur">Ordinateurs</a>
		<a class="btn btn-info" href="filiere">Filieres</a>
		<a class="btn btn-info" href="matiere">Matieres</a>
		<a class="btn btn-info" href="salle">Salles</a>
	</content>

</body>

</html>
