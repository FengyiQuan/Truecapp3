package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, String> {

  @Query("SELECT c FROM Area c WHERE c.id = :id")
  Area getAreaById(@Param("id") String id);

  @Query("SELECT c FROM Area c WHERE c.areaName = :areaName AND c.provinceOrState = :provinceOrState")
  Area getAreaByAreaNameAndProvinceOrState(@Param("areaName") String areaName, @Param("provinceOrState") String provinceOrState);

  @Query("SELECT c FROM Area c WHERE c.areaName LIKE :areaName")
  Area getAreaByAreaName(@Param("areaName") String areaName);


  @Query("SELECT c FROM Area c WHERE c.provinceOrState LIKE :provinceOrState")
  Area getAreaByProvinceOrState(@Param("provinceOrState") String provinceOrState);

}
