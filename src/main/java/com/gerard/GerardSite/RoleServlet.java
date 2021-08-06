package com.gerard.GerardSite;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/kennel")
public class RoleServlet extends HttpServlet {

    @Override
    public void init() {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = request.getParameter("command");
        response.addCookie(new Cookie("locale", locale));
        if(locale.equalsIgnoreCase("SWITCH_LOCALE_TO_BE")){
            response.addCookie(new Cookie("bundle", "locale_be"));
        }
        else if(locale.equalsIgnoreCase("SWITCH_LOCALE_TO_RU")){
            response.addCookie(new Cookie("bundle", "locale_ru"));
        }
        //request.getRequestDispatcher("pages/home.jsp").forward(request,response);
        response.sendRedirect("http://localhost:8080/GerardSite/kennel");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       //select
       request.setAttribute("localePseudoScope", request.getServletPath());
       Cookie cookie = new Cookie("sfdf", "sfsef");
       request.setAttribute("Accept-Language", request.getHeader("Accept-Language"));
      request.getRequestDispatcher("pages/home.jsp").forward(request,response);
    }

    @Override
    public void destroy() {
    }
}