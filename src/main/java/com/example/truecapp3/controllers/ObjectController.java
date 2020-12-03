package com.example.truecapp3.controllers;

import com.example.truecapp3.enums.ObjectCondition;
import com.example.truecapp3.enums.ObjectType;
import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Area;
import com.example.truecapp3.models.Category;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.UserRepository;
import com.example.truecapp3.services.CategoryService;
import com.example.truecapp3.services.CreditService;
import com.example.truecapp3.services.ObjectService;
import com.example.truecapp3.services.PhotoService;
import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ObjectController {
  @Autowired
  UserService usuarioServicio;
  @Autowired
  CategoryService categoryService;

  @Autowired
  CreditService creditoServicio;


  @Autowired
  PhotoService fotoServicio;

  @Autowired
  ObjectService productoServicio;

  @Autowired
  UserRepository usuariorepositorio;


  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @PostMapping("/registrarProducto")
  public String registrarProducto(HttpSession session, ModelMap modelo,
                                  @RequestParam String condicion,
                                  @RequestParam String type,
                                  @RequestParam(required = false) List<MultipartFile> archivos,
                                  @RequestParam String titulo,
                                  @RequestParam String descripcion,
                                  @RequestParam String categoria) {

    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {

      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getActiveUserByEmail(username);
      String idUsuario = usuario.getId();

      try {

        Area area = usuario.getArea();
        Category category = categoryService.createCategory(categoria);
        String categoriaID = category.getId();
        ObjectType objectType = ObjectType.PRODUCT;
        switch (type) {
          case "service":
            objectType = ObjectType.SERVICE;
            break;
          case "product":
            objectType = ObjectType.PRODUCT;
            break;
          default:
        }
        ObjectCondition objectCondition = ObjectCondition.WITH_DETAILS;
        switch (condicion) {
          case "new":
            objectCondition = ObjectCondition.NEW;
            break;
          case "like new":
            objectCondition = ObjectCondition.OPEN_BOX;
            break;
          case "used":
            objectCondition = ObjectCondition.USED;
            break;
          default:
        }

        Object producto = productoServicio.addObject(idUsuario, objectCondition, categoriaID, archivos, titulo, descripcion, objectType, area);
        addDetails(modelo,usuario);
      } catch (Exception ex) {

        Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
        modelo.put("Error", ex.getMessage());
        //            modelo.put("Tipo", tipo);
        modelo.put("Fotos", archivos);
        modelo.put("Título", titulo);
        modelo.put("Descripción", descripcion);
        //            modelo.put("Donación O Trueque", "Trueque");

        return "user_nuevoTrueque";

      }

    }
    return "user_home";

  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @PostMapping("/modificarProducto")
  public String modificarProducto(HttpSession session, ModelMap modelo, @RequestParam String idProducto, @RequestParam List<MultipartFile> archivos, @RequestParam String titulo, @RequestParam String descripcion) {

    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getActiveUserByEmail(username);
      String idUsuario = usuario.getId();

      try {

        ObjectCondition condicion = ObjectCondition.NEW;
        ObjectType type = ObjectType.PRODUCT;
        Area area = usuario.getArea();
        String categoriaID = "0";
        Object producto = productoServicio.modifyObject(idUsuario, idProducto, condicion, categoriaID, archivos, titulo, descripcion, type, area);
//                usuarioServicio.cargarProducto(idUsuario, producto.getId());

      } catch (Exception ex) {

        Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
        modelo.put("Error ", ex.getMessage());
//                modelo.put("Condición ", condicion);
//                modelo.put("Categoría ", categoriaID);
        modelo.put("Fotos", archivos);
        modelo.put("Título", titulo);
        modelo.put("Descripción", descripcion);
//                modelo.put("Donación O Trueque", dot);
        addDetails(modelo,usuario);
        return "user_home";

      }

    }

    return "user_home";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @PostMapping("/bajarProducto")
  public String bajarProducto(HttpSession session, ModelMap modelo, @RequestParam String idProducto) {

    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {

      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getActiveUserByEmail(username);
      String idUsuario = usuario.getId();

      try {

        productoServicio.deleteById(idUsuario, idProducto);
//                usuarioServicio.cargarProducto(idUsuario, producto.getId());

      } catch (Exception ex) {

        Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
        modelo.put("Error ", ex.getMessage());
        return "user_home";

      }

    }

    return "user_home";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping(value = "/delete/{id}")
  public String eliminarListaProducto(@PathVariable(value = "id") String idProducto,ModelMap modelo) throws ServiceError {
    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuarioServicio.getActiveUserByEmail(username);
      String idUsuario = usuario.getId();
      productoServicio.deleteById(idProducto, idUsuario);

      modelo.put("productos", usuario.getObjects());
      System.out.println("Producto Eliminado con exito");
      addDetails(modelo,usuario);
    }

    return "user_listaTrueques";
  }

  private void addDetails(ModelMap modelo, User usuario) {
    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      modelo.addAttribute("name", usuario.getName());
      modelo.addAttribute("lastName", usuario.getLastName());
      modelo.addAttribute("street", usuario.getStreet());
      modelo.addAttribute("aptName", usuario.getAptNumber());
      modelo.addAttribute("dob", usuario.getBirthday());
      modelo.addAttribute("cellphone", usuario.getCellphone());
      modelo.addAttribute("area", usuario.getArea());
      modelo.addAttribute("currentCreditsCount", usuario.getCurrentCreditsCount());
      modelo.addAttribute("successfulTradesCount", usuario.getSuccessfulTradesCount());
    }
  }

//  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
//  @PostMapping("/rehabilitarProducto")
//  public String rehabilitarProducto(HttpSession session, ModelMap modelo, @RequestParam String idProducto) {
//
//    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    if (principal instanceof UserDetails) {
//
//      String username = ((UserDetails) principal).getUsername();
//      User usuario = usuariorepositorio.getActiveUserByEmail(username);
//      String idUsuario = usuario.getId();
//
//      try {
//
//        Object producto = productoServicio.rehabilitar(idUsuario, idProducto);
////                usuarioServicio.cargarProducto(idUsuario, producto.getId());
//
//      } catch (Exception ex) {
//
//        Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
//        modelo.put("Error ", ex.getMessage());
//        return "user_home";
//
//      }
//
//    }
//
//    return "user_home";
//  }

//    @PostMapping("/registrarServicio")
//    public String registrarServicio(ModelMap modelo, @RequestParam String idUsuario, @RequestParam Integer horas_estimadas, @RequestParam TipoServicio tipo, @RequestParam List<MultipartFile> archivos, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam Boolean dot, @RequestParam Zona zona) {
//
//        try {
//            objetoServicio.agregarServicio(idUsuario, horas_estimadas, tipo, archivos, titulo, descripcion, dot, zona);
//        } catch (Exception ex) {
//            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//            modelo.put("Error", ex.getMessage());
//            modelo.put("Horas Estimadas", horas_estimadas);
//            modelo.put("Tipo", tipo);
//            modelo.put("Fotos", archivos);
//            modelo.put("Título", titulo);
//            modelo.put("Descripción", descripcion);
//            modelo.put("Donación O Trueque", dot);
//            modelo.put("Zona", zona);
//            return "user_home";
//        }
//
//        return "user_home";
//    }

//    @PostMapping("/modificarServicio")
//    public String modificarServicio(ModelMap modelo, @RequestParam String idUsuario, @RequestParam String idServicio, @RequestParam Integer horas_estimadas, @RequestParam TipoServicio tipo, @RequestParam List<MultipartFile> archivos, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam Boolean dot, @RequestParam Zona zona) {
//
//        try {
//            objetoServicio.modificarServicio(idUsuario, idServicio, horas_estimadas, tipo, archivos, titulo, descripcion, dot, zona);
//        } catch (Exception ex) {
//            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//            modelo.put("Error", ex.getMessage());
//            modelo.put("Horas Estimadas", horas_estimadas);
//            modelo.put("Tipo", tipo);
//            modelo.put("Fotos", archivos);
//            modelo.put("Título", titulo);
//            modelo.put("Descripción", descripcion);
//            modelo.put("Donación O Trueque", dot);
//            modelo.put("Zona", zona);
//            return "user_home";
//        }
//
//        return "user_home";
//    }

}
