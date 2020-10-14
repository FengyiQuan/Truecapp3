package com.example.truecapp3.services;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Area;
import com.example.truecapp3.repositories.AreaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaService {
  @Autowired
  AreaRepository areaRepository;

  @Transactional
  public void createArea(String areaName, String provinceOrState) throws ServiceError {

    validate(areaName, provinceOrState);

    Area area = new Area();
    area.setAreaName(areaName);
    area.setProvinceOrState(provinceOrState);

    areaRepository.save(area);
  }


  private void validate(String areaName, String provinceOrState) throws ServiceError {
    if (areaName == null || areaName.isEmpty()) {
      throw new ServiceError("El nombre de la zona/departamento no puede ser nulo o vacio.");
    }

    if (provinceOrState == null) {
      throw new ServiceError("La provincia no puede ser nulo o vacio.");
    }
  }
}
