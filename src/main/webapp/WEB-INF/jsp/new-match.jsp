<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Match</title>
</head>
<body>
<%@ include file="header.jsp"%>

<h3>Create Match</h3>

<form action="${pageContext.request.contextPath}/new-match" method="post">
    <label for="player_first">
        <input type="text" name="fist" id="player_first">
    </label>
    <br>
    <label for="player_second">
        <input type="text" name="second" id="player_second">
    </label>
    <br>
    <button type="submit">Send</button>
</form>


<%@ include file="footer.jsp"%>
</body>
</html>