<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css">


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


		<br><br>
		<a class="btn btn-danger" href="index.html">Deconnexion</a>
	</content>

</body>

</html>
