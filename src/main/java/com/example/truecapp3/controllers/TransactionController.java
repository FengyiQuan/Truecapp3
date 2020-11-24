package com.example.truecapp3.controllers;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Transaction;
import com.example.truecapp3.models.User;
import com.example.truecapp3.services.CreditService;
import com.example.truecapp3.services.ObjectService;
import com.example.truecapp3.services.PhotoService;
import com.example.truecapp3.services.TransactionService;
import com.example.truecapp3.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

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


  @RequestMapping(value = "/iniciarOferta", method = RequestMethod.GET)
  public String iniciarOferta(ModelMap modelo, @RequestParam String idProducto4, @RequestParam String idProducto5) throws ServiceError {
//    System.out.println(idProducto4);
//    System.out.println(idProducto5);

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
    modelo.put("productosU", usuario.getObjects());
    modelo.put("exito", "Tu oferta ha sido enviada. Espera una respuesta del otro usuario pronto.");
    return "user_tiendahome2";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping(value = "/delete/{id}")
  public String eliminarListaProducto(@PathVariable(value = "id") String idProducto) throws ServiceError {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuarioServicio.getActiveUserByEmail(username);
      String idUsuario = usuario.getId();
      productoServicio.deleteById(idProducto, idUsuario);
      System.out.println("Producto Eliminado con exito");
    }
    return "user_listaTrueques";
  }

  @RequestMapping(value = "/rechazar/{transactionId}", method = RequestMethod.GET)
  public String rechazarTransaction(ModelMap modelo, @PathVariable String transactionId) {
    try {
      transaccionServicio.addCancelDate(transactionId, new Date());
    } catch (Exception ex) {
      modelo.put("error", ex.getMessage());
      return "new_user";
    }
    return "user_tiendahome2";
  }

  @RequestMapping(value = "/addDeliveryDate/{transactionId}", method = RequestMethod.GET)
  public String addDeliveryDate(ModelMap modelo, @PathVariable String transactionId) {
    try {
      transaccionServicio.addCancelDate(transactionId, new Date());
    } catch (Exception ex) {
      modelo.put("error", ex.getMessage());
      return "new_user";
    }
    return "user_tiendahome2";
  }

  @RequestMapping(value = "/addOfferDate/{transactionId}", method = RequestMethod.GET)
  public String addOfferDate(ModelMap modelo, @PathVariable String transactionId) {
    try {
      transaccionServicio.addCancelDate(transactionId, new Date());
    } catch (Exception ex) {
      modelo.put("error", ex.getMessage());
      return "new_user";
    }
    return "user_tiendahome2";
  }

  @RequestMapping(value = "/completeTransaction/{transactionId}", method = RequestMethod.GET)
  public String completeTransaction(ModelMap modelo, @PathVariable String transactionId) {
    try {
      transaccionServicio.completeTransaction(transactionId);
    } catch (Exception ex) {
      modelo.put("error", ex.getMessage());
      return "new_user";
    }
    return "user_tiendahome2";
  }


}
