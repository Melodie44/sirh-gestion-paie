<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<h1>Liste des employés</h1>
		<a class="btn btn-default float-right" href="<c:url value='creer'/>">Ajouter un employé</a>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Date/heure création</th>
						<th>Matricule</th>
						<th>Grade</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${remusEmployes}" var="remuEmploye">
						<tr>
							<td>${dates[remuEmploye.id-1]}</td>
							<td>${remuEmploye.matricule}</td>
							<td>${remuEmploye.grade.code}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>	
		</div>	
	</div>
</body>
</html>