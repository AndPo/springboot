package lits.com.springboot.service.impl;

import lits.com.springboot.dto.CityDto;
import lits.com.springboot.model.City;
import lits.com.springboot.repository.CityRepository;
import lits.com.springboot.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "cityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CityDto findOne(Long id) {
        City city= cityRepository.findOne(id);
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public CityDto save(CityDto cityDto) {
        City city = cityRepository.save(modelMapper.map(cityDto, City.class));
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public CityDto update(CityDto cityDto) {
        City city = cityRepository.findByName(cityDto.getName());
        //TODO complete this method if in City Entity
        //change fields our Entity
        //cityRepository.save(city);
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public void delete(Long id) {
        cityRepository.delete(id);
    }

    @Override
    public CityDto findByName(String name) {
        return null;
    }
}
