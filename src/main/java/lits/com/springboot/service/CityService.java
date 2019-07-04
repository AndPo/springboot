package lits.com.springboot.service;

import lits.com.springboot.dto.CityDto;

public interface CityService {

    CityDto findOne(Long id);

    CityDto save(CityDto cityDto);

    CityDto update(CityDto cityDto);

    void delete(Long id);

    CityDto findByName(String name);
}
