package com.busschedule.web.repository;

import com.busschedule.web.models.Schedule;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findScheduleById(Long id);
    @Query("SELECT c FROM Schedule c where c.departureTime > :departureTime")
    List<Schedule> findSchedulesByDepartureTime(LocalDateTime departureTime);

    @Query("SELECT c FROM Schedule c where c.departureTime >= :departureTime and c.departureTime < :endOfDay")
    List<Schedule> findScheduleByTimeInterval(LocalDateTime departureTime, LocalDateTime endOfDay);

    @Query("SELECT c FROM Schedule c where c.departureTime < :departureTime")
    List<Schedule> findOverdueSchedule(LocalDateTime departureTime);

//    @Query("SELECT DISTINCT c FROM Schedule c " +
//            "JOIN Route r ON c.route " +
//            "JOIN RoutesStops rs ON r.routesStops " +
//            "JOIN Stop s ON rs.stop " +
//            "WHERE s.name = :stopName")
//    List<Schedule> findAllByStops(@Param("stopName") String stopName);

//    @Query("SELECT DISTINCT c FROM Schedule c " +
//            "JOIN Route r ON c.route " +
//            "JOIN RoutesStops rs on r.routesStops " +
//            "JOIN Stop s ON rs.stop " +
//            "WHERE s.name = :stopName and c.departureTime >= :departureTime and c.departureTime < :endOfDay")
//    List<Schedule> findAllByStopsAndTimeForSearch(@Param("stopName") String stopName,
//                                                 @Param("departureTime")LocalDateTime departureTime,
//                                                 @Param("endOfDay") LocalDateTime endOfDay);

    @Query(value = "SELECT DISTINCT c.id, c.accessibility, c.arrival_time, c.departure_time, c.price, c.seats, c.buses_id, c.routes_id FROM schedules c " +
                    "JOIN routes r ON c.routes_id = r.id " +
                    "JOIN routes_stops rs ON rs.route_id = r.id " +
                    "JOIN stops s ON rs.stop_id = s.id " +
                    "WHERE s.name = :stopName and rs.stop_number > 1 and c.departure_time >= :departureTime and c.departure_time < :endOfDay", nativeQuery = true)
    List<Schedule> findAllByStopsAndTimeForSearch(@Param("stopName") String stopName,
                                              @Param("departureTime")LocalDateTime departureTime,
                                              @Param("endOfDay") LocalDateTime endOfDay);

}
