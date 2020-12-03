package com.example.truecapp3.controllers;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Area;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.Transaction;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.ObjectRepository;
import com.example.truecapp3.repositories.TransactionRepository;
import com.example.truecapp3.repositories.UserRepository;
import com.example.truecapp3.services.AreaService;
import com.example.truecapp3.services.CreditService;
import com.example.truecapp3.services.ObjectService;
import com.example.truecapp3.services.PhotoService;
import com.example.truecapp3.services.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
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
  private UserService usuarioServicio;
  @Autowired
  private CreditService creditoServicio;
  @Autowired
  private ObjectService productoServicio;
  @Autowired
  private AreaService areaService;
  @Autowired
  private PhotoService fotoServicio;
  @Autowired
  private UserRepository usuariorepositorio;
  @Autowired
  private ObjectRepository productorepositorio;
  @Autowired
  private TransactionRepository transaccionRepositorio;


  @GetMapping("/")
  public String index(ModelMap modelo) {
    User usuario = null;

    try {
      java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String username = ((UserDetails) principal).getUsername();

      usuario = usuariorepositorio.getActiveUserByEmail(username);

    } catch (Exception ignored) {
    }
    List<Object> productos = productorepositorio.findAll(Sort.unsorted());
    if (usuario == null) {
      modelo.put("productos", productos);
      return "index";
    } else {


      modelo.put("productos", objectFilter(productos, usuario.getId()));
      return "index_logueado";
    }


  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping("/home")
  public String home(ModelMap modelo) {
    User usuario = null;

    try {
      java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String username = ((UserDetails) principal).getUsername();

      usuario = usuariorepositorio.getActiveUserByEmail(username);

    } catch (Exception ignored) {
    }
    List<Object> productos = productorepositorio.findAll(Sort.unsorted());
    if (usuario == null) {
      modelo.put("productos", productos);
      return "index";
    } else {

      modelo.put("productos", objectFilter(productos, usuario.getId()));
      return "index_logueado";
    }
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping("/user_home")
  public String userhome(ModelMap modelo) {
    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);
    addDetails(modelo, usuario);

    try {
      List<Transaction> transacciones = transaccionRepositorio.getTransactionsByUserId(usuario.getId());
//              usuario.getTransactions();

      modelo.put("transacciones", transacciones);


    } catch (Exception ignored) {
    }


    return "user_home";
  }

//  @GetMapping("/Perfil_Usuario")
//  public String PerfilFreelancerPublico() {
//    return "Perfil_Usuario";
//  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @GetMapping("/user_listaTrueques")
  public String userListaTrueque(ModelMap modelo) {
    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);
    addDetails(modelo, usuario);
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
    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);
    List<Object> productos = productorepositorio.findAll(Sort.unsorted());
    if (usuario == null) {
      modelo.put("productos", productos);
      return "index";
    } else {
      modelo.put("productos", objectFilter(productos, usuario.getId()));
      modelo.put("productosU", usuario.getObjects());
      return "user_tiendahome2";
    }
  }

//  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
//  @RequestMapping(value = "/tiendahome2", method = RequestMethod.GET)
//  public String TiendaHome2(ModelMap modelo) {
//    modelo.put("productos", productorepositorio.findAll(Sort.unsorted()));
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String username = ((UserDetails) principal).getUsername();
//    User usuario = usuariorepositorio.getUserByEmail(username);
//    modelo.put("productosU", usuario.getObjects());
//    addDetails(modelo, usuario);
//    return "user_tiendahome2";
//  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @RequestMapping(value = "/listaTrueques", method = RequestMethod.GET)
  public String ListaTrueque(ModelMap modelo) {
    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);
    modelo.put("productos", usuario.getObjects());
    addDetails(modelo, usuario);
    return "user_listaTrueques";
  }


  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @RequestMapping(value = "/nuevoTrueque", method = RequestMethod.GET)
  public String nuevoTrueque(ModelMap modelo) {
    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = ((UserDetails) principal).getUsername();
    User usuario = usuariorepositorio.getUserByEmail(username);
    addDetails(modelo, usuario);
    return "user_nuevoTrueque";
  }


