package com.rybindev.scoreboard.servlet;

import com.rybindev.scoreboard.exception.OngoingMatchNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/error")
public class ErrorHandlerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req,resp);
    }

    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

       // Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
       // String servletName = (String) req.getAttribute("jakarta.servlet.error.servlet_name");
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");


        if (throwable != null) {

            if (OngoingMatchNotFoundException.class == throwable.getClass()){
                resp.sendRedirect("matches");
            }else {
              resp.sendRedirect("error.jsp");

            }

        }

    }
}
