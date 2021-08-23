package com.gerard.site.controller.command;

import com.gerard.site.controller.Page;
import com.gerard.site.entity.DogEntity;
import com.gerard.site.exception.ServiceException;
import com.gerard.site.service.impl.DogServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public enum GoToDogsPageCommand implements Command {
    INSTANCE;
    //TODO
    //SHOW ALL ACTIVE
    private final String viewAttributeName = "allDogs";

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws ServiceException {
        List<DogEntity> allDogs =  DogServiceImpl.getInstance().provideActiveDogs();
        request.setAttribute(viewAttributeName, allDogs);
        return Page.DOGS.getPageUrl();
    }
}