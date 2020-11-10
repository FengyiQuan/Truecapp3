package com.example.truecapp3.controllers;


import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.Photo;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.ObjectRepository;
import com.example.truecapp3.services.ObjectService;
import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PhotoController {
  @Autowired
  UserService usuarioServicio;

  @Autowired
  ObjectRepository productoServicio;

  @Autowired
  ObjectService productoservicio;

  @GetMapping("fotoPerfil/{id}")
  public ResponseEntity<byte[]> fotoPerfil(@PathVariable String id) throws ServiceError {
    System.out.println(id);
    User usuario = usuarioServicio.getActiveUserById(id);

    Photo foto = usuario.getProfilePic();

    if (foto != null) {
      final HttpHeaders headers = new HttpHeaders();

      headers.setContentType(MediaType.IMAGE_JPEG);

      return new ResponseEntity<>(foto.getContent(), headers, HttpStatus.OK);

    }
    return null;
  }


  @GetMapping("fotoProducto/{id}")
  public ResponseEntity<byte[]> fotoProducto(@PathVariable String id) throws ServiceError {

    Object producto = productoServicio.getObjectById(id);

    Photo foto = producto.getPhoto().get(0);
    final HttpHeaders headers = new HttpHeaders();

    headers.setContentType(MediaType.IMAGE_JPEG);

    return new ResponseEntity<>(foto.getContent(), headers, HttpStatus.OK);
  }
//
//
//  @GetMapping("fotoObjeto/{id}")
//  public ResponseEntity<byte[]> fotoObjeto(@PathVariable String id) throws ServiceError {
//
//
//    Object producto = productoServicio.getObjectById(id);
//
//
//    Photo foto = producto.getPhoto().get(0);
//    final HttpHeaders headers = new HttpHeaders();
//
//    headers.setContentType(MediaType.IMAGE_JPEG);
//
//    return new ResponseEntity<>(foto.getContent(), headers, HttpStatus.OK);
//  }


}
