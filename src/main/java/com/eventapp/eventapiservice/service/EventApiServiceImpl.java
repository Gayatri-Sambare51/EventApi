package com.eventapp.eventapiservice.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventapp.eventapiservice.model.EventApi;
import com.eventapp.eventapiservice.repository.EventApiRepository;

@Service
public class EventApiServiceImpl implements EventApiService {

    @Autowired
    private EventApiRepository eventRepository;

    @Override
    public List<EventApi> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public EventApi getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }


    @Override
    public boolean deleteEvent(Long id) {
        Optional<EventApi> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            eventRepository.delete(eventOptional.get());
            return true; // Event was found and deleted
        }
        return false; // Event not found
    }

    @Override
    public List<EventApi> findEventsByCity(String city) {
        return eventRepository.findByCity(city);
    }

    @Override
    public List<EventApi> findEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }

    // Implement in EventApiServiceImpl
    @Override
    public List<EventApi> findEventsByDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        return eventRepository.findByDateTime(localDate.atStartOfDay(), localDate.plusDays(1).atStartOfDay());
    }

	@Override
	public EventApi addEvent(EventApi eventApi) {
		return eventRepository.save(eventApi);
	}

	@Override
	public EventApi updateEvent(Long id, EventApi eventApi) {
		Optional<EventApi> existingEvent = eventRepository.findById(id);
        if (!existingEvent.isPresent()) {
            // Returning null if the event is not found
            return null;
        }
        eventApi.setId(id);
        return eventRepository.save(eventApi);
	}

	@Override
	public List<EventApi> findEventByName(String name) {
		return eventRepository.findByName(name);
	}
	
	
}
