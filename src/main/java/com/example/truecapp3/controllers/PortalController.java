package com.example.truecapp3.controllers;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Transaction;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.ObjectRepository;
import com.example.truecapp3.repositories.TransactionRepository;
import com.example.truecapp3.repositories.UserRepository;
import com.example.truecapp3.services.CreditService;
import com.example.truecapp3.services.ObjectService;
import com.example.truecapp3.services.PhotoService;
import com.example.truecapp3.services.UserService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalController {
  @Autowired
  UserService usuarioServicio;
  @Autowired
  CreditService creditoServicio;
  @Autowired
  ObjectService productoServicio;


  @Autowired
  PhotoService fotoServicio;

  @Autowired
  UserRepository usuariorepositorio;

  @Autowired
  ObjectRepository productorepositorio;

  @Autowired
  TransactionRepository transaccionRepositorio;

  @GetMapping("/")
  public String index(ModelMap modelo) {
    User usuario = null;

    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String username = ((UserDetails) principal).getUsername();

      usuario = usuariorepositorio.getActiveUserByEmail(username);
    } catch (Exception ignored) {
    }
    if (usuario == null) {
      modelo.put("productos", productorepositorio.findAll(Sort.unsorted()));
      return "index";
    } else {
      modelo.put("productos", productorepositorio.findAll(Sort.unsorted()));
      return "index_logueado";
    }


  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping("/home")
  public String home(ModelMap modelo) {
    modelo.put("productos", productorepositorio.findAll(Sort.unsorted()));
    return "index_logueado";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping("/user_home")
  public String userhome(ModelMap modelo) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);

    try {
      List<Transaction> Transacciones = usuario.getTransactions();
      List<Transaction> ofrecidas = new ArrayList();

      for (Transaction transaccion : Transacciones) {
        if (transaccion.getUser1().equals(usuario)||transaccion.getUser2().equals(usuario)) {

          ofrecidas.add(transaccion);

        }
        if (!ofrecidas.isEmpty()) {
          modelo.put("transacciones", ofrecidas);
        }
      }


    } catch (Exception e) {
    }


    return "user_home";
  }

  @GetMapping("/Perfil_Usuario")
  public String PerfilFreelancerPublico() {
    return "Perfil_Usuario";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping("/user_listaTrueques")
  public String userListaTrueque() {
    return "user_listaTrueques";
  }

  @GetMapping(value = "/login")
  public String login(@RequestParam(required = false) String error, ModelMap modelo) {
    if (error != null) {
      modelo.put("error", "Nombre de Usuario o clave Incorrectos");
    }
    return "login";
  }

  @GetMapping(value = "/register")
  public String NuevoCliente(ModelMap modelo) {

    return "new_user";
  }


  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping(value = "/tiendahome")
  public String TiendaHome(ModelMap modelo) {
    modelo.put("productos", productorepositorio.findAll(Sort.unsorted()));
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);
    modelo.put("productosU", usuario.getProducts());
    return "user_tiendahome2";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @RequestMapping(value = "/tiendahome2", method = RequestMethod.GET)
  public String TiendaHome2(ModelMap modelo) {
    modelo.put("productos", productorepositorio.findAll(Sort.unsorted()));
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);
    modelo.put("productosU", usuario.getProducts());
    return "user_tiendahome2";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @RequestMapping(value = "/listaTrueques", method = RequestMethod.GET)
  public String ListaTrueque(ModelMap modelo) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);
    modelo.put("productos", usuario.getProducts());
    return "user_listaTrueques";
  }


  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @RequestMapping(value = "/nuevoTrueque", method = RequestMethod.GET)
  public String nuevoTrueque(ModelMap modelo) {

    return "user_nuevoTrueque";
  }

  @PostMapping("/buscar")
  public String buscar(@RequestParam String Busqueda) {
    System.out.println(Busqueda);
    return "tables";
  }

  @PostMapping("/registrar")
  public String registerClient(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave, @RequestParam String clave2) {
    System.out.println(mail);
    boolean exito;

    // email has been registered
    if (!usuarioServicio.checkEmail(mail)) {
      modelo.put("error", "Hola! Parece que ya tenés cuenta con nosotros. Porqué no probas Iniciando Sesión?");
      return "login";
    }

    try {
      usuarioServicio.verifyPassword(clave, clave2);
      usuarioServicio.createNewUser(nombre, apellido, mail, clave);
    } catch (Exception ex) {
      Logger.getLogger(PortalController.class.getName()).log(Level.SEVERE, null, ex);
      modelo.put("error", ex.getMessage());
      modelo.put("nombre", nombre);
      modelo.put("apellido", apellido);
      modelo.put("mail", mail);
      return "new_user";
    }
    exito = true;
    modelo.put("exito", exito);
    modelo.put("bienvenida", "Gracias por unirte a nuestra Plataforma, " + nombre
                             + "! Iniciá sesión para comenzar.");

    return "login";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @RequestMapping(value = "/Inicio", method = RequestMethod.GET)
  public String Redireccion(HttpSession session, ModelMap modelo) {


    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getUserByEmail(username);
      boolean isFirst = usuario.isFirstTime();


      if (isFirst) {
        return "new_user_info";
      } else {

        try {
          List<Transaction> Transacciones = usuario.getTransactions();
          List<Transaction> ofrecidas = new ArrayList<>();

          for (Transaction transaccion : Transacciones) {
            if (transaccion.getUser1().equals(usuario)||transaccion.getUser2().equals(usuario)) {

              ofrecidas.add(transaccion);

            }
            if (!ofrecidas.isEmpty()) {
              modelo.put("transacciones", ofrecidas);
            }
          }


        } catch (Exception e) {
        }


        return "user_home";
      }


    }
    return "user_home";

  }


  @GetMapping("/verificarUsuario/{id}")
  public String verificarUsuario(@PathVariable String id, ModelMap modelo) throws ServiceError {
    System.out.println(id);

    try {
      User usuario = usuarioServicio.getActiveUserById(id);
      if (usuario.isEmailVerified()) {
        modelo.put("error", "Tu email ya ha sido verificado anteriormente. Iniciá Sesión para continuar");
        return "login";
      } else {
//        usuarioServicio.EmailVerificado(id);
        modelo.put("bienvenida", "Tu email ha sido verificado correctamente! Iniciá Sesión para continuar");
        return "login";
      }


    } catch (ServiceError e) {
      modelo.put("error", e.getMessage());
      return "login";
    }


  }


  @PostMapping("/completarRegistro")
  public String completarRegistro(ModelMap modelo, @RequestParam(required = false) String calle, @RequestParam(required = false) String numeroCasa, @RequestParam(required = false) MultipartFile perfil, @RequestParam(required = false) MultipartFile dni, @RequestParam(required = false) String telefono) throws ServiceError {

    System.out.println(calle);
    System.out.println(numeroCasa);
    System.out.println(telefono);

    Boolean exito = false;


    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getUserByEmail(username);

      System.out.println(usuario.getEmail());
      //        usuarioServicio.(perfil, dni, usuario.getId(), calle, numeroCasa, telefono);
//        usuarioServicio.enviarMailVerificacion(usuario.getId());
return "user_home";
    }


    return "new_user_info";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping(value = "/delete/{id}")
  public String eliminarListaProducto(@PathVariable(value = "id") String idProducto) throws ServiceError {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getUserByEmail(username);
      String idUsuario = usuario.getId();
      productoServicio.deleteById(idProducto, idUsuario);
      System.out.println("Producto Eliminado con exito");
    }
    return "user_listaTrueques";
  }
}
