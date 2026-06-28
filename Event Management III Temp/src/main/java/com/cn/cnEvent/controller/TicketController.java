package com.cn.cnEvent.controller;

import com.cn.cnEvent.entity.Ticket;
import com.cn.cnEvent.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id) {
        return ticketService.getById(id);
    }

    @GetMapping("/all")
    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    @GetMapping("/allByAge/{age}")
    public List<Ticket> getBelowAge(@PathVariable Long age) {
        return ticketService.getBelowAge(age);
    }
}
