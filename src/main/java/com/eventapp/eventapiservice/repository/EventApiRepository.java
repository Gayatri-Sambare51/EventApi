package com.eventapp.eventapiservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eventapp.eventapiservice.model.EventApi;

public interface EventApiRepository extends JpaRepository<EventApi, Long> {
	List<EventApi> findByName(String name);
    List<EventApi> findByCity(String city);
    List<EventApi> findByCategory(String category);
    List<EventApi> findByDateTimeAfter(LocalDateTime dateTime);
    @Query("SELECT e FROM EventApi e WHERE e.dateTime >= :startDate AND e.dateTime < :endDate")
    List<EventApi> findByDateTime(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}