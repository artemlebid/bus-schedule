package com.busschedule.web.repository;

import com.busschedule.web.models.RoutesStops;
import com.busschedule.web.models.RoutesStopsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoutesStopsRepository extends JpaRepository<RoutesStops, RoutesStopsId> {
    @Query("SELECT c FROM RoutesStops c ORDER BY c.route.name, c.stopNumber")
    List<RoutesStops> findAllSorted();
    @Query("SELECT c FROM RoutesStops c where c.route.id = :id ORDER BY c.price")
    List<RoutesStops> findAllByRouteId(@Param("id") Long id);
}
