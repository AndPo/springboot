package lits.com.springboot.service.impl;

import lits.com.springboot.dto.CityDto;
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
        return Optional.ofNullable(cityRepository.findOne(id))
                .map(e -> modelMapper.map(e, CityDto.class))
                .orElse(new CityDto());
    }

    @Override
    public CityDto save(CityDto cityDto) {
        return Optional.ofNullable(cityDto)
                .map(e -> modelMapper.map(e, City.class))
                .map(e -> cityRepository.save(e))
                .map(e -> modelMapper.map(e, CityDto.class))
                .orElse(new CityDto());
    }

    @Override
    public CityDto update(CityDto cityDto) {
        // Name can't repeat in other
        return Optional.ofNullable(cityRepository.findByName(cityDto.getName()))
                .map(e -> e.setDescription(cityDto.getDescription()))
                .map(e -> cityRepository.save(e))
                .map(e -> modelMapper.map(e, CityDto.class))
                .orElse(new CityDto());
    }

    @Override
    public void delete(Long id) {
        cityRepository.delete(id);
    }

    @Override
    public CityDto findByName(String name) {
        return Optional.ofNullable(cityRepository.findByName(name))
                .map(e -> modelMapper.map(e, CityDto.class))
                .orElse(new CityDto());
    }
}
