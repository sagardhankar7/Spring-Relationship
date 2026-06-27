package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.EventScheduleDetailDAL;
import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventScheduleDetailService {
    @Autowired
    EventScheduleDetailDAL eventScheduleDetailDAL;

    @Transactional
    public EventScheduleDetail getById(Long id) {
        EventScheduleDetail eDetail = eventScheduleDetailDAL.getById(id);
        if (eDetail==null) throw new NotFoundException("E Details not found");
        return eDetail;
    }

    @Transactional
    public List<EventScheduleDetail> getAll() {
        List<EventScheduleDetail> list = eventScheduleDetailDAL.getAll();
        if (list.isEmpty()) throw new NotFoundException("No Detials found");
        return list;
    }

    @Transactional
    public String save(EventScheduleDetail e) {
        EventScheduleDetail eDetail = eventScheduleDetailDAL.getById(e.getId());
        if (eDetail!=null) throw new ElementAlreadyExistException("EventDetail already present");
        return eventScheduleDetailDAL.save(e);
    }

    @Transactional
    public void delete(Long id) {
        eventScheduleDetailDAL.delete(id);
    }

    public List<Event> getByLocation(String location) {
//        eventScheduleDetailDAL
        return List.of();
    }
}
