package com.cn.cnEvent.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Event {
	@OneToOne(cascade = CascadeType.ALL)
	EventScheduleDetail eventScheduleDetail;

	public EventScheduleDetail getEventScheduleDetail() {
		return eventScheduleDetail;
	}

	public void setEventScheduleDetail(EventScheduleDetail eventScheduleDetail) {
		this.eventScheduleDetail = eventScheduleDetail;
	}

	@OneToMany(cascade = CascadeType.ALL)
    List<Ticket> ticket;

	public List<Ticket> getTickets() {
		return ticket;
	}

	public void setTickets(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
