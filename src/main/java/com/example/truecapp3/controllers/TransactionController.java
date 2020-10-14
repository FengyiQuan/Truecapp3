//package com.example.truecapp3.controllers;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/")
//public class TransactionController {
//  @Autowired
//  UsuarioServicio usuarioServicio;
//  @Autowired
//  CreditoServicio creditoServicio;
//  @Autowired
//  ProductoServicio productoServicio;
//
//  @Autowired
//  EmpresaServicio empresaServicio;
//
//  @Autowired
//  FotoServicio fotoServicio;
//
//  @Autowired
//  usuarioRepositorio usuariorepositorio;
//
//  @Autowired
//  ProductoRepositorio productorepositorio;
//
//  @Autowired
//  TransaccionServicio transaccionServicio;
//
//
////    @PostMapping("/iniciarOferta")
////    public String iniciarOferta(ModelMap modelo, @RequestParam String idProducto1, @RequestParam String idProducto2) throws ErrorServicio {
////
////        try {
////            transaccionServicio.iniciarOferta(idProducto1, idProducto2);
////        } catch (Exception ex) {
////            modelo.put("error", ex.getMessage());
////            return "new_user";
////        }
////
////        return "new_user";
////    }
//
//  @RequestMapping(value = "/iniciarOferta", method = RequestMethod.GET)
//  public String iniciarOferta(ModelMap modelo, @RequestParam String idProducto4, @RequestParam String idProducto5) throws ErrorServicio {
//    System.out.println(idProducto4);
//    System.out.println(idProducto5);
//
//    try {
//      transaccionServicio.iniciarOferta(idProducto4, idProducto5);
//    } catch (Exception ex) {
//      modelo.put("error", ex.getMessage());
//      return "new_user";
//    }
//    modelo.put("productos", productorepositorio.findAll(Sort.unsorted()));
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String username = ((UserDetails) principal).getUsername();
//    Usuario usuario = usuariorepositorio.buscarUsuarioPorMail(username);
//    modelo.put("productosU", usuario.getListaProductos());
//    modelo.put("exito", "Tu oferta ha sido enviada. Espera una respuesta del otro usuario pronto.");
//    return "user_tiendahome2";
//  }
//}
