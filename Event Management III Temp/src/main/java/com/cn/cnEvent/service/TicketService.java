package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.TicketDAL;
import com.cn.cnEvent.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketDAL ticketDAL;

    public Ticket getById(Long id) {
        return ticketDAL.getById(id);
    }

    public List<Ticket> getAll() {
        return ticketDAL.getAllTicket();
    }

    public List<Ticket> getBelowAge(Long age) {
        return ticketDAL.getBelowAge(age);
    }
}
