package com.busschedule.web.service.impl;

import com.busschedule.web.dto.BusDto;
import com.busschedule.web.models.Bus;
import com.busschedule.web.repository.BusRepository;
import com.busschedule.web.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {
    private BusRepository busRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public void saveBus(BusDto busDto) {
        Bus bus = mapToBus(busDto);
        busRepository.save(bus);
    }

    @Override
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    @Override
    public List<BusDto> findAllBuses() {
        List<Bus> allBuses = busRepository.findAll();
        return allBuses.stream().map((bus -> mapToBusDto(bus))).collect(Collectors.toList());
    }

    @Override
    public BusDto findBusById(Long id) {
        Bus bus = new Bus();
        Optional<Bus> optionalBus = busRepository.findById(id);

        if(optionalBus.isPresent()){
            bus = optionalBus.get();
        }

        return mapToBusDto(bus);
    }

    private BusDto mapToBusDto(Bus bus){
        BusDto busDto = BusDto.builder()
                .id(bus.getId())
                .licensePlate(bus.getLicensePlate())
                .name(bus.getName())
                .company(bus.getCompany())
                .build();

        return busDto;
    }

    private Bus mapToBus(BusDto busDto){
        Bus bus = Bus.builder()
                .id(busDto.getId())
                .licensePlate(busDto.getLicensePlate())
                .name(busDto.getName())
                .company(busDto.getCompany())
                .build();
        return bus;
    }
}
