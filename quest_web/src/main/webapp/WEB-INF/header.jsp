<%@ taglib prefix="c" uri="jakarta.tags.core" %> 

<base href="/quest_web/">
 <!--<base href="${pageContext.request.contextPath}/">-->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css">


<header>
  <div><a href="home"><img src="assets/images/makers-logo.png" ></a></div>
  <div>accueil</div>
  
  <c:if test="${connected!=null}">
  	<a class="btn btn-danger" id="btnDisconnect" href="logout">Deconnexion</a>
  </c:if>
</header>