package com.rybindev.scoreboard.servlet;

import com.rybindev.scoreboard.exception.ValidationException;
import com.rybindev.scoreboard.model.CreateMatchDto;
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
        String first = req.getParameter("first");
        String second = req.getParameter("second");

        first = first != null ? first.trim() : null;
        second= second != null ? second.trim() : null;

        CreateMatchDto createMatchDto = new CreateMatchDto(first , second);

        try {
            OngoingMatch math = ongoingMatchesService.createMath(createMatchDto);
            resp.sendRedirect("match-score?uuid=" + math.getUuid());
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }


    }
}
