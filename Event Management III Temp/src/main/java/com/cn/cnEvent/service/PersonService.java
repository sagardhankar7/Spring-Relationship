package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.PersonDAL;
import com.cn.cnEvent.entity.Person;
import com.cn.cnEvent.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonDAL personDAL;

    public Person getById(Long id) {
        Person person= personDAL.getById(id);
        if (person==null) throw new NotFoundException("No person found");
        return person;
    }

    public List<Person> getAll() {
        List<Person> list= personDAL.getAllPerson();
        if (list == null) throw new NotFoundException("No list of person");
        return list;
    }
}
