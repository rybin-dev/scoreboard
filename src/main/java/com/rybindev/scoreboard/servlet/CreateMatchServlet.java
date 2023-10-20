package com.rybindev.scoreboard.servlet;

import com.rybindev.scoreboard.model.OngoingMatch;
import com.rybindev.scoreboard.service.OngoingMatchesService;
import com.rybindev.scoreboard.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class CreateMatchServlet extends HttpServlet {
    OngoingMatchesService ongoingMatchesService;

    @Override
    public void init() throws ServletException {
        super.init();
        ongoingMatchesService = (OngoingMatchesService) getServletContext().getAttribute(OngoingMatchesService.class.getName());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.get("new-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fist = req.getParameter("fist");
        String second = req.getParameter("second");

        if (fist != null && second != null && !fist.equals(second)){
            OngoingMatch math = ongoingMatchesService.createMath(fist, second);
            resp.sendRedirect("match-score?uuid=" + math.getUuid());
        }else {
            req.setAttribute("error",true);
            doGet(req, resp);
        }



    }
}
