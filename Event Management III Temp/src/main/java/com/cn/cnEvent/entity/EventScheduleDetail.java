package com.cn.cnEvent.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EventScheduleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    LocalDateTime startTime;
    @Column
    LocalDateTime endTime;
    @Column
    String location;

    //todo: why not bidirectional
//    @OneToOne(mappedBy = "eventScheduleDetail")
//    Event event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }
}
