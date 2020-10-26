package com.example.truecapp3.services;

import com.example.truecapp3.enums.ObjectCondition;
import com.example.truecapp3.enums.ObjectType;
import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Area;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.Photo;
import com.example.truecapp3.repositories.ObjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectService {
  @Autowired
  private ObjectRepository or;
  @Autowired
  private UserService us;
  @Autowired
  private PhotoService ps;
  @Autowired
  private CategoryService cs;
  @Autowired
  private AreaService as;

  public Object getObjectById(String ID) throws ServiceError {
    Optional<Object> optionalObject = or.findById(ID);
    if (optionalObject.isPresent()) {
      return optionalObject.get();
    } else {
      throw new ServiceError("Ese Producto no existe");
    }
  }

  private void validate(String userId, String title, String description, ObjectCondition condition,
                        String categoryId, ObjectType objectType, String areaId) throws ServiceError {
    if (userId == null || userId.isEmpty()) {
      throw new ServiceError("Invalid user id.");
    }
    if (title == null || title.isEmpty()) {
      throw new ServiceError("El titulo de la publicación es un campo obligatorio");
    }

    if (description == null || description.isEmpty()) {
      throw new ServiceError("La descripción de la publicación es un campo obligatorio");
    }

    if (condition == null) {
      throw new ServiceError("La condición del producto es un campo obligatorio");
    }

    if (categoryId == null) {
      throw new ServiceError("La categoría del producto es un campo obligatorio");
    }
    if (objectType == null) {
      throw new ServiceError("object type cannot be null.");
    }
    if (areaId == null || areaId.isEmpty()) {
      throw new ServiceError("invalid area id.");
    }
  }

  @Transactional
  public Object addObject(String userId, ObjectCondition condition, String categoryId, List<MultipartFile> files,
                          String title, String description, ObjectType objectType, String areaId) throws ServiceError {
    try {
      validate(userId, title, description, condition, categoryId, objectType, areaId);

      Object newObject = new Object();
      newObject.setDescription(description);
      newObject.setCategory(cs.getCategoryById(categoryId));
      newObject.setObjectCondition(condition);
      newObject.setProductArea(as.getAreaById(areaId));
      newObject.setObjectCondition(condition);
      newObject.setTitle(title);
      List<Photo> photos = new ArrayList<>();
      for (MultipartFile obj : files) {
        Photo photo = ps.save(obj);
        photos.add(photo);
      }
      newObject.setPhoto(photos);

      Object object = or.save(newObject);
      us.bindObject(userId, object.getId());
      return object;
    } catch (ServiceError e) {
      throw new ServiceError("Operación Interrumpida");
    }
  }


  @Transactional
  public Object modifyObject(String objectId, String userId, ObjectCondition condition, String categoryId,
                             List<MultipartFile> files, String title, String description, ObjectType objectType,
                             String areaId) throws ServiceError {
    if (objectId == null || objectId.isEmpty()) {
      throw new ServiceError("invalid objectId.");
    }
    try {
      Object object = getObjectById(objectId);
      if (object.getOwner().equals(us.getActiveUserById(userId))) {
        return addObject(userId, condition, categoryId, files, title, description, objectType, areaId);
      } else {
        throw new ServiceError("No Tienes Los Permisos Para Realizar Esa Acción");
      }
    } catch (ServiceError e) {
      throw new ServiceError("Operación Interrumpida");
    }
  }

  @Transactional
  public void deleteById(String objectId, String userId) throws ServiceError {
    try {
      Object object = getObjectById(objectId);
      if (object.getOwner().equals(us.getActiveUserById(userId))) {
        or.deleteById(objectId);
      } else {
        throw new ServiceError("No Tienes Los Permisos Para Realizar Esa Acción");
      }
    } catch (ServiceError e) {
      throw new ServiceError("Operación Interrumpida");
    }
  }

  public List<Object> listObjects() {
    return or.getObjects(new Pageable());
  }

  public List<Object> listByTitle(String title) throws ServiceError {
    if (title == null || title.isEmpty()) {
      return or.getObjects(new Pageable(), title);
    } else {
      throw new ServiceError("invalid argument.");
    }

  }

  public List<Object> listByArea(Area area) throws ServiceError {
    if (area != null) {
      return or.getObjects(new Pageable(), area);
    } else {
      throw new ServiceError("invalid argument.");
    }
  }
}
