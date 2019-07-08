<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>login page</title>
<style type="text/css">
.error-block {
	color: #ff0000;
	background-color: #ffEEEE;
	padding: 8px;
	margin: 16px;
}
.success-block {
	color: green;
	background-color: #00ff0026;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload="document.formLogin.j_username.focus()">
	<h3>Login Page</h3>
<!-- 	en caso de haber un error aca lo capturamos -->
	<c:if test="${not empty error}">
		<div class="error-block">
			Your login was unsuccessful caused:
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	
<!-- 	aca recibimos la info del logout -->
	<c:if test="${not empty logout}">
		<div class="success-block">
			you have been logged out succesfully
		</div>
	</c:if>
	
<!-- 	esta es la url que spring espera para auntenticar a un usuario -->
	<form action="j_spring_security_check" name="formLogin" method="post">
		<table>
			<tr>
				<td>
					User: <input type="text" name="j_username" value="" />
				</td>
				<td>
					Password: <input type="password" name="j_password" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit" /> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>