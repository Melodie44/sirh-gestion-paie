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
			<a href="<c:url value='lister'/>">Employés</a>
			<a href="<c:url value="/mvc/bulletins/lister"/>">Bulletins</a>
		</div>
		<h1>Ajouter un Employé</h1>
		<p>Préfixe Matricule : ${prefixMatricule}</p>
		<form:form method="post" modelAttribute="employe">
			<div class="form-group">
				<label for="Matricule">Matricule</label> 
				<form:input path="matricule" value="${prefixMatricule}" class="form-control"/>
			</div>
			<div class="form-group">
				<label for="Entreprise">Entreprise</label> 
				<form:select path="entreprise.id" class="form-control">
					<form:options items="${entreprises}" itemValue="id" itemLabel="denomination"/>
				</form:select>
			</div>
			<div class="form-group">
				<label for="Profil">Profil</label> 
				<form:select path="profilRemuneration.id" class="form-control">
					<form:options items="${profilsRemu}" itemValue="id" itemLabel="code"/>
				</form:select>
			</div>
			<div class="form-group">
				<label for="Grade">Grade</label> 
				<form:select path="grade.id" class="form-control">
					<form:options items="${grades}" itemValue="id" itemLabel="code"/>
				</form:select>
			</div>
			<button type="submit" value="submit" class="btn btn-default float-right">Ajouter</button>
		</form:form>
	</div>
</body>
</html>