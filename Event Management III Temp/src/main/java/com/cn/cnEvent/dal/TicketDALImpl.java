package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Ticket;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDALImpl implements TicketDAL{
    @Autowired
    EntityManager entityManager;

    @Override
    public Ticket getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Ticket.class, id);
    }

    @Override
    public List<Ticket> getAllTicket() {
        Session session = entityManager.unwrap(Session.class);
        List<Ticket> list = session.createQuery("from Ticket", Ticket.class).getResultList();
        return list;
    }

    @Override
    public List<Ticket> getBelowAge(Long age) {
        List<Ticket> allTicket = getAllTicket();
        List<Ticket> result = new ArrayList<>();
        for (Ticket ticket: allTicket) {
            if (ticket.getPerson().getAge() < age) result.add(ticket);
        }

        return result;
    }
}
