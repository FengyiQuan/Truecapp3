package com.example.truecapp3.controllers;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.User;
import com.example.truecapp3.services.CreditService;
import com.example.truecapp3.services.ObjectService;
import com.example.truecapp3.services.PhotoService;
import com.example.truecapp3.services.TransactionService;
import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class TransactionController {
  @Autowired
  UserService usuarioServicio;
  @Autowired
  CreditService creditoServicio;
  @Autowired
  ObjectService productoServicio;


  @Autowired
  PhotoService fotoServicio;


  @Autowired
  TransactionService transaccionServicio;


//    @PostMapping("/iniciarOferta")
//    public String iniciarOferta(ModelMap modelo, @RequestParam String idProducto1, @RequestParam String idProducto2) throws ErrorServicio {
//
//        try {
//            transaccionServicio.iniciarOferta(idProducto1, idProducto2);
//        } catch (Exception ex) {
//            modelo.put("error", ex.getMessage());
//            return "new_user";
//        }
//
//        return "new_user";
//    }

  @RequestMapping(value = "/iniciarOferta", method = RequestMethod.GET)
  public String iniciarOferta(ModelMap modelo, @RequestParam String idProducto4, @RequestParam String idProducto5) throws ServiceError {
    System.out.println(idProducto4);
    System.out.println(idProducto5);

    try {
      transaccionServicio.startTransaction(idProducto4, idProducto5);
    } catch (Exception ex) {
      modelo.put("error", ex.getMessage());
      return "new_user";
    }
    modelo.put("productos", productoServicio.findAll(Sort.unsorted()));
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuarioServicio.getActiveUserByEmail(username);
    modelo.put("productosU", usuario.getProducts());
    modelo.put("exito", "Tu oferta ha sido enviada. Espera una respuesta del otro usuario pronto.");
    return "user_tiendahome2";
  }
}
