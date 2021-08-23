package com.gerard.site.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller, extends HttpServlet interface
 * {@link HttpServlet}, overrides and manages
 * only 'GET' and 'POST' HTTP requests, works as role controller .
 * <p>Redirects 'POST' requests . Forwards 'GET' requests .
 * Uses Router class com.gerard.site.controller.Router {@link Router}
 * for preparing url and location for redirecting and forwarding respectively .
 * </p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 * @see Router#prepareUrl(HttpServletRequest, HttpServletResponse)
 * @see Router#preparePath(HttpServletRequest, HttpServletResponse)
 */
@WebServlet
public class Controller extends HttpServlet {
    /**
     * Application context on server. Is using for preparing page path
     */
     static final String SCHEMA = "http://";
     static final String DOMAIN = "localhost:8080";
     static final String APPLICATION_CONTEXT = "/gerard";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String url = Router.prepareUrl(request, response);
        String location = APPLICATION_CONTEXT + url;
        response.sendRedirect(location);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String path = Router.preparePath(request, response);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}