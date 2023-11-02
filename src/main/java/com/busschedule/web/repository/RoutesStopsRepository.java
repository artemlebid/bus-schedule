package com.busschedule.web.repository;

import com.busschedule.web.models.RoutesStops;
import com.busschedule.web.models.RoutesStopsId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface RoutesStopsRepository extends JpaRepository<RoutesStops, RoutesStopsId> {
    @Query("SELECT c FROM RoutesStops c ORDER BY c.route.name, c.stopNumber")
    List<RoutesStops> findAllSorted();

    @Query("SELECT c FROM RoutesStops c WHERE c.id.routeId =:routeId AND c.id.stopId = :stopId")
    RoutesStops findByRouteIdAndStopId(@Param("routeId") Long routeId, @Param("stopId") Long stopId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO routes_stops " +
            "(arrival_time, departure_time, price, stop_number, stop_id, route_id) " +
            "VALUES (:arrival_time, :departure_time, :price, :stop_number, :stop_id, :route_id)", nativeQuery = true)
    void customSave(@Param("arrival_time")LocalTime arrivalTime,
                    @Param("departure_time")LocalTime departureTime,
                    @Param("price") Double price,
                    @Param("stop_number") Integer stopNumber,
                    @Param("stop_id") Long stopId,
                    @Param("route_id")Long routeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE routes_stops " +
            "SET arrival_time = :arrival_time, " +
            "departure_time = :departure_time, " +
            "price = :price, " +
            "stop_number = :stop_number " +
            "WHERE stop_id = :stop_id AND route_id = :route_id", nativeQuery = true)
    void customUpdate(@Param("arrival_time")LocalTime arrivalTime,
                      @Param("departure_time")LocalTime departureTime,
                      @Param("price") Double price,
                      @Param("stop_number") Integer stopNumber,
                      @Param("stop_id") Long stopId,
                      @Param("route_id")Long routeId);

}
