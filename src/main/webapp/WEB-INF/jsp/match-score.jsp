<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
    <title>Match</title>
</head>
<body>
<%@ include file="header.jsp" %>

<main>
    <form method="post">
        <h1>Score board</h1>
        <table>
            <tr>
                <th>Player</th>
                <th>Set1</th>
                <th>Set2</th>
                <th>Set3</th>
                <th>Game</th>
                <th></th>
            </tr>
            <tr>
                <td>${requestScope.match.names[0]}</td>
                <td>${requestScope.match.setScores[0][0]}</td>
                <td>${requestScope.match.setScores[1][0]}</td>
                <td>${requestScope.match.setScores[2][0]}</td>
                <td>${requestScope.match.gameScore[0]}</td>
                <td>
                    <button type="submit" name="player" value="FIRST" id="player-1">Send</button>
                </td>

            </tr>
            <tr>
                <td>${requestScope.match.names[1]}</td>
                <td>${requestScope.match.setScores[0][1]}</td>
                <td>${requestScope.match.setScores[1][1]}</td>
                <td>${requestScope.match.setScores[2][1]}</td>
                <td>${requestScope.match.gameScore[1]}</td>
                <td>
                    <button type="submit" name="player" value="SECOND" id="player-2">Send</button>
                </td>
            </tr>
        </table>
    </form>

</main>

<%@ include file="footer.jsp" %>
</body>
</html>
