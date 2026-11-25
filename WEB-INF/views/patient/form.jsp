<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Saisie Patient</title></head>
<body>
    <h2>${mode == 'edit' ? 'Modifier un patient' : 'Nouveau patient'}</h2>
    <form action="${pageContext.request.contextPath}/patients/enregistrer" method="post">
        <c:if test="${patient.id > 0}">
            <input type="hidden" name="id" value="${patient.id}" />
        </c:if>
        <div>
            <label>Matricule :</label>
            <input type="text" name="matricule" value="${patient.matricule}" required />
        </div>
        <div>
            <label>Nom :</label>
            <input type="text" name="nom" value="${patient.nom}" required />
        </div>
        <div>
            <label>Prénom :</label>
            <input type="text" name="prenom" value="${patient.prenom}" required />
        </div>
        <div>
            <label>Date de naissance :</label>
            <input type="date" name="dateNaissanceStr" value="${patient.dateNaissance}" />
        </div>
        <div>
            <label>Sexe :</label>
            <select name="sexe">
                <option value="M" ${patient.sexe == 'M' ? 'selected' : ''}>Masculin</option>
                <option value="F" ${patient.sexe == 'F' ? 'selected' : ''}>Féminin</option>
            </select>
        </div>
        <div>
            <label>Téléphone :</label>
            <input type="text" name="telephone" value="${patient.telephone}" />
        </div>
        <div>
            <label>Groupe sanguin :</label>
            <input type="text" name="groupeSanguin" value="${patient.groupeSanguin}" placeholder="ex: O+, A-" />
        </div>
        <div>
            <label>Situation médicale :</label><br/>
            <textarea name="situationMedicale" rows="5" cols="45" required>${patient.situationMedicale}</textarea>
        </div>
        <br/>
        <button type="submit">Enregistrer</button>
        <a href="${pageContext.request.contextPath}/patients">Annuler</a>
    </form>
</body>
</html>