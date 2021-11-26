package com.epam.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@Component
public class ExceptionHandler extends AbstractHandlerExceptionResolver {

  @Override
  protected ModelAndView doResolveException(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) {

    logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", ex);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("error");
    return modelAndView;
  }
}
