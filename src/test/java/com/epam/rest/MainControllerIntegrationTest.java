package com.epam.rest;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.epam.facade.impl.BookingFacadeImpl;
import com.epam.model.User;
import com.epam.model.impl.UserImpl;
import com.epam.service.EventService;
import com.epam.service.TicketService;
import com.epam.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


class MainControllerIntegrationTest {

  @Mock
  private UserServiceImpl userService;
  @Mock
  private EventService eventService;
  @Mock
  private TicketService ticketService;
  @InjectMocks
  private BookingFacadeImpl bookingFacade;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(new MainController(bookingFacade)).build();
  }

  @Test
  void testHomePage() throws Exception {
    this.mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"))
        .andDo(MockMvcResultHandlers.print())
        .andReturn();
  }

  @Test
  void testMessagePage() throws Exception {
    String name = "John";
    User user = new UserImpl();
    user.setId(1);
    user.setName(name);
    user.setEmail("email@email.com");
    when(userService.getByName(eq(name), anyInt(), anyInt())).thenReturn(singletonList(user));
    this.mockMvc.perform(get("/usersByName?name=" + name))
        .andExpect(status().isOk())
        .andExpect(view().name("showUsers"))
        .andExpect(model().attributeExists("users"))
        .andExpect(model().attribute("users", containsInAnyOrder(user)));
  }
}

