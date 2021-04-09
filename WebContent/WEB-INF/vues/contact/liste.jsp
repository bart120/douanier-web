
<h1>Liste des contacts</h1>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Mail</th>
			<th>Date de naissance</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="co" items="${contacts}">
			<tr>
				<td>${co.nom}</td>
				<td>${co.prenom}</td>
				<td>${co.mail}</td>
				<td><fmt:formatDate value="${co.dateNaissance}" pattern="dd/MM/yyyy"/></td>
				<td>
					<a href='<c:url value="/contact/detail?id=${co.id}"/>'>detail</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div id="divresult"></div>

<button id="btntest" type="button">Test XSS</button>

<script>
	$("#btntest").click(function(){
		//alert('coucou');
		$.ajax({
			url: 'http://localhost:8080/douanier-web/contact/api',
			type: 'POST',
			success: function(data){
				alert(data.prenom);
				$("#divresult").html(data);
			},
			error: function(){
				alert("erreur");
			}
		});
	});
</script>

