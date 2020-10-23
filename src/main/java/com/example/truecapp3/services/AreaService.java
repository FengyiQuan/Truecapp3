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

  // if it has already exist, return it; otherwise, create a new area
  @Transactional
  public Area createArea(String areaName, String provinceOrState) throws ServiceError {
    validate(areaName, provinceOrState);
    Area area = areaRepository.getAreaByAreaNameAndProvinceOrState(areaName, provinceOrState);
    if (area == null) {
      Area newArea = new Area();
      newArea.setAreaName(areaName);
      newArea.setProvinceOrState(provinceOrState);

      return areaRepository.save(newArea);
    }
    return area;

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
