<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Match</title>
</head>
<body>
<%@ include file="header.jsp"%>

<div>
    <form method="post">
        <label for="player_name">
            <input type="text" name="playerName" id="player_name">
        </label>
        <button type="submit">search</button>
    </form>
</div>
<div>
<ul>
    <c:forEach var = "match" items="${requestScope.matches}">
        <li>
            <span <c:if test="${match.first.winner}">style="background-color: chartreuse" </c:if>>
                    ${match.first.name}
            </span>
            -
            <span <c:if test="${match.second.winner}"> style="background-color: chartreuse" </c:if>>
                    ${match.second.name}
            </span>
        </li>
    </c:forEach>
</ul>

</div>
<%@ include file="footer.jsp"%>
</body>
</html>
