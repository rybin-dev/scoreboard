<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
    <title>Hello World!</title>
</head>
<body>
<%@ include file="header.jsp" %>
<main>
    <h1> Hello World! </h1>
    <br/>
    <a href="${pageContext.request.contextPath}/new-match">New Match</a>
    <br/>
    <a href="${pageContext.request.contextPath}/matches">Matches</a>
</main>
<%@ include file="footer.jsp" %>
</body>
</html>