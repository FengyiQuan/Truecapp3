package com.example.truecapp3.services;

import com.example.truecapp3.enums.CreditType;
import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Credit;
import com.example.truecapp3.models.User;
import com.example.truecapp3.repositories.CreditRepository;
import com.example.truecapp3.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService {


  @Autowired
  private CreditRepository creditRepository;
  @Autowired
  private NotificationService notificationService;
  @Autowired
  private UserRepository userRepository;


  @Transactional
  public void add(String idUsuario, int suma, CreditType tipoCredito) throws ServiceError {

    Credit credito = new Credit();
    Optional<User> buscarUsuario = userRepository.findById(idUsuario);

    if (buscarUsuario.isPresent()) {
      credito.setAdmissionDate(new Date());
      User usuario = buscarUsuario.get();
      List<Credit> listaCreditos = usuario.getCredits();
      credito.setCreditType(tipoCredito);
      usuario.setCurrentCreditsCount(usuario.getCredits().size() + suma);
      listaCreditos.add(credito);
      usuario.setCredits(listaCreditos);
      userRepository.save(usuario);
      creditRepository.save(credito);
      notificationService.send(
              "Has recibido " + suma + " creditos.", "TruecApp", usuario.getEmail());
    } else {
      throw new ServiceError("No tienes creditos para enviar");
    }
  }

  @Transactional
  public void spend(String idUsuario, int subtraction, CreditType tipoCredito) throws ServiceError {

    Credit credito = new Credit();
    Optional<User> buscarUsuario = userRepository.findById(idUsuario);

    if (buscarUsuario.isPresent()) {
      credito.setExpenditureDate(new Date());
      User usuario = buscarUsuario.get();
      List<Credit> credits = usuario.getCredits();
      credito.setCreditType(tipoCredito);
      usuario.setCurrentCreditsCount(usuario.getCredits().size() - subtraction);
      credits.add(credito);
      usuario.setCredits(credits);
      userRepository.save(usuario);
      creditRepository.save(credito);
      notificationService.send(
              "Has gastado " + subtraction + " creditos.", "TruecApp", usuario.getEmail());
    } else {
      throw new ServiceError("No tienes creditos para gastar");
    }
  }

}
