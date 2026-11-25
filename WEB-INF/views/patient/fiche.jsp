<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Fiche Patient</title></head>
<body>
    <h2>Fiche Médicale : ${patient.nom} ${patient.prenom}</h2>
    <p>
        <img src="/cdn/patients/${patient.id}/qrcode/${patient.id}-qrcode.jpg" 
        alt="QR Code ${patient.matricule}" 
        width="200" height="200">
    </p>
    <p><strong>Matricule :</strong> ${patient.matricule}</p>
    <p><strong>Date de naissance :</strong> ${patient.dateNaissance}</p>
    <p><strong>Sexe :</strong> ${patient.sexe}</p>
    <p><strong>Téléphone :</strong> ${patient.telephone}</p>
    <p><strong>Groupe sanguin :</strong> ${patient.groupeSanguin}</p>
    <p><strong>Date d'admission :</strong> ${patient.dateAdmission}</p>
    <div>
        <strong>Situation médicale :</strong>
        <p>${patient.situationMedicale}</p>
    </div>
    <p>
        <a href="${pageContext.request.contextPath}/patients/editer?id=${patient.id}">Modifier</a> |
        <a href="${pageContext.request.contextPath}/patients">Retour à la liste</a>
    </p>
</body>
</html>