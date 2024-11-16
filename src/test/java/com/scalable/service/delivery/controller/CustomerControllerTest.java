package com.scalable.service.delivery.controller;

import com.scalable.service.delivery.model.Customer;
import com.scalable.service.delivery.model.Order;
import com.scalable.service.delivery.service.CustomUserDetailsService;
import com.scalable.service.delivery.service.CustomerService;
import com.scalable.service.delivery.service.JwtService;
import com.scalable.service.delivery.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void testRegisterCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("John Doe");

        Mockito.when(customerService.registerCustomer(any(Customer.class))).thenReturn(customer);
        Mockito.when(jwtService.generateToken(any())).thenReturn("valid-token");
        Mockito.when(customUserDetailsService.loadUserByUsername(anyString())).thenReturn(new org.springframework.security.core.userdetails.User("user", "password", new ArrayList<>()));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"password\": \"password123\"}")
                        .header("Authorization", "Bearer valid-token"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andDo(print());
    }

    @Test
    public void testGetOrderHistory() throws Exception {
        Order order = new Order();
        order.setId("1");
        order.setCustomerId("1");
        List<Order> orders = Collections.singletonList(order);

        Mockito.when(orderService.getOrderHistory(anyString())).thenReturn(orders);
        Mockito.when(customUserDetailsService.loadUserByUsername(anyString())).thenReturn(new org.springframework.security.core.userdetails.User("user", "password", new ArrayList<>()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/customer/1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer valid-token"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].customerId").value("1"))
                .andDo(print());
    }
}