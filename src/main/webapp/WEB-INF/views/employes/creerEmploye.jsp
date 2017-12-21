<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<a href="<%=request.getContextPath()%>/">Employés</a>
			<a href="<%=request.getContextPath()%>/">Bulletins</a>
		</div>
		<h1>Ajouter un Employé</h1>
		<p>Préfixe Matricule : ${prefixMatricule}</p>
		<form:form method="post" modelAttribut="employe">
			<div class="form-group">
				<label for="Matricule">Matricule</label> 
				<input id="matricule" type="text" class="form-control" name="matricule">
			</div>
			<div class="form-group">
				<label for="Entreprise">Entreprise</label> 
				<form:select path="entreprise" id="entreprise">
					<c:forEach items="${entreprises}" var="entreprise">
						<form:option value="${entreprise.denomination}" label="${entreprise.denomination}"/>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label for="Entreprise">Profil</label> 
				<select class="form-control" id="profil">
					<c:forEach items="${profilsRemu}"  var="profilRemu">
						<option>${profilRemu.code}</option>
					</c:forEach>
  				</select>
			</div>
			<div class="form-group">
				<label for="Entreprise">Grade</label> 
				<select class="form-control" id="grade">
					<c:forEach items="${grades}"  var="grade">
						<option>${grade.code}</option>
					</c:forEach>
  				</select>
			</div>
			<button type="submit" class="btn btn-default">Ajouter</button>
		</form:form>
	</div>
</body>
</html>