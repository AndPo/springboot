package lits.com.springboot.service.impl;

import lits.com.springboot.dto.CityDto;
import lits.com.springboot.exception.CityNotFoundException;
import lits.com.springboot.model.City;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service(value = "cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CityDto findOne(Long id) {
        CityDto cityDto = Optional.ofNullable(cityRepository.findById(id))
                .map(e -> modelMapper.map(e, CityDto.class))
                .orElseThrow(() -> new CityNotFoundException("City with id: " + id + " not found"));
        return cityDto;
    }

    @Override
    public CityDto findByName(String name) {
        City city = cityRepository.findByName(name)
                .orElseThrow(() -> new CityNotFoundException("City with name: " + name + " not found"));

        return modelMapper.map(city, CityDto.class);
    }


    @Override
    public CityDto save(CityDto cityDto) {

        CityDto resultCityDto = Optional.ofNullable(cityDto)
                .map(e -> modelMapper.map(e, City.class))
                .map(e -> cityRepository.save(e))
                .map(e -> modelMapper.map(e, CityDto.class))
                .orElseThrow(() -> new CityNotFoundException("CityDtois null"));

        return resultCityDto;
    }

    @Override
    public CityDto update(CityDto cityDto) {

        // Name can't repeat in other
        CityDto resultCityDto = cityRepository.findByName(cityDto.getName())
                .map(e -> e.setDescription(cityDto.getDescription()))
                .map(e -> cityRepository.save(e))
                .map(e -> modelMapper.map(e, CityDto.class))
                .orElseThrow(() -> new CityNotFoundException("City with name: " + cityDto.getName() + " not found"));

        return resultCityDto;
    }

    @Override
    public void delete(Long id) {
        log.info("attempting to deleteById City Object from database");
        cityRepository.deleteById(id);
    }

/*    @Override
    public CityDto findByName(String name) {

        CityDto cityDto = Optional.ofNullable(cityRepository.findByName(name))
                .map(e -> modelMapper.map(e, CityDto.class))
                .orElse(new CityDto());

        if (cityDto.equals(new CityDto())) {
            log.warn("Got null or empty City Object from repository");
        } else {
            log.info("Got " + cityDto + " Object from repository");
        }

        return cityDto;
    }*/
}
