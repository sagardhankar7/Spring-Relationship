package com.cn.cnEvent.controller;

import com.cn.cnEvent.entity.EventScheduleDetail;
import com.cn.cnEvent.service.EventScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventScheduleDetail")
public class EventScheduleDetailController {
    @Autowired
    EventScheduleDetailService esDetailService;

    @GetMapping("/{id}")
    public EventScheduleDetail getById(@PathVariable Long id) {
        return esDetailService.getById(id);
    }

    @GetMapping("/all")
    public List<EventScheduleDetail> getAll() {
        return esDetailService.getAll();
    }

    @PostMapping("/save")
    public String save(@RequestBody EventScheduleDetail e) {
        //service return string "saved successfully"
        return esDetailService.save(e);
    }


}
