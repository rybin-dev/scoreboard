package com.rybindev.scoreboard.servlet;

import com.rybindev.scoreboard.mapper.ScoreboardMapper;
import com.rybindev.scoreboard.model.EPlayer;
import com.rybindev.scoreboard.model.OngoingMatch;
import com.rybindev.scoreboard.service.OngoingMatchesService;
import com.rybindev.scoreboard.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    private ScoreboardMapper scoreboardMapper;

    @Override
    public void init() throws ServletException {
        super.init();
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute(OngoingMatchesService.class.getName());
        scoreboardMapper = (ScoreboardMapper) getServletContext().getAttribute(ScoreboardMapper.class.getName());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidP = req.getParameter("uuid");
        UUID uuid = UUID.fromString(uuidP);

        OngoingMatch match = ongoingMatchesService.findByUuid(uuid);

        req.setAttribute("match", scoreboardMapper.mapFrom(match));
        req.getRequestDispatcher(JspHelper.get("match-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidP = req.getParameter("uuid");
        String playerP = req.getParameter("player");

        UUID uuid = UUID.fromString(uuidP);
        EPlayer player = EPlayer.valueOf(playerP.toUpperCase());


        OngoingMatch match = ongoingMatchesService.next(uuid, player);

        req.setAttribute("match", scoreboardMapper.mapFrom(match));
        req.getRequestDispatcher(JspHelper.get("match-score")).forward(req, resp);


    }
}
