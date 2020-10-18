package com.example.truecapp3.services;

import com.example.truecapp3.enums.CreditType;
import com.example.truecapp3.enums.ObjectType;
import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Area;
import com.example.truecapp3.models.Credit;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.Photo;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.CreditRepository;
import com.example.truecapp3.repositories.ObjectRepository;
import com.example.truecapp3.repositories.TransactionRepository;
import com.example.truecapp3.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectRepository objectRepository;
  @Autowired
  private PhotoService photoService;
  @Autowired
  private NotificationService notificationService;
  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private CreditRepository creditRepository;

  //la primera vez que se crea un usuario, no se crea con todos los datos, si no con lo minimo
  @Transactional
  public void createNewUser(String name, String lastName, String mail, String pwd) throws ServiceError, MessagingException {
    validar(name, lastName, "0", mail, pwd);

    User user = new User();
    user.setName(name);
    user.setLastName(lastName);
    user.setEmail(mail);
    String encode = new BCryptPasswordEncoder().encode(pwd);
    user.setPassword(encode);
    user.setNewUser(new Date());
    user.setFirstTime(true);
    user.setEmailVerified(false);
    user.setTransactions(new ArrayList<>());
    user.setCredits(new ArrayList<>());
    Credit credit = new Credit();
    credit.setCreditType(CreditType.PURCHASED);
    user.getCredits().add(credit);
    creditRepository.save(credit);
    userRepository.save(user);

    //    notificacionServicio.enviar("Bienvenidos a nuestra plataforma" + user.getNombre() + "! Esperemos que disfrutes mucho de nuestro proyecto.", "Te damos la bienvenida a TruecAPP, " + user.getNombre(), "bortnicaaron@gmail.com");
    notificationService.sendMail("Bienvenidos a nuestra plataforma", user);
  }


  @Transactional
  public void modificarUsuario(MultipartFile fotoPerfil, MultipartFile fotoDNI, String id,
                               String nombre, String apellido, String telefono,
                               Date fechaNacimiento, String address, Area zona,
                               String mail, String clave, String clave2) throws ServiceError {
    //validar(nombre, apellido, "0", mail,clave);
//        if (!clave.equals(clave2)) {
//           throw new ErrorServicio("Las contraseñas deben coincidir");
//
//        }
//

    Optional<User> respuesta = userRepository.findById(id);

    if (respuesta.isPresent()) {
      User usuario = respuesta.get();

      usuario.setName(nombre);
      usuario.setLastName(apellido);
      usuario.setCellphone(telefono);
      usuario.setEmail(mail);
      String encriptada = new BCryptPasswordEncoder().encode(clave);
      usuario.setPassword(encriptada);
      usuario.setBirthday(fechaNacimiento);
      usuario.setAddress(address);
      usuario.setArea(zona);
      String idFoto = usuario.getProfilePic().getId();
      if (idFoto != null) {
        Photo foto = photoService.update(idFoto, fotoPerfil);
        usuario.setProfilePic(foto);
      } else {
        Photo foto = photoService.save(fotoPerfil);
        usuario.setProfilePic(foto);
      }

      if (fotoDNI != null) {
        Photo fotodni = photoService.save(fotoDNI);
        usuario.setIdPic(fotodni);
      }

      userRepository.save(usuario);
    } else {
      throw new ServiceError("No se encontró el usuario solicitado");

    }

  }

  @Transactional
  public void completarUsuario(MultipartFile fotoPerfil, MultipartFile fotoDNI, String id,
                               String address, String telefono) throws ServiceError {
    //validar(nombre, apellido, "0", mail,clave);
//        if (!clave.equals(clave2)) {
//           throw new ErrorServicio("Las contraseñas deben coincidir");
//
//        }
//

    Optional<User> respuesta = userRepository.findById(id);

    if (respuesta.isPresent()) {
      User usuario = respuesta.get();

      usuario.setCellphone(telefono);

      usuario.setAddress(address);

      if (fotoPerfil != null) {
        Photo foto = photoService.save(fotoPerfil);
        usuario.setProfilePic(foto);
      }

      if (fotoDNI != null) {
        Photo fotodni = photoService.save(fotoDNI);
        usuario.setIdPic(fotodni);
      }
      usuario.setFirstTime(false);
      userRepository.save(usuario);
    } else {
      throw new ServiceError("No se encontró el usuario solicitado");

    }

  }

  //si solo queremos guardar la foto del usuario.
  @Transactional
  public void savePhotoProfile(MultipartFile archivo, String idUsuario) throws ServiceError {

    Optional<User> respuesta = userRepository.findById(idUsuario);

    if (respuesta.isPresent()) {
      User usuario = respuesta.get();

      Photo foto = photoService.save(archivo);
      usuario.setProfilePic(foto);
      userRepository.save(usuario);
    } else {
      throw new ServiceError("No se encontró el usuario solicitado");

    }

  }

  @Transactional
  public void saveIdPhoto(MultipartFile archivo, String idUsuario) throws ServiceError {

    Optional<User> respuesta = userRepository.findById(idUsuario);

    if (respuesta.isPresent()) {
      User usuario = respuesta.get();

      Photo foto = photoService.save(archivo);
      usuario.setIdPic(foto);
      userRepository.save(usuario);
    } else {
      throw new ServiceError("No se encontró el usuario solicitado");

    }

  }

  //si es necesario encontrar a un usuario por su id.
  public User consultarUsuarioID(String ID) throws ServiceError {
    Optional<User> respuesta = userRepository.findById(ID);
    if (respuesta.isPresent()) {
      User usuario = respuesta.get();
      return usuario;
    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  @Async
  public void enviarMailVerificacion(String ID) throws ServiceError {
    Optional<User> respuesta = userRepository.findById(ID);
    if (respuesta.isPresent()) {
      User usuario = respuesta.get();
      System.out.println(usuario.getEmail());
      System.out.println(usuario.getId());
      notificationService.send("Gracias por completar tu registro! " + usuario.getName()
                               + "! Para Verificar tu mail haz click aquí: http://localhost:8081/verificarUsuario/"
                               + usuario.getId(), "Verifica tu mail para poder comenzar a truecar!", usuario.getEmail());

    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  //una vez que el mail se verifica, se corre este metodo.
  @Transactional
  public void EmailVerify(String ID) throws ServiceError {
    Optional<User> respuesta = userRepository.findById(ID);
    if (respuesta.isPresent()) {
      User usuario = respuesta.get();
      usuario.setEmailVerified(Boolean.TRUE);
      userRepository.save(usuario);
    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  //correr este metodo cuando el usuario se loguea correctamente a la plataforma. esto
  //sirve para darle un mensaje de bienvenida.
  @Transactional
  public void SetFirstTime(String ID) throws ServiceError {
    Optional<User> respuesta = userRepository.findById(ID);
    if (respuesta.isPresent()) {
      User usuario = respuesta.get();
      usuario.setFirstTime(Boolean.FALSE);
      userRepository.save(usuario);
    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  //este metodo devuelve un booleano, de si ese mail ya existe en la base de datos
  public Boolean verificarEmail(String mail) {
    try {
      String id = userRepository.getUserByEmail(mail.toLowerCase()).getId();
      System.out.println("");
      Optional<User> respuesta = userRepository.findById(id);
      User usuario = respuesta.get();

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
  public void verifyPassword(String pwd1, String pwd2) throws ServiceError {
    if (pwd1.equals(pwd2)) {
      System.out.println("Contraseñas OK");

    } else {
      throw new ServiceError("Las contraseñas deben coincidir");

    }

  }

  @Transactional
  public void dropUser(String ID) throws ServiceError {
    Optional<User> respuesta = userRepository.findById(ID);
    if (respuesta.isPresent()) {
      User usuario = respuesta.get();
      usuario.setDeleteUser(new Date());
      userRepository.save(usuario);
    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  @Transactional
  public void enableUser(String ID) throws ServiceError {
    Optional<User> respuesta = userRepository.findById(ID);
    if (respuesta.isPresent()) {
      User usuario = respuesta.get();
      usuario.setDeleteUser(null);
      userRepository.save(usuario);
    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  @Transactional
  public void loadObjects(String userId, String productID) throws ServiceError {
    Optional<User> response = userRepository.findById(userId);
    if (response.isPresent()) {
      User user = response.get();

      Optional<Object> responseObject = objectRepository.findById(productID);
      if (responseObject.isPresent()) {

        Object object = responseObject.get();

        if (object.getObjectType() == ObjectType.PRODUCT) {
          if (user.getProducts() == null) {
            List<Object> objects = new ArrayList<>();
            user.setProducts(objects);

          }
          List<Object> updatedProducts = user.getProducts();
          if (updatedProducts.contains(object)) {
            updatedProducts.remove(object);
            updatedProducts.add(object);
          } else {
            updatedProducts.add(object);
          }

          user.setProducts(updatedProducts);

        } else if (object.getObjectType() == ObjectType.SERVICE) {
          if (user.getServices() == null) {
            List<Object> objects = new ArrayList<>();
            user.setServices(objects);

          }
          List<Object> updatedServices = user.getServices();
          if (updatedServices.contains(object)) {
            updatedServices.remove(object);
            updatedServices.add(object);
          } else {
            updatedServices.add(object);
          }
          user.setServices(updatedServices);
        }
      } else {
        throw new ServiceError("Ese Producto no existe");
      }
      userRepository.save(user);
    } else {
      throw new ServiceError("Ese usuario no existe");
    }

  }


  private void validar(String nombre, String apellido, String documento, String mail, String clave) throws ServiceError {
    if (nombre == null || nombre.isEmpty()) {
      throw new ServiceError("El nombre no puede ser vacío");

    }
    if (apellido == null || apellido.isEmpty()) {
      throw new ServiceError("El apellido no puede ser vacío");

    }
    if (documento == null || documento.isEmpty()) {
      throw new ServiceError("El documento no puede estar vacío");

    }
    if (mail == null || mail.isEmpty()) {
      throw new ServiceError("Debe indicar una dirección de correo electrónico");

    }
    if (clave == null || clave.length() < 6) {
      throw new ServiceError("La clave debe contener al menos 6 caracteres.");

    }

  }

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    User usuario = userRepository.getUserByEmail(mail);
    if (usuario != null) {
      List<GrantedAuthority> permisos = new ArrayList<>();
      GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_CLIENTE");
      permisos.add(p1);

      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = attr.getRequest().getSession();
      session.setAttribute("UsuarioSession", usuario);

      org.springframework.security.core.userdetails.User user =
              new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(), permisos);
      return user;
    } else {
      return null;
    }

  }
}
