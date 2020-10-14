//package com.example.truecapp3.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/")
//public class ObjectController {
//  @Autowired
//  UsuarioServicio usuarioServicio;
//
//  @Autowired
//  CreditoServicio creditoServicio;
//
//  @Autowired
//  EmpresaServicio empresaServicio;
//
//  @Autowired
//  FotoServicio fotoServicio;
//
//  @Autowired
//  ProductoServicio productoServicio;
//
//  @Autowired
//  usuarioRepositorio usuariorepositorio;
//
//  //    @Autowired
////    ServicioServicio servicioServicio;
//  @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
//  @PostMapping("/registrarProducto")
//  public String registrarProducto(HttpSession session, ModelMap modelo, @RequestParam List<MultipartFile> archivos, @RequestParam String titulo, @RequestParam String descripcion) {
//
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    if (principal instanceof UserDetails) {
//
//      String username = ((UserDetails) principal).getUsername();
//      Usuario usuario = usuariorepositorio.buscarUsuarioPorMail(username);
//      String idUsuario = usuario.getId();
//
//      try {
//
//        Condicion condicion = Condicion.NUEVO;
//        Boolean dot = true;
//        String categoriaID = "0";
//        Producto producto = productoServicio.agregarProducto(idUsuario, condicion, categoriaID, archivos, titulo, descripcion, dot);
////                usuarioServicio.cargarProducto(idUsuario, producto.getId());
//
//      } catch (Exception ex) {
//
//        Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//        modelo.put("Error", ex.getMessage());
//        //            modelo.put("Tipo", tipo);
//        modelo.put("Fotos", archivos);
//        modelo.put("Título", titulo);
//        modelo.put("Descripción", descripcion);
//        //            modelo.put("Donación O Trueque", "Trueque");
//        return "user_nuevoTrueque";
//
//      }
//
//    }
//
//    return "user_home";
//
//  }
//
//  @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
//  @PostMapping("/modificarProducto")
//  public String modificarProducto(HttpSession session, ModelMap modelo, @RequestParam String idProducto, @RequestParam List<MultipartFile> archivos, @RequestParam String titulo, @RequestParam String descripcion) {
//
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    if (principal instanceof UserDetails) {
//      String username = ((UserDetails) principal).getUsername();
//      Usuario usuario = usuariorepositorio.buscarUsuarioPorMail(username);
//      String idUsuario = usuario.getId();
//
//      try {
//
//        Condicion condicion = Condicion.NUEVO;
//        Boolean dot = true;
//        String categoriaID = "0";
//        Producto producto = productoServicio.modificarProducto(idUsuario, idProducto, condicion, categoriaID, archivos, titulo, descripcion, dot);
////                usuarioServicio.cargarProducto(idUsuario, producto.getId());
//
//      } catch (Exception ex) {
//
//        Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//        modelo.put("Error ", ex.getMessage());
////                modelo.put("Condición ", condicion);
////                modelo.put("Categoría ", categoriaID);
//        modelo.put("Fotos", archivos);
//        modelo.put("Título", titulo);
//        modelo.put("Descripción", descripcion);
////                modelo.put("Donación O Trueque", dot);
//        return "user_home";
//
//      }
//
//    }
//
//    return "user_home";
//  }
//
//  @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
//  @PostMapping("/bajarProducto")
//  public String bajarProducto(HttpSession session, ModelMap modelo, @RequestParam String idProducto) {
//
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    if (principal instanceof UserDetails) {
//
//      String username = ((UserDetails) principal).getUsername();
//      Usuario usuario = usuariorepositorio.buscarUsuarioPorMail(username);
//      String idUsuario = usuario.getId();
//
//      try {
//
//        Producto producto = productoServicio.bajarProducto(idUsuario, idProducto);
////                usuarioServicio.cargarProducto(idUsuario, producto.getId());
//
//      } catch (Exception ex) {
//
//        Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//        modelo.put("Error ", ex.getMessage());
//        return "user_home";
//
//      }
//
//    }
//
//    return "user_home";
//  }
//
//  @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
//  @PostMapping("/rehabilitarProducto")
//  public String rehabilitarProducto(HttpSession session, ModelMap modelo, @RequestParam String idProducto) {
//
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    if (principal instanceof UserDetails) {
//
//      String username = ((UserDetails) principal).getUsername();
//      Usuario usuario = usuariorepositorio.buscarUsuarioPorMail(username);
//      String idUsuario = usuario.getId();
//
//      try {
//
//        Producto producto = productoServicio.rehabilitar(idUsuario, idProducto);
////                usuarioServicio.cargarProducto(idUsuario, producto.getId());
//
//      } catch (Exception ex) {
//
//        Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
//        modelo.put("Error ", ex.getMessage());
//        return "user_home";
//
//      }
//
//    }
//
//    return "user_home";
//  }
//
////    @PostMapping("/registrarServicio")
////    public String registrarServicio(ModelMap modelo, @RequestParam String idUsuario, @RequestParam Integer horas_estimadas, @RequestParam TipoServicio tipo, @RequestParam List<MultipartFile> archivos, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam Boolean dot, @RequestParam Zona zona) {
////
////        try {
////            objetoServicio.agregarServicio(idUsuario, horas_estimadas, tipo, archivos, titulo, descripcion, dot, zona);
////        } catch (Exception ex) {
////            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
////            modelo.put("Error", ex.getMessage());
////            modelo.put("Horas Estimadas", horas_estimadas);
////            modelo.put("Tipo", tipo);
////            modelo.put("Fotos", archivos);
////            modelo.put("Título", titulo);
////            modelo.put("Descripción", descripcion);
////            modelo.put("Donación O Trueque", dot);
////            modelo.put("Zona", zona);
////            return "user_home";
////        }
////
////        return "user_home";
////    }
//
////    @PostMapping("/modificarServicio")
////    public String modificarServicio(ModelMap modelo, @RequestParam String idUsuario, @RequestParam String idServicio, @RequestParam Integer horas_estimadas, @RequestParam TipoServicio tipo, @RequestParam List<MultipartFile> archivos, @RequestParam String titulo, @RequestParam String descripcion, @RequestParam Boolean dot, @RequestParam Zona zona) {
////
////        try {
////            objetoServicio.modificarServicio(idUsuario, idServicio, horas_estimadas, tipo, archivos, titulo, descripcion, dot, zona);
////        } catch (Exception ex) {
////            Logger.getLogger(PortalControlador.class.getName()).log(Level.SEVERE, null, ex);
////            modelo.put("Error", ex.getMessage());
////            modelo.put("Horas Estimadas", horas_estimadas);
////            modelo.put("Tipo", tipo);
////            modelo.put("Fotos", archivos);
////            modelo.put("Título", titulo);
////            modelo.put("Descripción", descripcion);
////            modelo.put("Donación O Trueque", dot);
////            modelo.put("Zona", zona);
////            return "user_home";
////        }
////
////        return "user_home";
////    }
//
//}
