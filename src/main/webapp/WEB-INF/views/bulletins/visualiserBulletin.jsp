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
			<a href="<c:url value="/mvc/employes/lister"/>">Employés</a> <a
				href="<c:url value='lister'/>">Bulletins</a>
		</div>
		<h1>Bulletin de salaire</h1>
		<p class="text-right">
			<strong>Période</strong>
		</p>
		<p class="text-right">Du ${bulletin.periode.dateDebut} au
			${bulletin.periode.dateFin}</p>
		<strong class="strong">Entreprise</strong>
		<p>${bulletin.remunerationEmploye.entreprise.denomination}</p>
		<p class="text-right">
			<strong>Matricule</strong> ${bulletin.remunerationEmploye.matricule}
		</p>
		<p>SIRET: ${bulletin.remunerationEmploye.entreprise.siret}</p>
		<strong>Salaire</strong>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Rubriques</th>
						<th>Base</th>
						<th>Taux Salarial</th>
						<th>Montant Salarial</th>
						<th>Taux patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Salaire de base</td>
						<td>${bulletin.remunerationEmploye.grade.nbHeuresBase}</td>
						<td>${bulletin.remunerationEmploye.grade.tauxBase}</td>
						<td>${resultat.salaireDeBase}</td>
						<c:forEach var="i" begin="1" end="2" step="1">
							<td></td>
						</c:forEach>
					</tr>
					<tr>
						<td>Prime Except.</td>
						<c:forEach var="i" begin="1" end="2" step="1">
							<td></td>
						</c:forEach>
						<td>${bulletin.primeExceptionnelle}</td>
						<c:forEach var="i" begin="1" end="2" step="1">
							<td></td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach var="i" begin="1" end="6" step="1">
							<td></td>
						</c:forEach>
					</tr>
					<tr>
						<td>Salaire Brut</td>
						<c:forEach var="i" begin="1" end="2" step="1">
							<td></td>
						</c:forEach>
						<td>${resultat.salaireBrut}</td>
						<c:forEach var="i" begin="1" end="2" step="1">
							<td></td>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
		<strong>Cotisations</strong>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Rubriques</th>
						<th>Base</th>
						<th>Taux Salarial</th>
						<th>Montant Salarial</th>
						<th>Taux patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsNonImposables}" var="cotisation">
						<tr>
							<td>${cotisation.code}</td>
							<td>${resultat.salaireBrut}</td>
							<td>${cotisation.tauxSalarial}</td>
							<td>${resultat.salaireDeBase}</td>
							<td>${cotisation.tauxPatronal}</td>
							<td></td>
						</tr>
					</c:forEach>
					<tr>
						<td>Total Retenue</td>
						<td></td>
						<td></td>
						<td>${resultat.totalRetenueSalarial}</td>
						<td></td>
						<td>${resultat.totalCotisationsPatronales}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<strong>NET IMPOSABLE : ${resultat.netImposable}</strong>
		<div class="table-responsive">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>Rubriques</th>
						<th>Base</th>
						<th>Taux Salarial</th>
						<th>Montant Salarial</th>
						<th>Taux patronal</th>
						<th>Cot. patronales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${bulletin.remunerationEmploye.profilRemuneration.cotisationsImposables}" var="cotisation">
						<tr>
							<td>${cotisation.code}</td>
							<td>${resultat.salaireBrut}</td>
							<td>${cotisation.tauxSalarial}</td>
							<td>${resultat.salaireDeBase}</td>
							<td>${cotisation.tauxPatronal}</td>
							<td></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<strong class="pull-right">NET A PAYER : ${resultat.netAPayer}</strong>
		</div>
	</div>
</body>
</html>