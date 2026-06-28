package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.SpeakerDAL;
import com.cn.cnEvent.entity.Speaker;
import com.cn.cnEvent.exception.ElementAlreadyExistException;
import com.cn.cnEvent.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpeakerService {

    @Autowired
    SpeakerDAL speakerDAL;

    @Transactional
    public Speaker getById(Long id) {
        Speaker speaker = speakerDAL.getById(id);
        if (speaker==null) throw new NotFoundException("No Speaker found");
        return speaker;
    }

    @Transactional
    public List<Speaker> getAllSpeaker() {
        List<Speaker> list = speakerDAL.getAllSpeaker();
        if (list==null) throw new NotFoundException("No Speaker found");
        return list;
    }

    @Transactional
    public List<Speaker> filterSpeaker(Long experience, Long eventCount) {
        List<Speaker> allSpeaker = getAllSpeaker();
        List<Speaker> result = new ArrayList<>();
        for (Speaker speaker: allSpeaker) {
            if (speaker.getEvents().size()>=eventCount && speaker.getExperience()>experience) result.add(speaker);
        }

        return result;
    }
    @Transactional
    public String save(Speaker speaker) {
        if (speakerDAL.getById(speaker.getId())!=null) throw new ElementAlreadyExistException("The speaker already exist.");
        return speakerDAL.save(speaker);
    }
    @Transactional
    public void saveSpeakerToEvent(Long eventId, Long speakerId) {
        speakerDAL.addSpeakerEvent(eventId, speakerId);
    }
}
