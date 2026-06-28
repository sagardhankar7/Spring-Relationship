package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Speaker;

import java.util.List;

public interface SpeakerDAL {
    List<Speaker> getAllSpeaker();

    Speaker getById(Long id);

    String save(Speaker speaker);

    void addSpeakerEvent(Long eventId, Long speakerId);
}
