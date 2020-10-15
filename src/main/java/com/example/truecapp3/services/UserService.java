package com.example.truecapp3.services;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.CreditRepository;
import com.example.truecapp3.repositories.ObjectRepository;
import com.example.truecapp3.repositories.TransactionRepository;
import com.example.truecapp3.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository usuarioRespositorio;

  @Autowired
  private ObjectRepository productoRepositorio;
  @Autowired
  private PhotoService fotoServicio;
  @Autowired
  private NotificationService notificacionServicio;
  @Autowired
  private TransactionRepository transaccionRepositorio;

  @Autowired
  private CreditRepository creditoRepositorio;

  //la primera vez que se crea un usuario, no se crea con todos los datos, si no con lo minimo
  @Transactional
  public void nuevoUsuario(String nombre, String apellido, String mail, String clave) throws ServiceError, MessagingException {
    validar(nombre, apellido, "0", mail, clave);

    Usuario usuario = new Usuario();
    usuario.setNombre(nombre);
    usuario.setApellido(apellido);

    usuario.setMail(mail);
    String encriptada = new BCryptPasswordEncoder().encode(clave);
    usuario.setClave(encriptada);
    usuario.setFechaAlta(new Date());
    usuario.setIsFirstTime(true);
    usuario.setEmailVerified(false);
    usuario.setListaTransacciones(new ArrayList<>());
    usuario.setListaCreditos(new ArrayList<>());
    Credito credito = new Credito();
    credito.setTipocredito(tipoCredito.COMPRADO);
    usuario.getListaCreditos().add(credito);
    creditoRepositorio.save(credito);
    usuarioRespositorio.save(usuario);

    //    notificacionServicio.enviar("Bienvenidos a nuestra plataforma" + usuario.getNombre() + "! Esperemos que disfrutes mucho de nuestro proyecto.", "Te damos la bienvenida a TruecAPP, " + usuario.getNombre(), "bortnicaaron@gmail.com");
    notificacionServicio.sendMail("Bienvenidos a nuestra plataforma", usuario);
  }

  @Transactional
  public void modificarUsuario(MultipartFile fotoPerfil, MultipartFile fotoDNI, String id,
                               String nombre, String apellido, String documento, String telefono,
                               Date fechaNacimiento, String calle, String numeroCasa, Zona zona,
                               String mail, String clave, String clave2) throws ErrorServicio {
    //validar(nombre, apellido, "0", mail,clave);
//        if (!clave.equals(clave2)) {
//           throw new ErrorServicio("Las contraseñas deben coincidir");
//
//        }
//

    Optional<Usuario> respuesta = usuarioRespositorio.findById(id);

    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();

      usuario.setNombre(nombre);
      usuario.setApellido(apellido);
      usuario.setDocumento(documento);
      usuario.setTelefono(telefono);
      usuario.setMail(mail);
      String encriptada = new BCryptPasswordEncoder().encode(clave);
      usuario.setClave(encriptada);
      usuario.setFechaNacimiento(fechaNacimiento);
      usuario.setCalle(calle);
      usuario.setNumeroCasa(numeroCasa);
      usuario.setZona(zona);
      String idFoto = usuario.getFotoPerfil().getId();
      if (idFoto != null) {
        Foto foto = fotoServicio.Actualizar(idFoto, fotoPerfil);
        usuario.setFotoPerfil(foto);
      } else {
        Foto foto = fotoServicio.Guardar(fotoPerfil);
        usuario.setFotoPerfil(foto);
      }

      if (fotoDNI != null) {
        Foto fotodni = fotoServicio.Guardar(fotoDNI);
        usuario.setFotoDNI(fotodni);
      }

      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("No se encontró el usuario solicitado");

    }

  }

  @Transactional
  public void completarUsuario(MultipartFile fotoPerfil, MultipartFile fotoDNI, String id,
                               String calle, String numeroCasa, String telefono) throws ErrorServicio {
    //validar(nombre, apellido, "0", mail,clave);
//        if (!clave.equals(clave2)) {
//           throw new ErrorServicio("Las contraseñas deben coincidir");
//
//        }
//

    Optional<Usuario> respuesta = usuarioRespositorio.findById(id);

    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();

      usuario.setTelefono(telefono);

      usuario.setCalle(calle);
      usuario.setNumeroCasa(numeroCasa);

      if (fotoPerfil != null) {
        Foto foto = fotoServicio.Guardar(fotoPerfil);
        usuario.setFotoPerfil(foto);
      }

      if (fotoDNI != null) {
        Foto fotodni = fotoServicio.Guardar(fotoDNI);
        usuario.setFotoDNI(fotodni);
      }
      usuario.setIsFirstTime(false);
      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("No se encontró el usuario solicitado");

    }

  }

  //si solo queremos guardar la foto del usuario.
  @Transactional
  public void GuardarFotoPerfil(MultipartFile archivo, String idUsuario) throws ErrorServicio {

    Optional<Usuario> respuesta = usuarioRespositorio.findById(idUsuario);

    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();

      Foto foto = fotoServicio.Guardar(archivo);
      usuario.setFotoPerfil(foto);
      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("No se encontró el usuario solicitado");

    }

  }

  @Transactional
  public void GuardarFotoDNI(MultipartFile archivo, String idUsuario) throws ErrorServicio {

    Optional<Usuario> respuesta = usuarioRespositorio.findById(idUsuario);

    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();

      Foto foto = fotoServicio.Guardar(archivo);
      usuario.setFotoDNI(foto);
      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("No se encontró el usuario solicitado");

    }

  }

  //si es necesario encontrar a un usuario por su id.
  public Usuario consultarUsuarioID(String ID) throws ErrorServicio {
    Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();
      return usuario;
    } else {
      throw new ErrorServicio("Ese usuario no existe");
    }
  }

  @Async
  public void enviarMailVerificacion(String ID) throws ErrorServicio {
    Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();
      System.out.println(usuario.getMail());
      System.out.println(usuario.getId());
      notificacionServicio.send("Gracias por completar tu registro! " + usuario.getNombre()
                                + "! Para Verificar tu mail haz click aquí: http://localhost:8081/verificarUsuario/"
                                + usuario.getId(), "Verifica tu mail para poder comenzar a truecar!", usuario.getMail());

    } else {
      throw new ErrorServicio("Ese usuario no existe");
    }
  }

  //una vez que el mail se verifica, se corre este metodo.
  @Transactional
  public void EmailVerificado(String ID) throws ErrorServicio {
    Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();
      usuario.setEmailVerified(Boolean.TRUE);
      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("Ese usuario no existe");
    }
  }

  //correr este metodo cuando el usuario se loguea correctamente a la plataforma. esto
  //sirve para darle un mensaje de bienvenida.
  @Transactional
  public void SetFirstTime(String ID) throws ErrorServicio {
    Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();
      usuario.setIsFirstTime(Boolean.FALSE);
      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("Ese usuario no existe");
    }
  }

  //este metodo devuelve un booleano, de si ese mail ya existe en la base de datos
  public Boolean verificarEmail(String mail) {
    try {
      String id = usuarioRespositorio.buscarUsuarioPorMail(mail.toLowerCase()).getId();
      System.out.println("");
      Optional<Usuario> respuesta = usuarioRespositorio.findById(id);
      Usuario usuario = respuesta.get();

      if (respuesta.isPresent()) {
        System.out.println("Usuario encontrado");
        return true;
      } else {
        System.out.println("Usuario no encontrado");
        return false;
      }
//          throw new ErrorServicio("Hola, "+usuario.getNombre()+" "+usuario.getApellido()+"! Parece que ya tenés cuenta con nosotros. Porqué no probas Iniciando Sesión?");

    } catch (Exception e) {
      return false;
    }

  }

  //verifica que ambas claves sean las mismas
  public void verificarClave(String Clave1, String Clave2) throws ErrorServicio {
    if (Clave1.equals(Clave2)) {
      System.out.println("Contraseñas OK");

    } else {
      throw new ErrorServicio("Las contraseñas deben coincidir");

    }

  }

  @Transactional
  public void bajaUsuario(String ID) throws ErrorServicio {
    Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();
      usuario.setFechaBaja(new Date());
      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("Ese usuario no existe");
    }
  }

  @Transactional
  public void rehabilitarUsuario(String ID) throws ErrorServicio {
    Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();
      usuario.setFechaBaja(null);
      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("Ese usuario no existe");
    }
  }

  @Transactional
  public void cargarProducto(String userId, String productID) throws ErrorServicio {
    Optional<Usuario> respuesta = usuarioRespositorio.findById(userId);
    if (respuesta.isPresent()) {
      Usuario usuario = respuesta.get();

      Optional<Producto> respuestaProducto = productoRepositorio.findById(productID);
      if (respuestaProducto.isPresent()) {

        Producto producto = respuestaProducto.get();

        if (usuario.getListaProductos() == null) {
          List<Producto> listaNueva = new ArrayList<Producto>();
          usuario.setListaProductos(listaNueva);

        }
        List<Producto> listaActualizada = usuario.getListaProductos();
        if (listaActualizada.contains(producto)) {
          listaActualizada.remove(producto);
          listaActualizada.add(producto);
        } else {
          listaActualizada.add(producto);
        }

        usuario.setListaProductos(listaActualizada);

      } else {

        throw new ErrorServicio("Ese Producto no existe");

      }

      usuarioRespositorio.save(usuario);
    } else {
      throw new ErrorServicio("Ese usuario no existe");
    }

  }

  private void validar(String nombre, String apellido, String documento, String mail, String clave) throws ErrorServicio {
    if (nombre == null || nombre.isEmpty()) {
      throw new ErrorServicio("El nombre no puede ser vacío");

    }
    if (apellido == null || apellido.isEmpty()) {
      throw new ErrorServicio("El apellido no puede ser vacío");

    }
    if (documento == null || documento.isEmpty()) {
      throw new ErrorServicio("El documento no puede estar vacío");

    }
    if (mail == null || mail.isEmpty()) {
      throw new ErrorServicio("Debe indicar una dirección de correo electrónico");

    }
    if (clave == null || clave.length() < 6) {
      throw new ErrorServicio("La clave debe contener al menos 6 caracteres.");

    }

  }

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    Usuario usuario = usuarioRespositorio.buscarUsuarioPorMail(mail);
    if (usuario != null) {
      List<GrantedAuthority> permisos = new ArrayList<>();
      GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_CLIENTE");
      permisos.add(p1);

      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = attr.getRequest().getSession();
      session.setAttribute("UsuarioSession", usuario);

      User user = new User(usuario.getMail(), usuario.getClave(), permisos);
      return user;
    } else {
      return null;  @Autowired
      private UserRepository usuarioRespositorio;

      @Autowired
      private ObjectRepository productoRepositorio;
      @Autowired
      private PhotoService fotoServicio;
      @Autowired
      private NotificationService notificacionServicio;
      @Autowired
      private TransactionRepository transaccionRepositorio;

      @Autowired
      private CreditRepository creditoRepositorio;

      //la primera vez que se crea un usuario, no se crea con todos los datos, si no con lo minimo
      @Transactional
      public void nuevoUsuario(String nombre, String apellido, String mail, String clave) throws ServiceError, MessagingException {
        validar(nombre, apellido, "0", mail, clave);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);

        usuario.setMail(mail);
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);
        usuario.setFechaAlta(new Date());
        usuario.setIsFirstTime(true);
        usuario.setEmailVerified(false);
        usuario.setListaTransacciones(new ArrayList<>());
        usuario.setListaCreditos(new ArrayList<>());
        Credito credito = new Credito();
        credito.setTipocredito(tipoCredito.COMPRADO);
        usuario.getListaCreditos().add(credito);
        creditoRepositorio.save(credito);
        usuarioRespositorio.save(usuario);

        //    notificacionServicio.enviar("Bienvenidos a nuestra plataforma" + usuario.getNombre() + "! Esperemos que disfrutes mucho de nuestro proyecto.", "Te damos la bienvenida a TruecAPP, " + usuario.getNombre(), "bortnicaaron@gmail.com");
        notificacionServicio.sendMail("Bienvenidos a nuestra plataforma", usuario);
      }

      @Transactional
      public void modificarUsuario(MultipartFile fotoPerfil, MultipartFile fotoDNI, String id,
              String nombre, String apellido, String documento, String telefono,
              Date fechaNacimiento, String calle, String numeroCasa, Zona zona,
              String mail, String clave, String clave2) throws ErrorServicio {
        //validar(nombre, apellido, "0", mail,clave);
//        if (!clave.equals(clave2)) {
//           throw new ErrorServicio("Las contraseñas deben coincidir");
//
//        }
//

        Optional<Usuario> respuesta = usuarioRespositorio.findById(id);

        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();

          usuario.setNombre(nombre);
          usuario.setApellido(apellido);
          usuario.setDocumento(documento);
          usuario.setTelefono(telefono);
          usuario.setMail(mail);
          String encriptada = new BCryptPasswordEncoder().encode(clave);
          usuario.setClave(encriptada);
          usuario.setFechaNacimiento(fechaNacimiento);
          usuario.setCalle(calle);
          usuario.setNumeroCasa(numeroCasa);
          usuario.setZona(zona);
          String idFoto = usuario.getFotoPerfil().getId();
          if (idFoto != null) {
            Foto foto = fotoServicio.Actualizar(idFoto, fotoPerfil);
            usuario.setFotoPerfil(foto);
          } else {
            Foto foto = fotoServicio.Guardar(fotoPerfil);
            usuario.setFotoPerfil(foto);
          }

          if (fotoDNI != null) {
            Foto fotodni = fotoServicio.Guardar(fotoDNI);
            usuario.setFotoDNI(fotodni);
          }

          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("No se encontró el usuario solicitado");

        }

      }

      @Transactional
      public void completarUsuario(MultipartFile fotoPerfil, MultipartFile fotoDNI, String id,
              String calle, String numeroCasa, String telefono) throws ErrorServicio {
        //validar(nombre, apellido, "0", mail,clave);
//        if (!clave.equals(clave2)) {
//           throw new ErrorServicio("Las contraseñas deben coincidir");
//
//        }
//

        Optional<Usuario> respuesta = usuarioRespositorio.findById(id);

        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();

          usuario.setTelefono(telefono);

          usuario.setCalle(calle);
          usuario.setNumeroCasa(numeroCasa);

          if (fotoPerfil != null) {
            Foto foto = fotoServicio.Guardar(fotoPerfil);
            usuario.setFotoPerfil(foto);
          }

          if (fotoDNI != null) {
            Foto fotodni = fotoServicio.Guardar(fotoDNI);
            usuario.setFotoDNI(fotodni);
          }
          usuario.setIsFirstTime(false);
          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("No se encontró el usuario solicitado");

        }

      }

      //si solo queremos guardar la foto del usuario.
      @Transactional
      public void GuardarFotoPerfil(MultipartFile archivo, String idUsuario) throws ErrorServicio {

        Optional<Usuario> respuesta = usuarioRespositorio.findById(idUsuario);

        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();

          Foto foto = fotoServicio.Guardar(archivo);
          usuario.setFotoPerfil(foto);
          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("No se encontró el usuario solicitado");

        }

      }

      @Transactional
      public void GuardarFotoDNI(MultipartFile archivo, String idUsuario) throws ErrorServicio {

        Optional<Usuario> respuesta = usuarioRespositorio.findById(idUsuario);

        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();

          Foto foto = fotoServicio.Guardar(archivo);
          usuario.setFotoDNI(foto);
          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("No se encontró el usuario solicitado");

        }

      }

      //si es necesario encontrar a un usuario por su id.
      public Usuario consultarUsuarioID(String ID) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();
          return usuario;
        } else {
          throw new ErrorServicio("Ese usuario no existe");
        }
      }

      @Async
      public void enviarMailVerificacion(String ID) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();
          System.out.println(usuario.getMail());
          System.out.println(usuario.getId());
          notificacionServicio.send("Gracias por completar tu registro! " + usuario.getNombre()
                                    + "! Para Verificar tu mail haz click aquí: http://localhost:8081/verificarUsuario/"
                                    + usuario.getId(), "Verifica tu mail para poder comenzar a truecar!", usuario.getMail());

        } else {
          throw new ErrorServicio("Ese usuario no existe");
        }
      }

      //una vez que el mail se verifica, se corre este metodo.
      @Transactional
      public void EmailVerificado(String ID) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();
          usuario.setEmailVerified(Boolean.TRUE);
          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("Ese usuario no existe");
        }
      }

      //correr este metodo cuando el usuario se loguea correctamente a la plataforma. esto
      //sirve para darle un mensaje de bienvenida.
      @Transactional
      public void SetFirstTime(String ID) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();
          usuario.setIsFirstTime(Boolean.FALSE);
          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("Ese usuario no existe");
        }
      }

      //este metodo devuelve un booleano, de si ese mail ya existe en la base de datos
      public Boolean verificarEmail(String mail) {
        try {
          String id = usuarioRespositorio.buscarUsuarioPorMail(mail.toLowerCase()).getId();
          System.out.println("");
          Optional<Usuario> respuesta = usuarioRespositorio.findById(id);
          Usuario usuario = respuesta.get();

          if (respuesta.isPresent()) {
            System.out.println("Usuario encontrado");
            return true;
          } else {
            System.out.println("Usuario no encontrado");
            return false;
          }
//          throw new ErrorServicio("Hola, "+usuario.getNombre()+" "+usuario.getApellido()+"! Parece que ya tenés cuenta con nosotros. Porqué no probas Iniciando Sesión?");

        } catch (Exception e) {
          return false;
        }

      }

      //verifica que ambas claves sean las mismas
      public void verificarClave(String Clave1, String Clave2) throws ErrorServicio {
        if (Clave1.equals(Clave2)) {
          System.out.println("Contraseñas OK");

        } else {
          throw new ErrorServicio("Las contraseñas deben coincidir");

        }

      }

      @Transactional
      public void bajaUsuario(String ID) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();
          usuario.setFechaBaja(new Date());
          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("Ese usuario no existe");
        }
      }

      @Transactional
      public void rehabilitarUsuario(String ID) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRespositorio.findById(ID);
        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();
          usuario.setFechaBaja(null);
          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("Ese usuario no existe");
        }
      }

      @Transactional
      public void cargarProducto(String userId, String productID) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRespositorio.findById(userId);
        if (respuesta.isPresent()) {
          Usuario usuario = respuesta.get();

          Optional<Producto> respuestaProducto = productoRepositorio.findById(productID);
          if (respuestaProducto.isPresent()) {

            Producto producto = respuestaProducto.get();

            if (usuario.getListaProductos() == null) {
              List<Producto> listaNueva = new ArrayList<Producto>();
              usuario.setListaProductos(listaNueva);

            }
            List<Producto> listaActualizada = usuario.getListaProductos();
            if (listaActualizada.contains(producto)) {
              listaActualizada.remove(producto);
              listaActualizada.add(producto);
            } else {
              listaActualizada.add(producto);
            }

            usuario.setListaProductos(listaActualizada);

          } else {

            throw new ErrorServicio("Ese Producto no existe");

          }

          usuarioRespositorio.save(usuario);
        } else {
          throw new ErrorServicio("Ese usuario no existe");
        }

      }

      private void validar(String nombre, String apellido, String documento, String mail, String clave) throws ErrorServicio {
        if (nombre == null || nombre.isEmpty()) {
          throw new ErrorServicio("El nombre no puede ser vacío");

        }
        if (apellido == null || apellido.isEmpty()) {
          throw new ErrorServicio("El apellido no puede ser vacío");

        }
        if (documento == null || documento.isEmpty()) {
          throw new ErrorServicio("El documento no puede estar vacío");

        }
        if (mail == null || mail.isEmpty()) {
          throw new ErrorServicio("Debe indicar una dirección de correo electrónico");

        }
        if (clave == null || clave.length() < 6) {
          throw new ErrorServicio("La clave debe contener al menos 6 caracteres.");

        }

      }

      @Override
      public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRespositorio.buscarUsuarioPorMail(mail);
        if (usuario != null) {
          List<GrantedAuthority> permisos = new ArrayList<>();
          GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_CLIENTE");
          permisos.add(p1);

          ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
          HttpSession session = attr.getRequest().getSession();
          session.setAttribute("UsuarioSession", usuario);

          User user = new User(usuario.getMail(), usuario.getClave(), permisos);
          return user;
        } else {
          return null;
        }

      }
    }

  }
}
