package com.gerard.site.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Class created to route HTTP requests that coming to controller
 * (Controller class {@link Controller} .
 * <p>
 *    To find needing route extracts special parameter from
 *    request. Then uses this parameter value to find
 *    and execute specified action by calling method
 *    getAction() in class com.gerard.site.controller.ActionFactory
 *    {@link ActionFactory#getAction(String)}.
 * </p>
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
class Router {

    /**
     * Request parameter name to be extracted from the request .
     * Is using as action identifier
     */
    private static final String ACTION_IDENTIFIER_REQUEST_PARAMETER_NAME = "command";

    /**
     * Root directory name where must be stored all pages .
     * Is using for preparing page path
     */
    private static final String DIRECTORY_NAME = "/pages";

    /**
     * File extension for pages. Is using for preparing page path
     */
    private static final String FILE_EXTENSION = ".jsp";

    private Router(){
    }

    /**
     * Prepares target page's url by extracting specified request's parameter.
     *
     * @param request HTTP request to extract parameter from
     * @param response HTTP response assigned to this HTTP request
     * @return page url
     */
    static String prepareUrl(HttpServletRequest request, HttpServletResponse response){
        String command = request.getParameter(ACTION_IDENTIFIER_REQUEST_PARAMETER_NAME);
        Action action = ActionFactory.INSTANCE.getAction(command);
        String url = action.execute(request, response);
        return url;
    }

    /**
     * Prepares target page's path by extracting specified request's parameter.
     *
     * @param request HTTP request to extract parameter from
     * @param response HTTP response assigned to this HTTP request
     * @return page path
     */
    static String preparePath(HttpServletRequest request, HttpServletResponse response) {
        String url = prepareUrl(request, response);
        String path = DIRECTORY_NAME + url + FILE_EXTENSION;
        return path;
    }
}