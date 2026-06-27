package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDALImpl implements EventDAL {

    @Autowired
    EntityManager entityManager;

    @Override
    public Event getById(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Event event = session.get(Event.class, id);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
        Session session = entityManager.unwrap(Session.class);
        List<Event> allEvents = session.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        return allEvents;
    }

    @Override
    public String save(Event event) {
        Session session = entityManager.unwrap(Session.class);
        session.save(event);
        return "The event was saved successfully.";
    }

    @Override
    public String delete(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Event event = session.get(Event.class, id);
        session.delete(event);
        return "The event was deleted successfully";
    }

    @Override
    public String update(Event updateEvent) {
        Session session = entityManager.unwrap(Session.class);
        Event currentEvent = session.get(Event.class, updateEvent.getId());
        currentEvent.setName(updateEvent.getName());
        currentEvent.setDescription(updateEvent.getDescription());
        session.update(currentEvent);
        return "Event is updated successfully";
    }

    @Override
    public List<Event> getEventsByLocation(String location) {
        List<Event> allEvents = getAllEvents();
        List<Event> result = new ArrayList<>();
        for (Event e: allEvents) {
            if (e.getEventScheduleDetail().getLocation().equalsIgnoreCase(location)) result.add(e);
        }

        return result;
    }

    @Override
    public String deleteEventDetails(Long id) {
        //todo
//        Event event = getById(id);
//        EventScheduleDetail eventScheduleDetail = event.getEventScheduleDetail();
//        event.setEventScheduleDetail(null);
//        save(event);
//
//        Session session = entityManager.unwrap(Session.class);
//        session.delete(eventScheduleDetail);
//        return "The event detail is successfully deleted";

//        New
//        Session session = entityManager.unwrap(Session.class);
//        EventScheduleDetail detail = session
//                .createQuery("select e from EventScheduleDetail e where e.id=:id", EventScheduleDetail.class)
//                .setParameter("id", id).getSingleResult();
//        for (Event event: getAllEvents()) {
//            if (event.getEventScheduleDetail() != null && event.getEventScheduleDetail().getId().equals(id)) {
//                event.setEventScheduleDetail(null);
//                save(event);
//            }
//        }
//        session.delete(detail);

        Session session = entityManager.unwrap(Session.class);
        EventScheduleDetail detail = session
                .createQuery("select detail from EventScheduleDetail detail where detail.id=:id", EventScheduleDetail.class)
                .setParameter("id", id)
                .getSingleResult();

        for (Event event: getAllEvents()) {
            if (event.getEventScheduleDetail().getId().equals(id)) {
                event.setEventScheduleDetail(null);
                save(event);
            }
        }

        session.delete(detail);
//        return "Event detail deleted successfully";
        return "The eventSchedule was deleted successfully";
    }

    @Override
    public EventScheduleDetail getEventDetailByEventId(Long id) {
        Event event = getById(id);
        return event.getEventScheduleDetail();
    }


}
