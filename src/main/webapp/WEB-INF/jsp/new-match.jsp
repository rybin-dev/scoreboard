<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css">
    <title>New Match</title>
</head>
<body>
<%@ include file="header.jsp" %>
<main>
    <form action="${pageContext.request.contextPath}/new-match" method="post">
        <h1>Create Match</h1>

        <c:if test="${requestScope.errors != null}">
            <div class="text-error">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span> ${error.message} </span> <br>
                </c:forEach>
            </div>
        </c:if>

        <section>
            <fieldset>
                <p>
                    <label for="player_first">
                        <span>First player name: </span>
                        <input type="text" name="first" value="${param.first}" id="player_first">
                    </label>
                </p>

                <p>
                    <label for="player_second">
                        <span>Second player name: </span>
                        <input type="text" name="second" value="${param.second}" id="player_second">
                    </label>
                </p>

            </fieldset>
        </section>
        <section>
            <button type="submit">Send</button>
        </section>

    </form>


</main>
<%@ include file="footer.jsp" %>
</body>
</html>