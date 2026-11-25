<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Liste des Patients</title></head>
<body>
    <h2>Gestion des Patients (ADMIN)</h2>
    <p>
        <a href="${pageContext.request.contextPath}/patients/nouveau">+ Nouveau patient</a> |
        <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
    </p>
    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>
    <table border="1" cellpadding="6" cellspacing="0">
        <thead>
            <tr>
                <th>Matricule</th>
                <th>Nom & Prénom</th>
                <th>Groupe sanguin</th>
                <th>Situation médicale</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${patients}">
                <tr>
                    <td>${p.matricule}</td>
                    <td>${p.nom} ${p.prenom}</td>
                    <td>${p.groupeSanguin}</td>
                    <td>${p.situationMedicale}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/patients/fiche?id=${p.id}">Fiche</a> |
                        <a href="${pageContext.request.contextPath}/patients/editer?id=${p.id}">Modifier</a> |
                        <form action="${pageContext.request.contextPath}/patients/supprimer" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="${p.id}" />
                            <button type="submit" onclick="return confirm('Supprimer ce patient ?');">Supprimer</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>