package com.gerard.GerardSite.controller.action.impl;

import com.gerard.GerardSite.controller.action.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public enum LogoutAction implements Action {
    INSTANCE;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "logout";
    }
}