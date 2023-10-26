<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
          crossorigin="anonymous">
    <title>Match</title>
</head>
<body>
<%@ include file="header.jsp" %>

<main class="container">

        <h1>Score board</h1>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Player</th>
                <th scope="col">Set1</th>
                <th scope="col">Set2</th>
                <th scope="col">Set3</th>
                <th scope="col">Game</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${requestScope.match.names[0]}</td>
                <td>${requestScope.match.setScores[0][0]}</td>
                <td>${requestScope.match.setScores[1][0]}</td>
                <td>${requestScope.match.setScores[2][0]}</td>
                <td>${requestScope.match.gameScore[0]}</td>
                <td>
                    <form method="post">
                        <button class="btn btn-primary" type="submit" name="player" value="FIRST" id="player-1">
                            Send
                        </button>
                    </form>
                </td>

            </tr>
            <tr>
                <td>${requestScope.match.names[1]}</td>
                <td>${requestScope.match.setScores[0][1]}</td>
                <td>${requestScope.match.setScores[1][1]}</td>
                <td>${requestScope.match.setScores[2][1]}</td>
                <td>${requestScope.match.gameScore[1]}</td>
                <td>
                    <form method="post">
                        <button class="btn btn-primary" type="submit" name="player" value="SECOND" id="player-2">
                            Send
                        </button>
                    </form>

                </td>
            </tr>
            </tbody>
        </table>


</main>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous">
</script>
</body>
</html>
