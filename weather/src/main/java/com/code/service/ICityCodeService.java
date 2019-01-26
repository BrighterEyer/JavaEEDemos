package com.code.service;

import com.code.repository.ICityCodeRepository;
import com.code.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICityCodeService extends BaseService {

    @Autowired
    ICityCodeRepository cityCodeRepository;

    public String getCodeByCity(String cityName) {
        return cityCodeRepository.findByCityName(cityName);
    }
}
