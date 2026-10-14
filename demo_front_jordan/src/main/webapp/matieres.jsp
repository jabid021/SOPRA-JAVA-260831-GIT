<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="quest.model.Matiere" %>
    <%@ page import="java.util.List" %>
     <%@ page import="quest.context.Singleton" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion matieres</title>
</head>
<body>

<h1>Gestion des matieres</h1>

<table border>
	<tr><th>ID</th><th>Libelle</th></tr>
	
	<% 
	List<Matiere> matieres = Singleton.getInstance().getDaoMatiere().findAll();
	
	for(Matiere m : matieres) 
	{
		 out.println("<tr><td>"+m.getId()+"</td><td>"+m.getLibelle()+"</td></tr>");
	}
	%>
	
</table>

</body>
</html>