package com.example.truecapp3.services;

import com.example.truecapp3.enums.ObjectType;
import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Area;
import com.example.truecapp3.models.Object;
import com.example.truecapp3.models.Photo;
import com.example.truecapp3.models.Transaction;
import com.example.truecapp3.models.User;
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
  private AreaService areaService;
  @Autowired
  private ObjectService objectService;
  @Autowired
  private PhotoService photoService;
  @Autowired
  private NotificationService notificationService;

  // get user by id with null deleteUser only
  public User getActiveUserById(String id) throws ServiceError {
    User user = userRepository.getActiveUserById(id);
    if (user == null) {
      throw new ServiceError("User does not existed or has been deleted.");
    } else {
      return user;
    }
  }

  public void verifyPassword(String Clave1, String Clave2) throws ServiceError {
    if (Clave1.equals(Clave2)) {
      System.out.println("Contraseñas OK");
    } else {
      throw new ServiceError("Las contraseñas deben coincidir");
    }
  }

  // get user by id with null deleteUser only
  public User getActiveUserByEmail(String mail) throws ServiceError {
    User user = userRepository.getActiveUserByEmail(mail);
    if (user == null) {
      throw new ServiceError("User does not existed or has been deleted.");
    } else {
      return user;
    }
  }

  // find any user by id include deleted users
  public User findAnyUserById(String ID) throws ServiceError {
    Optional<User> optionalUser = userRepository.findById(ID);
    if (optionalUser.isPresent()) {
      return optionalUser.get();
    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  // true means email is valid to register
  public boolean checkEmail(String mail) {
    return userRepository.getUserByEmail(mail) == null;

  }

  //create a user at first time, do not ask all the information
  @Transactional
  public User createNewUser(String name, String lastName, String mail, String pwd) throws ServiceError, MessagingException {
    validateName(name);
    validateLastName(lastName);
    validateEmail(mail);
    validatePassword(pwd);
    if (!checkEmail(mail)) {
      throw new ServiceError("Email has been registered. Please use another email.");
    }

    User user = new User();
    user.setName(name);
    user.setLastName(lastName);
    user.setEmail(mail.toLowerCase());
    String encode = new BCryptPasswordEncoder().encode(pwd);
    user.setPassword(encode);

    User saveUser;
    try {
      saveUser = userRepository.save(user);
    } catch (Exception e) {
      throw new ServiceError("Operation terminates.");
    }
    notificationService.sendMail("Bienvenidos a nuestra plataforma", user);
    return saveUser;
  }

  @Transactional
  public User completeUser(MultipartFile fotoPerfil, MultipartFile fotoDNI, String id,
                           String calle, String numeroCasa, String telefono, Area area, Date dob) throws ServiceError {
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

      usuario.setStreet(calle);
      usuario.setAptNumber(numeroCasa);
      usuario.setBirthday(dob);

//      Area area = areaService.createArea(areaName, provinceOrState);
      usuario.setArea(area);

      if (!fotoPerfil.isEmpty()) {
        Photo foto = photoService.save(fotoPerfil);
        usuario.setProfilePic(foto);
      }

      if (!fotoDNI.isEmpty()) {
        Photo fotodni = photoService.save(fotoDNI);
        usuario.setIdPic(fotodni);
      }
      usuario.setFirstTime(false);
      return userRepository.save(usuario);
    } else {
      throw new ServiceError("No se encontró el usuario solicitado");
    }
  }

  @Transactional
  public User changePassword(String userId, String oldPassword, String newPassword1, String newPassword2) throws ServiceError {
    verifyPassword(newPassword1, newPassword2);
    validatePassword(newPassword1);
    User user = getActiveUserById(userId);
    String encode = new BCryptPasswordEncoder().encode(oldPassword);
    if (user.getPassword().equals(encode)) {
      String newEncode = new BCryptPasswordEncoder().encode(newPassword1);
      user.setPassword(newEncode);
      return userRepository.save(user);
    } else {
      throw new ServiceError("Passwords don't match. Please try again.");
    }
  }

  @Transactional
  public User changeEmail(String userId, String oldEmail, String newEmail) throws ServiceError {
    validateEmail(newEmail);
    User user = getActiveUserById(userId);
    if (user.getEmail().equalsIgnoreCase(oldEmail)) {
      user.setPassword(newEmail.toLowerCase());
      return userRepository.save(user);
    } else {
      throw new ServiceError("Emails don't match. Please try again.");
    }
  }

  @Transactional
  public User changeArea(String userId, String areaName, String provinceOrState) throws ServiceError {
    Area a = areaService.createArea(areaName, provinceOrState);
    User user = getActiveUserById(userId);
    user.setArea(a);
    return userRepository.save(user);
  }


  @Transactional
  public User modifyUser(MultipartFile fotoPerfil, MultipartFile fotoDNI, String userId, String name,
                         String lastName, String street, String aptNum, String phone, Date dob,
                         Area area) throws ServiceError {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setName(name);
      user.setLastName(lastName);
      user.setCellphone(phone);
      user.setBirthday(dob);
      user.setStreet(street);
      user.setAptNumber(aptNum);
      user.setArea(area);

      if (!fotoPerfil.isEmpty()) {
        Photo foto = photoService.save(fotoPerfil);
        user.setProfilePic(foto);
      }

      if (!fotoDNI.isEmpty()) {
        Photo fotodni = photoService.save(fotoDNI);
        user.setIdPic(fotodni);
      }
      userRepository.save(user);
      return userRepository.save(user);
    } else {
      throw new ServiceError("No se encontró el usuario solicitado");
    }

  }

