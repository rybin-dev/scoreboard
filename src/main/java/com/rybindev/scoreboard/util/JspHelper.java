package com.rybindev.scoreboard.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String FORMAT = "WEB-INF/jsp/%s.jsp";

    public String get(String t){
        return String.format(FORMAT,t);
    }
}
