<c:if test="${connected==null || !roles.contains('ROLE_ADMIN')}">
	<c:redirect url="home"/>
</c:if>