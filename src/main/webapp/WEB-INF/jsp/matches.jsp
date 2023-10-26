<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <h1>Matches</h1>

    <table class="table">
        <thead>
        <tr>
            <th colspan="4" scope="col">
                <form class="d-flex" method="get">
                    <input class="form-control me-2" name="playerName" type="search" placeholder="Поиск"
                           aria-label="Поиск">
                    <button class="btn btn-outline-success" type="submit">search</button>
                </form>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="match" items="${requestScope.pageMatch.matches}">
            <tr>
                <td class="<c:if test="${match.first.winner}"> text-success</c:if>">
                        ${match.first.name}
                </td>

                <td class="<c:if test="${match.second.winner}"> text-success</c:if>">
                        ${match.second.name}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-start">
            <c:if test="${requestScope.pageMatch.page - 2 >= 1 }">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/matches?page=${requestScope.pageMatch.page - 2}<c:if test="${param.playerName != null}">&playerName=${param.playerName}</c:if>">
                        Previous
                    </a>
                </li>
            </c:if>


            <c:forEach var="counter" begin="${requestScope.pageMatch.page - 2 + 1}"
                       end="${requestScope.pageMatch.page-1}">
                <c:if test="${requestScope.pageMatch.page - 2 + 1 > 0  }">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/matches?page=${counter}<c:if test="${param.playerName != null}" >&playerName=${param.playerName}</c:if>">
                                ${counter}
                        </a>
                    </li>
                </c:if>
            </c:forEach>


            <li class="page-item">
                <a class="page-link bg-primary text-light"
                   href="${pageContext.request.contextPath}/matches?page=${requestScope.pageMatch.page}<c:if test="${param.playerName != null}" >&playerName=${param.playerName}</c:if>">
                    ${requestScope.pageMatch.page}
                </a>
            </li>

            <c:forEach var="counter1" begin="${requestScope.pageMatch.page + 1}"
                       end="${requestScope.pageMatch.page + 2}">
                <c:if test="${counter1 <= requestScope.pageMatch.countPage }">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/matches?page=${counter1}<c:if test="${param.playerName != null}" >&playerName=${param.playerName}</c:if>">
                                ${counter1}
                        </a>
                    </li>
                </c:if>
            </c:forEach>

            <c:if test="${requestScope.pageMatch.page + 3 <= requestScope.pageMatch.countPage }">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/matches?page=${requestScope.pageMatch.page + 3}
                            <c:if test="${param.playerName != null}" >&playerName=${param.playerName}</c:if>">
                        Next
                    </a>
                </li>
            </c:if>

        </ul>
    </nav>


</main>

<%@ include file="footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous">
</script>
</body>
</html>
