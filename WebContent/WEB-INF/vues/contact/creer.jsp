<h1>Nouveau contact</h1>
<form action='<c:url value="/contact/creer" />' method="post">
  <div class="mb-3">
    <label for="inputNom" class="form-label">Nom</label>
    <input type="text" class="form-control" id="inputNom" name="inputNom">
  </div>
  <div class="mb-3">
    <label for="inputPrenom" class="form-label">Prénom</label>
    <input type="text" class="form-control" id="inputPrenom" name="inputPrenom">
  </div>
  <div class="mb-3">
    <label for="inputMail" class="form-label">Mail</label>
    <input type="email" class="form-control" id="inputMail" name="inputMail">
  </div>
  <div class="mb-3">
    <label for="inputDateNaissance" class="form-label">Date de naissance</label>
    <input type="date" class="form-control" id="inputDateNaissance" name="inputDateNaissance">
  </div>
  
  <button type="submit" class="btn btn-primary">Enregistrer</button>
</form>