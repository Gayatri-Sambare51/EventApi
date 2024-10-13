package com.eventapp.eventapiservice.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventapp.eventapiservice.model.EventApi;
import com.eventapp.eventapiservice.service.EventApiService;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventApiController {

    private final EventApiService eventApiService;

    public EventApiController(EventApiService eventApiService) {
        this.eventApiService = eventApiService;
    }

    @GetMapping
    public ResponseEntity<List<EventApi>> getAllEvents() {
        List<EventApi> events = eventApiService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventApi> getEventById(@PathVariable Long id) {
        EventApi eventApi = eventApiService.getEventById(id);
        if (eventApi != null) {
            return new ResponseEntity<>(eventApi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addEvent(@RequestBody EventApi eventApi) {
    	eventApiService.addEvent(eventApi);
        return new ResponseEntity<>("Event added successfully!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Long id, @RequestBody EventApi eventApi) {
        if (eventApiService.updateEvent(id, eventApi) != null) {
            return new ResponseEntity<>("Event updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event not found!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        if (eventApiService.deleteEvent(id)) {
            return new ResponseEntity<>("Event deleted successfully!", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Event not found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> findEventsByCategory(@PathVariable String category) {
        List<EventApi> events = eventApiService.findEventsByCategory(category);
        if (events.isEmpty()) {
            return new ResponseEntity<>("No events found in category: " + category, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<?> findEventsByCity(@PathVariable String city) {
        List<EventApi> events = eventApiService.findEventsByCity(city);
        if (events.isEmpty()) {
            return new ResponseEntity<>("No events found in city: " + city, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    
    @GetMapping("/date/{date}")
    public ResponseEntity<?> findEventsByDate(@PathVariable String date) {
        List<EventApi> events = eventApiService.findEventsByDate(date);
        if (events.isEmpty()) {
            return new ResponseEntity<>("No events found on date: " + date, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findEventByName(@PathVariable String name) {
        List<EventApi> events = eventApiService.findEventByName(name);
        if (events.isEmpty()) {
            return new ResponseEntity<>("No events found on name: " + name, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}