package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.Speaker;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SpeakerDALImpl implements SpeakerDAL {

    @Autowired
    EntityManager entityManager;

    @Override
    public Speaker getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Speaker.class, id);
    }

    @Override
    public List<Speaker> getAllSpeaker() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Speaker", Speaker.class).getResultList();
    }

    @Override
    public String save(Speaker speaker) {
        Session session = entityManager.unwrap(Session.class);
        session.save(speaker);
        return "The speaker was saved successfully.";
    }

    @Override
    public void addSpeakerEvent(Long eventId, Long speakerId) {
        Session session = entityManager.unwrap(Session.class);
        Event event = session.get(Event.class, eventId);
        Speaker speaker = session.get(Speaker.class, speakerId);
        event.getSpeakers().add(speaker);
        speaker.getEvents().add(event);
        session.save(event);
    }
}