//  @Transactional
//  public User bindTransaction(User user, Transaction transaction) throws ServiceError {
//    if (user!=null&&transaction!=null){
//      List<Transaction> trans = user.getTransactions();
//      trans.add(transaction);
//      user.setTransactions(trans);
//      return userRepository.save(user);
//    }else{
//      throw new ServiceError("User or transaction not exists.");
//    }
//
//  }

  @Transactional
  public User incrementSuccessfulTradesCount(String userId) throws ServiceError {
    User user = userRepository.getActiveUserById(userId);
    if (user == null) {
      throw new ServiceError("incrementSuccessfulTradesCount operation fail.");
    }

    user.setSuccessfulTradesCount(user.getSuccessfulTradesCount() + 1);
    return userRepository.save(user);

  }


  //si solo queremos guardar la foto del usuario.
  @Transactional
  public User saveProfilePhoto(MultipartFile file, String userId) throws ServiceError {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      Photo photo = photoService.save(file);
      user.setProfilePic(photo);
      return userRepository.save(user);
    } else {
      throw new ServiceError("No se encontró el usuario solicitado");
    }
  }

  @Transactional
  public User saveIdPhoto(MultipartFile file, String userId) throws ServiceError {
    Optional<User> optionalUser = userRepository.findById(userId);

    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      Photo photo = photoService.save(file);
      user.setIdPic(photo);
      return userRepository.save(user);
    } else {
      throw new ServiceError("No se encontró el usuario solicitado");
    }
  }


  @Transactional
  public User bindObject(String userId, String productID) throws ServiceError {
    User user = getActiveUserById(userId);

    Object object = objectService.getObjectById(productID);

    List<Object> updatedProducts = user.getObjects();
    if (updatedProducts.contains(object)) {
      updatedProducts.remove(object);
      updatedProducts.add(object);
    } else {
      updatedProducts.add(object);
    }
    user.setObjects(updatedProducts);

    return userRepository.save(user);
  }


  @Transactional
  public void setEmailVerifySuccess(String ID) throws ServiceError {
    User user = getActiveUserById(ID);
    userRepository.verifySuccessById(user.getId());
  }


  @Transactional
  public void SetFirstTimeToFalse(String ID) throws ServiceError {
    User user = getActiveUserById(ID);
    userRepository.markFirstTime(user.getId());
  }

  // check if a user is able to make transaction
  @Transactional
  public void checkStatusOfUser(String id) throws ServiceError {
    User user = getActiveUserById(id);
    if (!user.checkStatus()) {
      throw new ServiceError("This account is not able to make transactions.");
    }
  }


  @Transactional
  public void dropUser(String ID) throws ServiceError {
    Optional<User> optionalUser = userRepository.findById(ID);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setDeleteUser(new Date());
      userRepository.save(user);
    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  @Transactional
  public void enableUser(String ID) throws ServiceError {
    Optional<User> optionalUser = userRepository.findById(ID);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      user.setDeleteUser(null);
      userRepository.save(user);
    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }

  @Async
  public void sendEmailVerification(String ID) throws ServiceError {
    Optional<User> respuesta = userRepository.findById(ID);
    if (respuesta.isPresent()) {
      User usuario = respuesta.get();
      System.out.println(usuario.getEmail());
      System.out.println(usuario.getId());
      notificationService.sendTextMail("Gracias por completar tu registro! " + usuario.getName()
                                       + "! Para Verificar tu mail haz click aquí: http://localhost:8081/verificarUsuario/"
                                       + usuario.getId(), "Verifica tu mail para poder comenzar a truecar!", usuario.getEmail());

    } else {
      throw new ServiceError("Ese usuario no existe");
    }
  }


  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    User user = userRepository.getUserByEmail(mail);
    if (user != null) {
      List<GrantedAuthority> permisos = new ArrayList<>();
      switch (user.getUserType()) {

        case ADMIN:
          permisos.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
          break;
        case CLIENT:
          permisos.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
          break;
        default:
          break;
      }

      ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
      HttpSession session = attr.getRequest().getSession();
      session.setAttribute("UsuarioSession", user);

      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), permisos);
    } else {
      return null;
    }
  }


  private void validateName(String name) throws ServiceError {
    if (name == null || name.isEmpty()) {
      throw new ServiceError("El nombre no puede ser vacío");
    }
  }

  private void validateLastName(String lastName) throws ServiceError {
    if (lastName == null || lastName.isEmpty()) {
      throw new ServiceError("El apellido no puede ser vacío");
    }
  }

  private void validateEmail(String mail) throws ServiceError {
    if (mail == null || mail.isEmpty()) {
      throw new ServiceError("Debe indicar una dirección de correo electrónico");
    }
  }

  private void validatePassword(String pwd) throws ServiceError {
    if (pwd == null || pwd.length() < 6) {
      throw new ServiceError("La clave debe contener al menos 6 caracteres.");
    }
  }


}
