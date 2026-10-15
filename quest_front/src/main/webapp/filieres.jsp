<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
    
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des filières</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">

    <link rel="stylesheet" href="style.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
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
            <!--<tr><td align="center" colspan="6">AUCUN ELEMENT</td></tr>-->

            <tr>
                <td>1</td>
                <td>SOPRA-JAVA-260209</td>
                <td>2026-02-09</td>
                <td>2026-05-12</td>
                <td>1 - Violet</td>
                <td>
                    <a class="btn btn-info" href="module?filiere=1">Modules</a>
                    <a class="btn btn-warning" href="">Modifier</a>
  					<a class="btn btn-danger" href="">Supprimer</a>
                </td>
            </tr>

            <tr>
                <td>2</td>
                <td>SOPRA-JAVA-260407</td>
                <td>2026-04-07</td>
                <td>2026-07-15</td>
                <td>Pas de salle</td>
                <td>
                    <a class="btn btn-info" href="">Modules</a>
                    <a class="btn btn-warning" href="">Modifier</a>
  					<a class="btn btn-danger" href="">Supprimer</a>
                </td>
            </tr>

        </table>
        <div class="message-form">Formulaire d'ajout / update</div>

        <form action="filiere" method="post" class="form-clean">
            <input type="hidden" name="id" value="">

            <label for="libelle">Libellé</label>
            <input required="required" id="libelle" type="text" name="libelle" placeholder="Saisir libellé" value="">

            <label for="debut">Date début</label>
            <input type="date" id="debut" name="debut" value="">

            <label for="fin">Date fin</label>
            <input type="date" id="fin" name="fin" value="">
   

            <label for="salle">Salle</label>
            <select id="salle" name="salle.id">
                <option value="">Pas de salle</option>
                <option value="1">1 - Violet</option>
            </select>


            <div class="form-actions">
                <input type="submit" value="Sauvegarder" class="btn btn-success">
                <a href="filiere" class="btn btn-primary">Annuler</a>
            </div>
        </form>

        <br><br>
        <a class="btn btn-info" href="menuAdmin.html">Retour</a>
    </content>
</body>
</html>
