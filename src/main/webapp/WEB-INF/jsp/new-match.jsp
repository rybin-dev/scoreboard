<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
          crossorigin="anonymous">
    <title>New Match</title>
</head>
<body>
<%@ include file="header.jsp" %>

<main class="container">
    <form action="${pageContext.request.contextPath}/new-match" method="post">

        <fieldset>
            <legend>Create Match</legend>

            <c:if test="${requestScope.errors != null}">
                <div style="color: red">
                    <c:forEach var="error" items="${requestScope.errors}">
                        <span> ${error.message} </span> <br>
                    </c:forEach>
                </div>
            </c:if>

            <div class="mb-3">
                <label for="playerFirst" class="form-label">First player name:</label>
                <input type="text" name="first" value="${param.first}" id="playerFirst" class="form-control">
            </div>
            <div class="mb-3">
                <label for="playerSecond" class="form-label">Second player name:</label>
                <input type="text" name="second" value="${param.second}" id="playerSecond" class="form-control">
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </fieldset>
    </form>


</main>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous">
</script>
</body>
</html>