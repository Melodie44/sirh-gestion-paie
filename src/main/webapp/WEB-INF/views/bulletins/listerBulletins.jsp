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
			<a href="<c:url value="/mvc/employes/lister"/>">Employés</a>
			<a href="<c:url value='lister'/>">Bulletins</a>
		</div>
		<h1>Liste des bulletins</h1>
		<a class="btn btn-default float-right" href="<c:url value='creer'/>">Créer un nouveau bulletin</a>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Date/heure création</th>
						<th>Période</th>
						<th>Matricule</th>
						<th>Salaire brut</th>
						<th>Net Imposable</th>
						<th>Net A Payer</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bulletins}" var="bulletin">
						<tr>
							<td>${bulletin.dateHeureCreation}</td>
							<td>${bulletin.periode.periode}</td>
							<td>${bulletin.remunerationEmploye.matricule}</td>
							<td>${resultats[bulletin.id-1].salaireBrut}</td>
							<td>${resultats[bulletin.id-1].netImposable}</td>
							<td>${resultats[bulletin.id-1].netAPayer}</td>
							<td><a href="<c:url value='visualiser/${bulletin.id}'/>">Visualiser</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>	
		</div>	
	</div>
</body>
</html>