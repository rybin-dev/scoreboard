package com.rybindev.scoreboard.servlet;

import com.rybindev.scoreboard.model.MatchFilter;
import com.rybindev.scoreboard.service.MatchService;
import com.rybindev.scoreboard.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class MatchServlet extends HttpServlet {
    private MatchService matchService;


    @Override
    public void init() {
        matchService = (MatchService) getServletContext().getAttribute(MatchService.class.getName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String playerName = req.getParameter("playerName");

        MatchFilter matchFilter = new MatchFilter();

        if (page != null && page.matches("\\d{1,9}+")) {
            matchFilter.setPage(Integer.parseInt(page));
        }
        if (playerName != null && !playerName.isEmpty()) {
            matchFilter.setPlayerName(playerName);
        }

        req.setAttribute("pageMatch", matchService.findAll(matchFilter));
        req.getRequestDispatcher(JspHelper.get("matches")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
