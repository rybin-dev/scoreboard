<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match</title>
</head>
<body>
<%@ include file="header.jsp"%>
<div>
  <p> Player1: ${requestScope.match.names[0]}   |
      Set1: ${requestScope.match.setScores[0][0]}
      Set2: ${requestScope.match.setScores[1][0]}
      Set3: ${requestScope.match.setScores[2][0]}   |
      Set: ${requestScope.match.currentSetScore[0]}
      Game: ${requestScope.match.gameScore[0]}</p>
  <p> Player2: ${requestScope.match.names[1]}   |
      Set1: ${requestScope.match.setScores[0][1]}
      Set2: ${requestScope.match.setScores[1][1]}
      Set3: ${requestScope.match.setScores[2][1]}   |
      Set: ${requestScope.match.currentSetScore[1]}
      Game: ${requestScope.match.gameScore[1]}</p>

    <form method="post">
        <button type="submit" name="player" value="FIRST" id="player-1">Send</button>
        <button type="submit" name="player" value="SECOND" id="player-2">Send</button>
    </form>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>
