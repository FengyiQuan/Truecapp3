package com.example.truecapp3.services;

import com.example.truecapp3.enums.ObjectCondition;
import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.repositories.ObjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public Object getObjectById(String ID) throws ServiceError {
    Optional<Object> optionalObject = or.findById(ID);
    if (optionalObject.isPresent()) {
      return optionalObject.get();
    } else {
      throw new ServiceError("Ese Producto no existe");
    }
  }

  private void validate(String title, String description, ObjectCondition condition, String categoryId) throws ServiceError {

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

  }

//  @Transactional
//  public Object agregarProducto(String idUsuario, Condicion condicion, String categoriaID, List<MultipartFile> archivos, String titulo, String descripcion, Boolean dot) throws ServiceError {
//
//    try {
//
//      validate(titulo, descripcion, condicion, categoriaID);
//
//      Producto producto = new Producto();
//      producto.setUsuario(us.consultarUsuarioID(idUsuario));
//      producto.setCondicion(condicion);
////            producto.setCategoria(cs.consultarCategoriaId(categoriaID);
//      producto.setTitulo(titulo);
//
//      List<Foto> fotos = new ArrayList();
//      for (MultipartFile obj : archivos) {
//        Foto foto = fs.Guardar(obj);
//        fotos.add(foto);
//      }
//      producto.setFotos(fotos);
//
//      producto.setDescripcion(descripcion);
//      producto.setDot(dot);
//      producto.setFecha1(new Date());
//
//      pr.save(producto);
//      us.cargarProducto(idUsuario, producto.getId());
//      return producto;
//
//    } catch (ServiceError e) {
//
//      throw new ServiceError("Operación Interrumpida");
//
//    }
//
//  }
//
//  @Transactional
//  public Producto modificarProducto(String idUsuario, String idProducto, Condicion condicion, String categoriaID, List<MultipartFile> archivos, String titulo, String descripcion, Boolean dot) throws ErrorServicio {
//
//    try {
//
//      Producto producto = consultarProductoId(idProducto);
//
//      if (producto.getUsuario().equals(us.consultarUsuarioID(idUsuario))) {
//
//        validate(titulo, descripcion, condicion, categoriaID);
//
//        producto.setCondicion(condicion);
////                producto.setCategoria(cs.consultarCategoriaId(categoriaID);
//        producto.setTitulo(titulo);
//
//        List<Foto> fotos = new ArrayList();
//        for (MultipartFile obj : archivos) {
//          Foto foto = fs.Guardar(obj);
//          fotos.add(foto);
//        }
//        producto.setFotos(fotos);
//
//        producto.setDescripcion(descripcion);
//        producto.setDot(dot);
//        producto.setFecha1(new Date());
//
//        pr.save(producto);
//        us.cargarProducto(idUsuario, producto.getId());
//        return producto;
//
//      } else {
//
//        throw new ErrorServicio("No Tienes Los Permisos Para Realizar Esa Acción");
//
//      }
//
//    } catch (ErrorServicio e) {
//
//      throw new ErrorServicio("Operación Interrumpida");
//
//    }
//
//  }
//
//  @Transactional
//  public Producto bajarProducto(String idUsuario, String idProducto) throws ErrorServicio {
//
//    try {
//
//      Producto producto = consultarProductoId(idProducto);
//
//      if (producto.getUsuario().equals(us.consultarUsuarioID(idUsuario))) {
//
//        producto.setFecha2(new Date());
//        pr.save(producto);
//        us.cargarProducto(idUsuario, idProducto);
//        return producto;
//
//      } else {
//
//        throw new ErrorServicio("No Tienes Los Permisos Para Realizar Esa Acción");
//
//      }
//
//    } catch (ErrorServicio e) {
//
//      throw new ErrorServicio("Operación Interrumpida");
//
//    }
//
//  }
//
//  @Transactional
//  public Producto rehabilitar(String idUsuario, String idProducto) throws ErrorServicio {
//
//    try {
//
//      Producto producto = consultarProductoId(idProducto);
//
//      if (producto.getUsuario().equals(us.consultarUsuarioID(idUsuario))) {
//
//        producto.setFecha2(null);
//        pr.save(producto);
//        us.cargarProducto(idUsuario, idProducto);
//        return producto;
//
//      } else {
//
//        throw new ErrorServicio("No Tienes Los Permisos Para Realizar Esa Acción");
//
//      }
//
//    } catch (ErrorServicio e) {
//
//      throw new ErrorServicio("Operación Interrumpida");
//
//    }
//
//  }
//
//  public List<Producto> listarCuantos(String numero) throws ErrorServicio {
//
//
//    List<Producto> productos = pr.listarCuantos(new Pageable());
//
//    return productos;
//
//  }
//
//  public List<Producto> listarPorTítulo(String titulo) throws ErrorServicio {
//
//    List<Producto> productos = pr.listartActivos(titulo);
//
//    return productos;
//
//  }
}
