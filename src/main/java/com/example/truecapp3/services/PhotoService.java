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

  public Photo getPhotoById(String id) {
    Optional<Photo> optionalPhoto = photoRepository.findById(id);
    return optionalPhoto.orElse(null);
  }

  @Transactional
  public Photo save(MultipartFile pic) throws ServiceError {
    if (pic != null) {
      String type = pic.getContentType();
      if ("image/png".equals(type) || "image/jpeg".equals(type) || "image/jpg".equals(type)) {
        try {
          Photo photo = new Photo();
          photo.setMime(pic.getContentType());
          photo.setName(pic.getOriginalFilename());
          photo.setContent(pic.getBytes());
          return photoRepository.save(photo);
        } catch (IOException ex) {
          Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println(ex);
        }
      } else {
        throw new ServiceError("File type does not supported.");
      }
    }
    throw new ServiceError("Uploading file fails.");
  }

  @Transactional
  public Photo update(String photoId, MultipartFile newPic) throws ServiceError {
    if (newPic != null) {

      String type = newPic.getContentType();
      if ("image/png".equals(type) || "image/jpeg".equals(type) || "image/jpg".equals(type)) {
        try {
          Photo photo = new Photo();
          if (photoId != null) {
            Optional<Photo> optionalPhoto = photoRepository.findById(photoId);
            if (optionalPhoto.isPresent()) {
              photo = optionalPhoto.get();
            }
          }
          photo.setMime(newPic.getContentType());
          photo.setName(newPic.getOriginalFilename());
          photo.setContent(newPic.getBytes());
          return photoRepository.save(photo);

        } catch (IOException ex) {
          Logger.getLogger(PhotoService.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println(ex);
        }
      } else {
        throw new ServiceError("File type does not supported.");
      }
    }
    throw new ServiceError("Updating file fails.");
  }

  @Transactional
  public void deleteById(String photoId) {
    photoRepository.deleteById(photoId);
  }
}
