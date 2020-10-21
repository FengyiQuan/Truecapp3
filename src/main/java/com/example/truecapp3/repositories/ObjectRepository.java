package com.example.truecapp3.repositories;

import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.Photo;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectRepository extends JpaRepository<Object, String> {
  @Query("SELECT c FROM Object c WHERE c.id = :id")
   Object getObjectById(@Param("id") String id);

  @Query("SELECT c FROM Object c WHERE c.id = : id ORDER BY dateCreated DESC")
  List<java.lang.Object> getObjectsById(Pageable pageable, @Param("id") String id);

}