//  @PostMapping("/buscar")
//  public String buscar(@RequestParam String Busqueda) {
//    System.out.println(Busqueda);
//    return "tables";
//  }

  @PostMapping("/registrar")
  public String registerClient(ModelMap modelo, @RequestParam String nombre, @RequestParam String
          apellido, @RequestParam String mail, @RequestParam String clave, @RequestParam String clave2) {
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


    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getUserByEmail(username);
      boolean isFirst = usuario.isFirstTime();


      if (isFirst) {
        return "new_user_info";
      } else {
        addDetails(modelo, usuario);
        try {
          List<Transaction> Transacciones = transaccionRepositorio.getTransactionsByUserId(usuario.getId());
//                  usuario.getTransactions();
          List<Transaction> ofrecidas = new ArrayList<>();

          for (Transaction transaccion : Transacciones) {
            if (transaccion.getSeller().equals(usuario)
                || transaccion.getReceiver().equals(usuario)) {

              ofrecidas.add(transaccion);

            }
            if (!ofrecidas.isEmpty()) {
              modelo.put("transacciones", ofrecidas);
            }
          }


        } catch (Exception ignored) {
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
        usuarioServicio.setEmailVerifySuccess(id);
        modelo.put("bienvenida", "Tu email ha sido verificado correctamente! Iniciá Sesión para continuar");
        return "login";
      }


    } catch (ServiceError e) {
      modelo.put("error", e.getMessage());
      return "login";
    }


  }


  @PostMapping("/completarRegistro")
  public String completarRegistro(ModelMap modelo, @RequestParam String calle,
                                  @RequestParam String numeroCasa,
                                  @RequestParam(required = false) MultipartFile perfil,
                                  @RequestParam(required = false) MultipartFile dni,
                                  @RequestParam(required = false) String telefono,
                                  @RequestParam String areaName,
                                  @RequestParam String zona,
                                  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob) throws
          ServiceError {

    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    System.out.println(dob);
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getUserByEmail(username);

      System.out.println(usuario.getEmail());
      Area zone = areaService.createArea(areaName, zona);


      User newUser = usuarioServicio.completeUser(perfil, dni, usuario.getId(), calle, numeroCasa, telefono, zone, dob);
      usuarioServicio.sendEmailVerification(usuario.getId());
      addDetails(modelo, newUser);

      return "user_home";
    }


    return "new_user_info";
  }

  @PreAuthorize("hasAnyRole('ROLE_CLIENT')")
  @PostMapping("/editProfile")
  public String editProfile(ModelMap modelo, @RequestParam String calle,
                            @RequestParam String numeroCasa,
                            @RequestParam String name,
                            @RequestParam String lastName,
                            @RequestParam(required = false) MultipartFile perfil,
                            @RequestParam(required = false) MultipartFile dni,
                            @RequestParam(required = false) String telefono,
                            @RequestParam(required = false) String areaName,
                            @RequestParam(required = false) String zona,
                            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob)
          throws ServiceError {

    java.lang.Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    System.out.println(dob);
    if (principal instanceof UserDetails) {
      String username = ((UserDetails) principal).getUsername();
      User usuario = usuariorepositorio.getUserByEmail(username);

//      System.out.println(usuario.getEmail());
      Area zone = areaService.createArea(areaName, zona);
      System.out.println(zone.getAreaName());
      User newUser = usuarioServicio.modifyUser(perfil, dni, usuario.getId(), name, lastName, calle, numeroCasa, telefono, dob, zone);

      addDetails(modelo, newUser);
      return "user_home";
    }


    return "new_user_info";
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

  private List<Object> objectFilter(List<Object> objects, String currentUserId) {
    List<Object> result = new ArrayList<>();
    for (Object o : objects) {
      if (!o.getOwner().getId().equals(currentUserId)
          && transaccionRepositorio.getTransactionByObjectId(o.getId()) == null) {
            result.add(o);
          }
    }
    return result;
  }

}
