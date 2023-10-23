<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <section>
        <div>
            <form method="post">
                <label for="player_name">
                    <span>Player name: </span>
                    <input type="text" name="playerName" id="player_name">
                </label>
                <button type="submit">search</button>
            </form>
        </div>
    </section>

    <section>
        <div>
            <ul>
                <c:forEach var="match" items="${requestScope.pageMatch.matches}">
                    <li>
                    <span <c:if test="${match.first.winner}">class="winner"</c:if>>
                            ${match.first.name}
                    </span>
                        -
                        <span <c:if test="${match.second.winner}">class="winner"</c:if>>
                                ${match.second.name}
                        </span>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </section>

    <section>
        <div>
            <c:forEach var="counter" begin="1" end="${requestScope.pageMatch.countPage}">
                <a href="${pageContext.request.contextPath}/matches?page=${counter}<c:if test="${pageScope.param.playerName != null}" >&playerName=${pageScope.param.playerName}</c:if>">
                        ${counter}
                </a>

            </c:forEach>
        </div>
    </section>

</main>

<%@ include file="footer.jsp" %>
</body>
</html>
