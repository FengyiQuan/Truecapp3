package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Area;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.Photo;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectRepository extends JpaRepository<Object, String> {
  @Query("SELECT c FROM Object c WHERE c.id = :id")
   Object getObjectById(@Param("id") String id);

  @Query("SELECT c FROM Object c ORDER BY dateCreated DESC")
  List<Object> getObjects(Pageable pageable);

  @Query("SELECT c FROM Object c WHERE c.title LIKE '%:title %' ORDER BY dateCreated DESC")
  List<Object> getObjects(Pageable pageable,@Param("title")String title);

  @Query("SELECT c FROM Object c WHERE c.area LIKE '%:area %' ORDER BY dateCreated DESC")
  List<Object> getObjects(Pageable pageable,@Param("area") Area area);

}
