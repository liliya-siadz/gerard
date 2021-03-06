package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.service.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import com.gerard.site.service.view.Dog;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToPuppiesPageCommand implements Command {
    INSTANCE;
    private final String viewAttributeName = "puppies";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServiceException {
        List<Dog> puppies =  DogServiceImpl.getInstance().provideAllPuppiesForView();
        request.setAttribute(viewAttributeName, puppies);
        return Page.PUPPIES.getPageUrl();
    }
}
