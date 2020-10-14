package com.example.truecapp3.services;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Photo;
import com.example.truecapp3.repositories.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PhotoService {
  @Autowired
  private PhotoRepository photoRepository;

  @Transactional
  public Photo save(MultipartFile archivo) throws ServiceError {
    if (archivo != null) {

      try {
        Photo foto = new Photo();
        foto.setMime(archivo.getContentType());
        foto.setName(archivo.getOriginalFilename());
        foto.setContent(archivo.getBytes());
        return photoRepository.save(foto);
      } catch (IOException ex) {
        Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex);
      }

    }
    return null;

  }

  @Transactional
  public Photo update(String idFoto, MultipartFile archivo) throws ServiceError {
    if (archivo != null) {

      try {
        Photo foto = new Photo();
        if (idFoto != null) {
          Optional<Photo> respuesta = photoRepository.findById(idFoto);
          if (respuesta.isPresent()) {
            foto = respuesta.get();
          }
        }
        foto.setMime(archivo.getContentType());
        foto.setName(archivo.getOriginalFilename());
        foto.setContent(archivo.getBytes());
        return photoRepository.save(foto);

      } catch (IOException ex) {
        Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex);
      }

    }
    return null;

  }
}
