package com.epam.rest;

import com.epam.exception.BusinessException;
import com.epam.facade.BookingFacade;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.epam.model.impl.EventImpl;
import com.epam.model.impl.UserImpl;
import java.util.Date;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class MainController {

  private final BookingFacade bookingFacade;

  public MainController(BookingFacade bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @RequestMapping("/")
  public ModelAndView root() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    return modelAndView;
  }

  @GetMapping("/event")
  public Event getEvent(@RequestParam int id) {
    return bookingFacade.getEventById(id);
  }

  @GetMapping("/eventByTitle")
  public List<Event> getEventByTitle(@RequestParam String title) {
    return bookingFacade.getEventsByTitle(title, 1,1);
  }

  @GetMapping("/eventByDate")
  public List<Event> getEventForDay(@RequestParam Date day) {
    return bookingFacade.getEventsForDay(day, 1,1);
  }

  @PostMapping("/event")
  public void createEvent(@RequestParam String title, @RequestParam Date date) {
    Event event = new EventImpl();
    event.setTitle(title);
    event.setDate(date);
    bookingFacade.createEvent(event);
  }

  @PutMapping("/event")
  public void updateEvent(@RequestParam int id, @RequestParam String title, @RequestParam Date date) {
    Event event = new EventImpl();
    event.setId(id);
    event.setTitle(title);
    event.setDate(date);
    bookingFacade.updateEvent(event);
  }

  @DeleteMapping("/event")
  public void deleteEvent(@RequestParam int id) {
    bookingFacade.deleteEvent(id);
  }


  @GetMapping("/user")
  public User getUser(@RequestParam int id) {
    return bookingFacade.getUserById(id);
  }

  @GetMapping("/usersByName")
  public ModelAndView getUsersByName(Model model, @RequestParam String name) {
    List<User> usersByName = bookingFacade.getUsersByName(name, 1, 1);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("showUsers");
    model.addAttribute("users", usersByName);
    return modelAndView;
  }

  @GetMapping("/userByEmail")
  public User getUserByEmail(@RequestParam String email) {
    return bookingFacade.getUserByEmail(email);
  }

  @PostMapping("/user")
  public ModelAndView createUser(RedirectAttributes redirectAttributes, @ModelAttribute("user") UserImpl user) {
    String message = "Success";
    String alert = "alert-success";
    try {
      bookingFacade.createUser(user);
    } catch (BusinessException e) {
      message = "Failed";
      alert = "alert-danger";
    }
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("redirect:/");
    redirectAttributes.addFlashAttribute("message", message);
    redirectAttributes.addFlashAttribute("alertClass", alert);
    return modelAndView;
  }

  @PutMapping("/user")
  public void updateUser(@RequestParam int id, @RequestParam String name, @RequestParam String email) {
    User user = new UserImpl();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);
    bookingFacade.updateUser(user);
  }

  @DeleteMapping("/user")
  public void deleteUser(@RequestParam int id) {
    bookingFacade.deleteUser(id);
  }

  @PostMapping("/book")
  public void book(@RequestParam int userId, @RequestParam int eventId,
      @RequestParam int place, @RequestParam Ticket.Category category) {
    bookingFacade.bookTicket(userId, eventId,place, category);
  }

  @GetMapping("/bookedByUser")
  public List<Ticket> getBookedTicketsByUser(@RequestParam int userId) {
    User user = bookingFacade.getUserById(userId);
    return bookingFacade.getBookedTickets(user, 1, 1);
  }

  @GetMapping("/bookedByEvent")
  public List<Ticket> getBookedTicketsByEvent(@RequestParam int eventId) {
    Event event = bookingFacade.getEventById(eventId);
    return bookingFacade.getBookedTickets(event, 1, 1);
  }

  @DeleteMapping("/ticket")
  public void cancel(@RequestParam int id) {
    bookingFacade.cancelTicket(id);
  }
}
