//package com.example.truecapp3.services;
//
//import com.example.truecapp3.errors.ServiceError;
//import com.example.truecapp3.models.Object;
//import com.example.truecapp3.repositories.ObjectRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ObjectService {
//  @Autowired
//  private UserService us;
//
//  @Autowired
//  private ObjectRepository pr;
//
//  @Autowired
//  private PhotoService fs;
//
//  @Autowired
//  private CategoryService cs;
//
//  public Object consultarProductoId(String ID) throws ServiceError {
//
//    Optional<Object> respuesta = pr.findById(ID);
//    if (respuesta.isPresent()) {
//
//      Object producto = respuesta.get();
//      return producto;
//
//    } else {
//
//      throw new ServiceError("Ese Producto no existe");
//
//    }
//
//  }
//
//  public void validar(String titulo, String descripcion, Condicion condicion, String categoriaID) throws ServiceError {
//
//    if (titulo == null || titulo.isEmpty()) {
//      throw new ServiceError("El titulo de la publicación es un campo obligatorio");
//    }
//
//    if (descripcion == null || descripcion.isEmpty()) {
//      throw new ServiceError("La descripción de la publicación es un campo obligatorio");
//    }
//
//    if (condicion == null) {
//      throw new ServiceError("La condición del producto es un campo obligatorio");
//    }
//
//    if (categoriaID == null) {
//      throw new ServiceError("La categoría del producto es un campo obligatorio");
//    }
//
//  }
//
//  @Transactional
//  public Object agregarProducto(String idUsuario, Condicion condicion, String categoriaID, List<MultipartFile> archivos, String titulo, String descripcion, Boolean dot) throws ServiceError {
//
//    try {
//
//      validar(titulo, descripcion, condicion, categoriaID);
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
//        validar(titulo, descripcion, condicion, categoriaID);
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
//}
