<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PAIE - App</title>
<link rel="stylesheet"
	  href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div>
			<a href="<c:url value="/mvc/employes/lister"/>">Employés</a>
			<a href="<c:url value='lister'/>">Bulletins</a>
		</div>
		<h1>Créer Bulletin de Salaire</h1>
		<form:form method="post" modelAttribute="bulletin">
			<div class="form-group">
				<label for="Periode">Période</label> 
				<form:select path="periode.id" class="form-control">
					<form:options items="${periodes}" itemValue="id" itemLabel="periode"/>
				</form:select>
			</div>
			<div class="form-group">
				<label for="RemunerationEmploye">Matricule</label> 
				<form:select path="remunerationEmploye.id" class="form-control">
					<form:options items="${remusEmployes}" itemValue="id" itemLabel="matricule"/>
				</form:select>
			</div>
			<div class="form-group">
				<label for="PrimeExceptionnelle">Prime Exceptionnelle</label> 
				<form:input path="primeExceptionnelle" class="form-control"/>
			</div>
			<button type="submit" value="submit" class="btn btn-default float-right">Créer</button>
		</form:form>
	</div>
</body>
</html>