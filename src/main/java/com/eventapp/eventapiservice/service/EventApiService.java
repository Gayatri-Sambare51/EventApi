package com.eventapp.eventapiservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventapp.eventapiservice.model.EventApi;

public interface EventApiService {
    List<EventApi> getAllEvents();
    EventApi getEventById(Long id);
    EventApi addEvent(EventApi event);
    EventApi updateEvent(Long id, EventApi event);
    boolean deleteEvent(Long id);
    List<EventApi> findEventsByCity(String city);
    List<EventApi> findEventsByCategory(String category);
    List<EventApi> findEventsByDate(String date);
	List<EventApi> findEventByName(String name);
    
}