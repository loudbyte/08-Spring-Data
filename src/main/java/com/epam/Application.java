package com.epam;

import com.epam.facade.BookingFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

  public static void main(String[] args) {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookingFacade bookingFacade = context.getBean(BookingFacade.class);


  }

}
