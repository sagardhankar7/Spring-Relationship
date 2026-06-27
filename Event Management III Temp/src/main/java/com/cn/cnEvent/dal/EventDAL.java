package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;

import java.util.List;

public interface EventDAL {

	Event getById(Long id);

	List<Event> getAllEvents();

	String save(Event item);

	String delete(Long id);

	String update(Event updateEvent);

	List<Event> getEventsByLocation(String location);

	EventScheduleDetail getEventDetailByEventId(Long id);

	String deleteEventDetails(Long id);

//	EventScheduleDetail
}
