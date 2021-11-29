package com.epam.rest;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@Component
public class ExceptionHandler extends AbstractHandlerExceptionResolver {

  private static final Logger LOGGER = Logger.getLogger(ExceptionHandler.class.getName());

  @Override
  protected ModelAndView doResolveException(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) {

    LOGGER.log(Level.WARNING, "Handling of [" + ex.getClass().getName() + "] resulted in Exception", ex);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("error");
    return modelAndView;
  }
}
