package com.cn.cnEvent.controller;

import com.cn.cnEvent.entity.Speaker;
import com.cn.cnEvent.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speaker")
public class SpeakerController {

    @Autowired
    SpeakerService speakerService;

    @GetMapping("/{id}")
    public Speaker getById(@PathVariable Long id) {
        return speakerService.getById(id);
    }
    @GetMapping("/all")
    public List<Speaker> getAllSpeaker() {
        return speakerService.getAllSpeaker();
    }

    @GetMapping("/eventCount/{eventCount}/experience/{experience}")
    public List<Speaker> filterSpeaker(@PathVariable Long experience, @PathVariable Long eventCount) {
        return speakerService.filterSpeaker(experience, eventCount);
    }

    @PostMapping("/save")
    public String save(@RequestBody Speaker speaker) {
        return speakerService.save(speaker);
    }

    @PostMapping("/id/{speakerId}/eventId/{eventId}")
    public void saveSpeakerEvent(@PathVariable("eventId") Long eventId, @PathVariable("speakerId") Long speakerId) {
        // todo in progress
        speakerService.saveSpeakerToEvent(eventId, speakerId);
    }
}
