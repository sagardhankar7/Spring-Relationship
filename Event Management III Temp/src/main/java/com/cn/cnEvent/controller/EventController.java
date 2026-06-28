package com.cn.cnEvent.controller;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.entity.Ticket;
import com.cn.cnEvent.service.EventScheduleDetailService;
import com.cn.cnEvent.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
	@Autowired
	EventService eventService;

//	Fix: Utilize OneToOne Mapping
//	@Autowired
//	EventScheduleDetailService eventScheduleDetailService;

	@GetMapping("/{id}")
	public Event getEventById(@PathVariable Long id)
	{
		return eventService.getEventById(id);
	}
	@GetMapping("/all")
	public List<Event> getAllEvents()
	{
		return eventService.getAllEvents();
	}

	@PostMapping("/save")
	//todo how to add ticket save logic
	public String saveEvent(@RequestBody Event event) {
		return eventService.saveEvent(event);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteEvent(@PathVariable Long id)
	{
		return eventService.delete(id);
	}

	@PutMapping("/update")
	public String updateEvent(@RequestBody Event updateEvent)
	{
		return eventService.update(updateEvent);
	}

	@GetMapping("/eventScheduleDetail/{id}") //this id is -> event id
	public EventScheduleDetail getDetails(@PathVariable Long id) {
//		return eventScheduleDetailService.getById(id);
		return eventService.getEventDetailByEventId(id);
	}

	@DeleteMapping("/delete/eventScheduleDetail/{id}")
	public String deleteDetails(@PathVariable Long id) {
		return eventService.deleteEventDetails(id);
//		eventScheduleDetailService.delete(id);
	}

	@GetMapping("/location/{location}")
	public List<Event> allEventsByLocation(@PathVariable String location) {
		return eventService.getEventsByLocation(location);
//		List<Event> list = eventScheduleDetailService.getByLocation(location);
//		return list;
	}

	@GetMapping("/allTickets/{eventId}")
	public List<Ticket> getAllTicket(@PathVariable Long eventId) {
//		todo whynot: return getEventById(eventId).getTickets()
		return eventService.getAllAssociatedTickets(eventId);
	}

	@GetMapping("/tickets/PriceGreaterThan/{price}")
	public List<Event> filterEventByTicketPrice(@PathVariable Long price) {
//		List<Event> list = getAllEvents();
		return eventService.filteredEventByTicketPriceHigher(price);
	}
}
