<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Connexion - Hôpital</title></head>
<body>
    <h2>Connexion Hôpital</h2>
    <c:if test="${not empty erreur}">
        <p style="color:red;">${erreur}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p style="color:green;">${message}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/login/submit" method="post">
        <div>
            <label>Utilisateur :</label>
            <input type="text" name="username" required />
        </div>
        <div>
            <label>Mot de passe :</label>
            <input type="password" name="password" required />
        </div>
        <button type="submit">Se connecter</button>
    </form>
</body>
</html>