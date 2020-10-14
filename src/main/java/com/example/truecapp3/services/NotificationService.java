//package com.example.truecapp3.services;
//
//import com.example.truecapp3.models.Transaction;
//import com.example.truecapp3.models.User;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//@Service
//public class NotificationService {
//  @Autowired
//  private JavaMailSender mailSender;
//
//  @Async
//  public void send(String cuerpo, String titulo, String mail) {
//    SimpleMailMessage mensaje = new SimpleMailMessage();
//    mensaje.setTo(mail);
//    mensaje.setFrom("no-reply@truecapp");
//    mensaje.setSubject(titulo);
//    mensaje.setText(cuerpo);
//
//    mailSender.send(mensaje);
//
//  }
//
//  @Async
//  public void sendMail(String titulo, User usuario) throws AddressException, javax.mail.MessagingException {
//
//    // Recipient's email ID needs to be mentioned.
//    //String to = usuario.getMail();
//    String to = "bortnicaaron@gmail.com";
//    // Sender's email ID needs to be mentioned
//    String from = "grupo2egg@gmail.com";
//    final String username = "grupo2egg@gmail.com";//change accordingly
//    final String password = "bheqdtwkwmlanxzw";//change accordingly
//
//    // Assuming you are sending email through relay.jangosmtp.net
//    String host = "smtp.gmail.com";
//
//    Properties props = new Properties();
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.starttls.enable", "true");
//    props.put("mail.smtp.host", host);
//    props.put("mail.smtp.port", "587");
//
//    // Get the Session object.
//    Session session = Session.getInstance(props,
//            new javax.mail.Authenticator() {
//              protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//              }
//            });
//
//    try {
//      // Create a default MimeMessage object.
//      Message message = new MimeMessage(session);
//
//      // Set From: header field of the header.
//      message.setFrom(new InternetAddress(from));
//
//      // Set To: header field of the header.
//      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//
//      // Set Subject: header field
//      message.setSubject(titulo);
//
//      // Send the actual HTML message, as big as you like
//      message.setContent(
//              "<html xmlns=\"\\&quot;http://www.w3.org/1999/xhtml&quot;\"><head>\n" +
//              "   <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
//              "   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//              "   <title>Simples-Minimalistic Responsive Template</title>\n" +
//              "   \n" +
//              "   <style type=\"text/css\">\n" +
//              "      /* Client-specific Styles */\n" +
//              "      #outlook a {padding:0;} /* Force Outlook to provide a \"view in browser\" menu link. */\n"
//              +
//              "      body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n"
//              +
//              "      /* Prevent Webkit and Windows Mobile platforms from changing default font sizes, while not breaking desktop design. */\n"
//              +
//              "      .ExternalClass {width:100%;} /* Force Hotmail to display emails at full width */\n"
//              +
//              "      .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing.*/\n"
//              +
//              "      #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}\n"
//              +
//              "      img {outline:none; text-decoration:none;border:none; -ms-interpolation-mode: bicubic;}\n"
//              +
//              "      a img {border:none;}\n" +
//              "      .image_fix {display:block;}\n" +
//              "      p {margin: 0px 0px !important;}\n" +
//              "      table td {border-collapse: collapse;}\n" +
//              "      table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }\n"
//              +
//              "      a {color: #0a8cce;text-decoration: none;text-decoration:none!important;}\n" +
//              "      /*STYLES*/\n" +
//              "      table[class=full] { width: 100%; clear: both; }\n" +
//              "      /*IPAD STYLES*/\n" +
//              "      @media only screen and (max-width: 640px) {\n" +
//              "      a[href^=\"tel\"], a[href^=\"sms\"] {\n" +
//              "      text-decoration: none;\n" +
//              "      color: #0a8cce; /* or whatever your want */\n" +
//              "      pointer-events: none;\n" +
//              "      cursor: default;\n" +
//              "      }\n" +
//              "      .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" +
//              "      text-decoration: default;\n" +
//              "      color: #0a8cce !important;\n" +
//              "      pointer-events: auto;\n" +
//              "      cursor: default;\n" +
//              "      }\n" +
//              "      table[class=devicewidth] {width: 440px!important;text-align:center!important;}\n"
//              +
//              "      table[class=devicewidthinner] {width: 420px!important;text-align:center!important;}\n"
//              +
//              "      img[class=banner] {width: 440px!important;height:220px!important;}\n" +
//              "      img[class=colimg2] {width: 440px!important;height:220px!important;}\n" +
//              "      \n" +
//              "      \n" +
//              "      }\n" +
//              "      /*IPHONE STYLES*/\n" +
//              "      @media only screen and (max-width: 480px) {\n" +
//              "      a[href^=\"tel\"], a[href^=\"sms\"] {\n" +
//              "      text-decoration: none;\n" +
//              "      color: #0a8cce; /* or whatever your want */\n" +
//              "      pointer-events: none;\n" +
//              "      cursor: default;\n" +
//              "      }\n" +
//              "      .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" +
//              "      text-decoration: default;\n" +
//              "      color: #0a8cce !important; \n" +
//              "      pointer-events: auto;\n" +
//              "      cursor: default;\n" +
//              "      }\n" +
//              "      table[class=devicewidth] {width: 280px!important;text-align:center!important;}\n"
//              +
//              "      table[class=devicewidthinner] {width: 260px!important;text-align:center!important;}\n"
//              +
//              "      img[class=banner] {width: 280px!important;height:140px!important;}\n" +
//              "      img[class=colimg2] {width: 280px!important;height:140px!important;}\n" +
//              "      td[class=mobile-hide]{display:none!important;}\n" +
//              "      td[class=\"padding-bottom25\"]{padding-bottom:25px!important;}\n" +
//              "     \n" +
//              "      }\n" +
//              "   </style>\n" +
//              "</head>\n" +
//              "<body>\n" +
//              "   <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"preheader\">\n"
//              +
//              "      <tbody>\n" +
//              "         <tr>\n" +
//              "            <td>\n" +
//              "               <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#F7FAFF\">\n"
//              +
//              "                  <tbody>\n" +
//              "                     <tr>\n" +
//              "                        <td width=\"100%\">\n" +
//              "                           <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                              <tbody>\n" +
//              "                                 <!-- Spacing -->\n" +
//              "                                 <tr>\n" +
//              "                                    <td width=\"100%\" height=\"65\"></td>\n" +
//              "                                 </tr>\n" +
//              "                                 <!-- Spacing -->\n" +
//              "                                 \n" +
//              "                                 \n" +
//              "                                 \n" +
//              "                                 \n" +
//              "                              </tbody>\n" +
//              "                           </table>\n" +
//              "                        </td>\n" +
//              "                     </tr>\n" +
//              "                  </tbody>\n" +
//              "               </table>\n" +
//              "            </td>\n" +
//              "         </tr>\n" +
//              "      </tbody>\n" +
//              "   </table>\n" +
//              "  <!-- Start of preheader --><table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"preheader\">\n"
//              +
//              "     <tbody>\n" +
//              "        <tr>\n" +
//              "           <td>\n" +
//              "              <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#FF5856\">\n"
//              +
//              "                 <tbody>\n" +
//              "                    <tr>\n" +
//              "                       <td width=\"100%\">\n" +
//              "                          <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                             <tbody>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"20\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" align=\"riht\" valign=\"middle\" style=\"font-family: Helvetica, arial, sans-serif; font-size: 13px;padding:0px 20px 0px\"><a href=\"#\" style=\"text-decoration: none, font-size: 20px; color: #eacb3c\">TruecAPP</a> \n"
//              +
//              "                                   </td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"20\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                             </tbody>\n" +
//              "                          </table>\n" +
//              "                       </td>\n" +
//              "                    </tr>\n" +
//              "                 </tbody>\n" +
//              "              </table>\n" +
//              "           </td>\n" +
//              "        </tr>\n" +
//              "     </tbody>\n" +
//              "  </table>\n" +
//              "  \n" +
//              "  <!-- End of preheader -->\n" +
//              "\n" +
//              "  <!-- Start of header -->\n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"header\">\n"
//              +
//              "   <tbody>\n" +
//              "      <tr>\n" +
//              "         <td>\n" +
//              "            <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#CADFFF\">\n"
//              +
//              "               <tbody>\n" +
//              "                  <tr>\n" +
//              "                     <td width=\"100%\">\n" +
//              "                        <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                           <tbody>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              <tr>\n" +
//              "                                 <td height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                              </tr>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              <tr>\n" +
//              "                                 <td>\n" +
//              "                                    <!-- logo -->\n" +
//              "                                    <table width=\"140\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                       <tbody>\n" +
//              "                                          <tr>\n" +
//              "                                             <td width=\"169\" height=\"45\" align=\"center\">\n"
//              +
//              "                                                <div class=\"imgpop\">\n" +
//              "                                                   <a target=\"_blank\" href=\"#\">\n"
//              +
//              "                                                      <img src=\"https://assets-ouch.icons8.com/thumb/366/3577dd0e-ce2d-46d5-8ac4-4bf09e77ba8b.png\" alt=\"\" border=\"0\" style=\"display:block; border:none; outline:none; text-decoration:none;\" height=\"185\">\n"
//              +
//              "                                                   </a>\n" +
//              "                                                </div>\n" +
//              "                                             </td>\n" +
//              "                                          </tr>\n" +
//              "                                       </tbody>\n" +
//              "                                    </table>\n" +
//              "                                    <!-- end of logo -->\n" +
//              "                                 </td>\n" +
//              "                              </tr>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              <tr>\n" +
//              "                                 <td height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                              </tr>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                           </tbody>\n" +
//              "                        </table>\n" +
//              "                     </td>\n" +
//              "                  </tr>\n" +
//              "               </tbody>\n" +
//              "            </table>\n" +
//              "         </td>\n" +
//              "      </tr>\n" +
//              "   </tbody>\n" +
//              "  </table>\n" +
//              "  <!-- End of Header -->\n" +
//              "<table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"full-text\">\n"
//              +
//              "  <tbody>\n" +
//              "     <tr>\n" +
//              "        <td>\n" +
//              "           <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "              <tbody>\n" +
//              "                 <tr>\n" +
//              "                    <td width=\"100%\">\n" +
//              "                       <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#CADFFF\">\n"
//              +
//              "                          <tbody>\n" +
//              "                             <!-- Spacing -->\n" +
//              "                             <tr>\n" +
//              "                                <td height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                             </tr>\n" +
//              "                             <!-- Spacing -->\n" +
//              "                             <tr>\n" +
//              "                                <td>\n" +
//              "                                   <table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                      <tbody>\n" +
//              "                                         <!-- Title -->\n" +
//              "                                         <tr>\n" +
//              "                                            <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 27px; color: #333333; text-align:center; line-height: 30px;\" st-title=\"fulltext-heading\">!Bienvenidos a nuestra plataforma "
//              + usuario.getName() + "!</td>\n" +
//              "                                         </tr>\n" +
//              "                                         <!-- End of Title -->\n" +
//              "                                         \n" +
//              "                                         \n" +
//              "                                         <!-- End of spacing -->\n" +
//              "                                         \n" +
//              "                                         \n" +
//              "                                         <!-- End of content -->\n" +
//              "                                      </tbody>\n" +
//              "                                   </table>\n" +
//              "                                </td>\n" +
//              "                             </tr>\n" +
//              "                             <!-- Spacing -->\n" +
//              "                             <tr>\n" +
//              "                                <td height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                             </tr>\n" +
//              "                             <!-- Spacing -->\n" +
//              "                          </tbody>\n" +
//              "                       </table>\n" +
//              "                    </td>\n" +
//              "                 </tr>\n" +
//              "              </tbody>\n" +
//              "           </table>\n" +
//              "        </td>\n" +
//              "     </tr>\n" +
//              "  </tbody>\n" +
//              "</table>\n" +
//              "  \n" +
//              "<table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"seperator\">\n"
//              +
//              "  <tbody>\n" +
//              "     <tr>\n" +
//              "        <td>\n" +
//              "           <table width=\"600\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"devicewidth\">\n"
//              +
//              "              <tbody>\n" +
//              "                 <tr>\n" +
//              "                    <td align=\"center\" height=\"30\" style=\"font-size:1px; line-height:1px;\" bgcolor=\"#CADFFF\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "                 <tr>\n" +
//              "                    <td width=\"550\" align=\"center\" height=\"1\" bgcolor=\"#d1d1d1\" style=\"font-size:1px; line-height:1px;\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "                 <tr>\n" +
//              "                    <td align=\"center\" height=\"10\" style=\"font-size:1px; line-height:1px;\" bgcolor=\"#EDF4FF\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "              </tbody>\n" +
//              "           </table>\n" +
//              "        </td>\n" +
//              "     </tr>\n" +
//              "  </tbody>\n" +
//              "</table>\n" +
//              "<!-- Start of main-banner -->\n" +
//              "<table cellpadding=\"0\" bgcolor=\"#F7FAFF\" cellspacing=\"0\" border=\"0\" width=\"100%\">\n"
//              +
//              "  <tbody><tr>\n" +
//              "   <td align=\"center\" bgcolor=\"#F7FAFF\" style=\"padding:0px 20px\">\n" +
//              "    <table border=\"0\" bgcolor=\"#EDF4FF\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\">\n"
//              +
//              "     <tbody><tr>\n" +
//              "      <td style=\"font-family:'Arial',sans-serif;font-size:14px;line-height:22px;color:#939598;padding:30px 30px 30px\" width=\"100%\" valign=\"top\" align=\"left\">\n"
//              +
//              "   <span style=\"color:#000000\"><b>¡Bienvenido a tu cuenta TruecAPP!</b></span><br><br>\n"
//              +
//              "   Su cuenta sirve para donar a fundaciones de ayuda o truequear con otros usuarios, todo desde desde su cuantea personal.<br><br>\n"
//              +
//              "   <span style=\"color:#000000\"><b>Accediendo a su cuenta</b></span><br><br>\n" +
//              "   Puede realizar cambios en su cuenta en cualquier momento iniciando sesión en el portal web.\n"
//              +
//              "   <br><br>Que tenga una gratificante experiencia.<br><br>\n" +
//              "   El equipo de Trueccap!\n" +
//              "   <br></td>\n" +
//              "     </tr>\n" +
//              "    </tbody></table>\n" +
//              "   </td>\n" +
//              "  </tr>\n" +
//              "</tbody></table><!-- Start of main-banner -->\n" +
//              "  \n" +
//              "  \n" +
//              "  <!--mio1-->\n" +
//              "\n" +
//              "  \n" +
//              "  \n" +
//              "  <!-- end of 2 columns -->\n" +
//              "\n" +
//              "  \n" +
//              "\n" +
//              "<!-- Start of seperator --><!-- Start of seperator -->\n" +
//              "  \n" +
//              "  \n" +
//              "  <!-- End of seperator -->  \n" +
//              "  <!-- Start of Postfooter -->\n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"postfooter\">\n"
//              +
//              "     <tbody>\n" +
//              "        <tr>\n" +
//              "           <td>\n" +
//              "              <table width=\"600\" bgcolor=\"#eacb3c\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                 <tbody>\n" +
//              "                    <tr>\n" +
//              "                       <td width=\"100%\">\n" +
//              "                          <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                             <tbody>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"15\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "  \n" +
//              "                                <tr>\n" +
//              "                                   <td align=\"center\" valign=\"middle\" style=\"font-family: Helvetica, arial, sans-serif; font-size: 14px;color: #666666\" st-content=\"postfooter\">Para Verificar tu mail haz click aquí:<a href=\"#\" style=\"text-decoration: none; color: #0a8cce\"> Verificar</a> \n"
//              +
//              "                                   </td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"15\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                             </tbody>\n" +
//              "                          </table>\n" +
//              "                       </td>\n" +
//              "                    </tr>\n" +
//              "                 </tbody>\n" +
//              "              </table>\n" +
//              "           </td>\n" +
//              "        </tr>\n" +
//              "     </tbody>\n" +
//              "  </table>\n" +
//              "  <!-- End of postfooter -->\n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"preheader\">\n"
//              +
//              "   <tbody>\n" +
//              "      <tr>\n" +
//              "         <td>\n" +
//              "            <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#F7FAFF\">\n"
//              +
//              "               <tbody>\n" +
//              "                  <tr>\n" +
//              "                     <td width=\"100%\">\n" +
//              "                        <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                           <tbody>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              <tr>\n" +
//              "                                 <td width=\"100%\" height=\"65\"></td>\n" +
//              "                              </tr>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              \n" +
//              "                              \n" +
//              "                              \n" +
//              "                              \n" +
//              "                           </tbody>\n" +
//              "                        </table>\n" +
//              "                     </td>\n" +
//              "                  </tr>\n" +
//              "               </tbody>\n" +
//              "            </table>\n" +
//              "         </td>\n" +
//              "      </tr>\n" +
//              "   </tbody>\n" +
//              "</table>\n" +
//              "</body></html>",
//              "text/html");
//
//      // Send message
//      Transport.send(message);
//
//      System.out.println("Sent message successfully....");
//
//    } catch (MessagingException e) {
//      e.printStackTrace();
//      throw new RuntimeException(e);
//    }
//
//  }
//
//  @Async
//  public void enviarIniciarOferta(String titulo, Transaction transaccion) throws AddressException, javax.mail.MessagingException {
//
//    // Recipient's email ID needs to be mentioned.
//    String to = transaccion.getUsuariosInvolucrados().get(1).getMail();
//
//    // Sender's email ID needs to be mentioned
//    String from = "grupo2egg@gmail.com";
//    final String username = "grupo2egg@gmail.com";//change accordingly
//    final String password = "bheqdtwkwmlanxzw";//change accordingly
//
//    // Assuming you are sending email through relay.jangosmtp.net
//    String host = "smtp.gmail.com";
//
//    Properties props = new Properties();
//    props.put("mail.smtp.auth", "true");
//    props.put("mail.smtp.starttls.enable", "true");
//    props.put("mail.smtp.host", host);
//    props.put("mail.smtp.port", "587");
//
//    // Get the Session object.
//    Session session = Session.getInstance(props,
//            new javax.mail.Authenticator() {
//              protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//              }
//            });
//
//    try {
//      // Create a default MimeMessage object.
//      Message message = new MimeMessage(session);
//
//      // Set From: header field of the header.
//      message.setFrom(new InternetAddress(from));
//
//      // Set To: header field of the header.
//      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//
//      // Set Subject: header field
//      message.setSubject(titulo);
//
//      // Send the actual HTML message, as big as you like
//      message.setContent(
//              "<html xmlns=\"\\&quot;http://www.w3.org/1999/xhtml&quot;\"><head>\n" +
//              "   <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
//              "   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//              "   <title>Simples-Minimalistic Responsive Template</title>\n" +
//              "   \n" +
//              "   <style type=\"text/css\">\n" +
//              "      /* Client-specific Styles */\n" +
//              "      #outlook a {padding:0;} /* Force Outlook to provide a \"view in browser\" menu link. */\n"
//              +
//              "      body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n"
//              +
//              "      /* Prevent Webkit and Windows Mobile platforms from changing default font sizes, while not breaking desktop design. */\n"
//              +
//              "      .ExternalClass {width:100%;} /* Force Hotmail to display emails at full width */\n"
//              +
//              "      .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing.*/\n"
//              +
//              "      #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}\n"
//              +
//              "      img {outline:none; text-decoration:none;border:none; -ms-interpolation-mode: bicubic;}\n"
//              +
//              "      a img {border:none;}\n" +
//              "      .image_fix {display:block;}\n" +
//              "      p {margin: 0px 0px !important;}\n" +
//              "      table td {border-collapse: collapse;}\n" +
//              "      table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }\n"
//              +
//              "      a {color: #0a8cce;text-decoration: none;text-decoration:none!important;}\n" +
//              "      /*STYLES*/\n" +
//              "      table[class=full] { width: 100%; clear: both; }\n" +
//              "      /*IPAD STYLES*/\n" +
//              "      @media only screen and (max-width: 640px) {\n" +
//              "      a[href^=\"tel\"], a[href^=\"sms\"] {\n" +
//              "      text-decoration: none;\n" +
//              "      color: #0a8cce; /* or whatever your want */\n" +
//              "      pointer-events: none;\n" +
//              "      cursor: default;\n" +
//              "      }\n" +
//              "      .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" +
//              "      text-decoration: default;\n" +
//              "      color: #0a8cce !important;\n" +
//              "      pointer-events: auto;\n" +
//              "      cursor: default;\n" +
//              "      }\n" +
//              "      table[class=devicewidth] {width: 440px!important;text-align:center!important;}\n"
//              +
//              "      table[class=devicewidthinner] {width: 420px!important;text-align:center!important;}\n"
//              +
//              "      img[class=banner] {width: 440px!important;height:220px!important;}\n" +
//              "      img[class=colimg2] {width: 440px!important;height:220px!important;}\n" +
//              "      \n" +
//              "      \n" +
//              "      }\n" +
//              "      /*IPHONE STYLES*/\n" +
//              "      @media only screen and (max-width: 480px) {\n" +
//              "      a[href^=\"tel\"], a[href^=\"sms\"] {\n" +
//              "      text-decoration: none;\n" +
//              "      color: #0a8cce; /* or whatever your want */\n" +
//              "      pointer-events: none;\n" +
//              "      cursor: default;\n" +
//              "      }\n" +
//              "      .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" +
//              "      text-decoration: default;\n" +
//              "      color: #0a8cce !important; \n" +
//              "      pointer-events: auto;\n" +
//              "      cursor: default;\n" +
//              "      }\n" +
//              "      table[class=devicewidth] {width: 280px!important;text-align:center!important;}\n"
//              +
//              "      table[class=devicewidthinner] {width: 260px!important;text-align:center!important;}\n"
//              +
//              "      img[class=banner] {width: 280px!important;height:140px!important;}\n" +
//              "      img[class=colimg2] {width: 280px!important;height:140px!important;}\n" +
//              "      td[class=mobile-hide]{display:none!important;}\n" +
//              "      td[class=\"padding-bottom25\"]{padding-bottom:25px!important;}\n" +
//              "     \n" +
//              "      }\n" +
//              "   </style>\n" +
//              "</head>\n" +
//              "<body>\n" +
//              "   <!-- Start of preheader -->\n" +
//              "   <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"preheader\">\n"
//              +
//              "      <tbody>\n" +
//              "         <tr>\n" +
//              "            <td>\n" +
//              "               <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#F7FAFF\">\n"
//              +
//              "                  <tbody>\n" +
//              "                     <tr>\n" +
//              "                        <td width=\"100%\">\n" +
//              "                           <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                              <tbody>\n" +
//              "                                 <!-- Spacing -->\n" +
//              "                                 <tr>\n" +
//              "                                    <td width=\"100%\" height=\"65\"></td>\n" +
//              "                                 </tr>\n" +
//              "                                 <!-- Spacing -->\n" +
//              "                                 \n" +
//              "                                 \n" +
//              "                                 \n" +
//              "                                 \n" +
//              "                              </tbody>\n" +
//              "                           </table>\n" +
//              "                        </td>\n" +
//              "                     </tr>\n" +
//              "                  </tbody>\n" +
//              "               </table>\n" +
//              "            </td>\n" +
//              "         </tr>\n" +
//              "      </tbody>\n" +
//              "   </table>\n" +
//              "  <!-- Start of preheader -->\n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"preheader\">\n"
//              +
//              "     <tbody>\n" +
//              "        <tr>\n" +
//              "           <td>\n" +
//              "              <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#FF5856\">\n"
//              +
//              "                 <tbody>\n" +
//              "                    <tr>\n" +
//              "                       <td width=\"100%\">\n" +
//              "                          <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                             <tbody>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"20\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" align=\"riht\" valign=\"middle\" style=\"font-family: Helvetica, arial, sans-serif; font-size: 13px;padding:0px 20px 0px\"><a href=\"#\" style=\"text-decoration: none, font-size: 20px; color: #eacb3c\">TruecAPP</a> \n"
//              +
//              "                                   </td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"20\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                             </tbody>\n" +
//              "                          </table>\n" +
//              "                       </td>\n" +
//              "                    </tr>\n" +
//              "                 </tbody>\n" +
//              "              </table>\n" +
//              "           </td>\n" +
//              "        </tr>\n" +
//              "     </tbody>\n" +
//              "  </table>\n" +
//              "  \n" +
//              "  <!-- End of preheader -->\n" +
//              "\n" +
//              "  <!-- Start of header -->\n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"header\">\n"
//              +
//              "   <tbody>\n" +
//              "      <tr>\n" +
//              "         <td>\n" +
//              "            <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#CADFFF\">\n"
//              +
//              "               <tbody>\n" +
//              "                  <tr>\n" +
//              "                     <td width=\"100%\">\n" +
//              "                        <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                           <tbody>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              <tr>\n" +
//              "                                 <td height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                              </tr>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              <tr>\n" +
//              "                                 <td>\n" +
//              "                                    <!-- logo -->\n" +
//              "                                    <table width=\"140\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                       <tbody>\n" +
//              "                                          <tr>\n" +
//              "                                             <td width=\"169\" height=\"45\" align=\"center\">\n"
//              +
//              "                                                <div class=\"imgpop\">\n" +
//              "                                                   <a target=\"_blank\" href=\"#\">\n"
//              +
//              "                                                      <img src=\"https://www.hannaharnett.com/static/media/kingdom-upgrade-account.ac619fe5.png\" alt=\"\" border=\"0\" style=\"display:block; border:none; outline:none; text-decoration:none;\" height=\"160\" width=\"169\">\n"
//              +
//              "                                                   </a>\n" +
//              "                                                </div>\n" +
//              "                                             </td>\n" +
//              "                                          </tr>\n" +
//              "                                       </tbody>\n" +
//              "                                    </table>\n" +
//              "                                    <!-- end of logo -->\n" +
//              "                                 </td>\n" +
//              "                              </tr>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              <tr>\n" +
//              "                                 <td height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                              </tr>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                           </tbody>\n" +
//              "                        </table>\n" +
//              "                     </td>\n" +
//              "                  </tr>\n" +
//              "               </tbody>\n" +
//              "            </table>\n" +
//              "         </td>\n" +
//              "      </tr>\n" +
//              "   </tbody>\n" +
//              "  </table>\n" +
//              "  <!-- End of Header -->\n" +
//              "<table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"full-text\">\n"
//              +
//              "  <tbody>\n" +
//              "     <tr>\n" +
//              "        <td>\n" +
//              "           <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "              <tbody>\n" +
//              "                 <tr>\n" +
//              "                    <td width=\"100%\">\n" +
//              "                       <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#CADFFF\">\n"
//              +
//              "                          <tbody>\n" +
//              "                             <!-- Spacing -->\n" +
//              "                             <tr>\n" +
//              "                                <td height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                             </tr>\n" +
//              "                             <!-- Spacing -->\n" +
//              "                             <tr>\n" +
//              "                                <td>\n" +
//              "                                   <table width=\"560\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                      <tbody>\n" +
//              "                                         <!-- Title -->\n" +
//              "                                         <tr>\n" +
//              "                                            <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 30px; color: #333333; text-align:center; line-height: 30px;\" st-title=\"fulltext-heading\">¡Nuevo Trueque!</td>\n"
//              +
//              "                                         </tr>\n" +
//              "                                         <!-- End of Title -->\n" +
//              "                                         <!-- spacing -->\n" +
//              "                                         <tr>\n" +
//              "                                            <td width=\"100%\" height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                                         </tr>\n" +
//              "                                         <!-- End of spacing -->\n" +
//              "                                         <!-- content -->\n" +
//              "                                         <tr>\n" +
//              "                                            <td style=\"font-family: Helvetica, arial, sans-serif;font-size: 16px;color: #666666;text-align:center;line-height: 30px;\" st-content=\"fulltext-content\">"
//              + transaccion.getUsuariosInvolucrados().get(0).getNombre()
//              + " esta interesado en truequear su <span style=\"color: #ff5128\"> "
//              + transaccion.getProductoEmisor().getTitulo()
//              + " </span> por tu <span style=\"color: #FFBF03\"> "
//              + transaccion.getProductoReceptor().getTitulo() + " </span></td>\n" +
//              "                                         </tr>\n" +
//              "                                         <!-- End of content -->\n" +
//              "                                      </tbody>\n" +
//              "                                   </table>\n" +
//              "                                </td>\n" +
//              "                             </tr>\n" +
//              "                             <!-- Spacing -->\n" +
//              "                             <tr>\n" +
//              "                                <td height=\"20\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                             </tr>\n" +
//              "                             <!-- Spacing -->\n" +
//              "                          </tbody>\n" +
//              "                       </table>\n" +
//              "                    </td>\n" +
//              "                 </tr>\n" +
//              "              </tbody>\n" +
//              "           </table>\n" +
//              "        </td>\n" +
//              "     </tr>\n" +
//              "  </tbody>\n" +
//              "</table>\n" +
//              "  \n" +
//              "<table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"seperator\">\n"
//              +
//              "  <tbody>\n" +
//              "     <tr>\n" +
//              "        <td>\n" +
//              "           <table width=\"600\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"devicewidth\">\n"
//              +
//              "              <tbody>\n" +
//              "                 <tr>\n" +
//              "                    <td align=\"center\" height=\"30\" style=\"font-size:1px; line-height:1px;\" bgcolor=\"#CADFFF\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "                 <tr>\n" +
//              "                    <td width=\"550\" align=\"center\" height=\"1\" bgcolor=\"#d1d1d1\" style=\"font-size:1px; line-height:1px;\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "                 <tr>\n" +
//              "                    <td align=\"center\" height=\"30\" style=\"font-size:1px; line-height:1px;\" bgcolor=\"#EDF4FF\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "              </tbody>\n" +
//              "           </table>\n" +
//              "        </td>\n" +
//              "     </tr>\n" +
//              "  </tbody>\n" +
//              "</table>\n" +
//              "<!-- Start of main-banner --><!-- Start of main-banner -->\n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"2columns\">\n"
//              +
//              "     <tbody>\n" +
//              "        <tr>\n" +
//              "           <td>\n" +
//              "              <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                 <tbody>\n" +
//              "                    <tr>\n" +
//              "                       <td width=\"100%\">\n" +
//              "                          <table bgcolor=\"#EDF4FF \" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                             <tbody>\n" +
//              "                                <tr>\n" +
//              "                                   <td>\n" +
//              "                                      <!-- start of left column -->\n" +
//              "                                      <table width=\"290\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <!-- Spacing -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                            </tr>\n" +
//              "                                            <!-- Spacing -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td>\n" +
//              "                                                  <!-- start of text content table -->\n"
//              +
//              "                                                  <table width=\"290\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                                     <tbody>\n" +
//              "                                                        <!-- image -->\n" +
//              "                                                        <tr>\n" +
//              "                                                           <td width=\"290\" height=\"160\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                                                              <img class=\"img-fluid rounded-cube\" height=\"180\" border=\"0\" alt=\"\" src=\"http://truecapp.herokuapp.com/fotoProducto/"
//              + transaccion.getProductoEmisor().getId() + "\">\n" +
//              "                                                           </td>\n" +
//              "                                                        </tr>\n" +
//              "                                                        <!-- Content -->\n" +
//              "                                                        <tr>\n" +
//              "                                                           <td>\n" +
//              "                                                              <table width=\"270\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                                                 <tbody>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                                                    </tr>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #ff5128; line-height:24px;text-align:center;\" st-title=\"2coltitle1\">\n"
//              +
//              "                                                                         "
//              + transaccion.getProductoEmisor().getTitulo() + "\n" +
//              "                                                                       </td>\n" +
//              "                                                                    </tr>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                                                    </tr>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 14px; line-height:24px; color: #666666; text-align:center;\" st-conteent=\"2colcontent1\">\n"
//              +
//              "                                                                          "
//              + transaccion.getProductoEmisor().getDescripcion() + " \n" +
//              "                                                                       </td>\n" +
//              "                                                                    </tr>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                                                    </tr>\n" +
//              "                                                                 </tbody>\n" +
//              "                                                              </table>\n" +
//              "                                                           </td>\n" +
//              "                                                        </tr>\n" +
//              "                                                        <!-- end of Content -->\n" +
//              "                                                        <!-- end of content -->\n" +
//              "                                                     </tbody>\n" +
//              "                                                  </table>\n" +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                            <!-- end of text content table -->\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      <!-- end of left column -->\n" +
//              "                                      <!-- start of right column -->\n" +
//              "                                      <table width=\"290\" align=\"right\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <!-- Spacing -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                            </tr>\n" +
//              "                                            <!-- Spacing -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td>\n" +
//              "                                                  <!-- start of text content table -->\n"
//              +
//              "                                                  <table width=\"290\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                                     <tbody>\n" +
//              "                                                        <!-- image -->\n" +
//              "                                                        <tr>\n" +
//              "                                                           <td width=\"290\" height=\"160\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                                                              <img class=\"img-fluid rounded-cube\" height=\"180\" border=\"0\" alt=\"\" src=\"http://truecapp.herokuapp.com/fotoProducto/"
//              + transaccion.getProductoReceptor().getId() + "\">\n" +
//              "                                                           </td>\n" +
//              "                                                        </tr>\n" +
//              "                                                        <!-- Content -->\n" +
//              "                                                        <tr>\n" +
//              "                                                           <td>\n" +
//              "                                                              <table width=\"270\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                                                 <tbody>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                                                    </tr>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #FFBF03 ;line-height:24px; text-align:center;\" st-title=\"2coltitle2\">\n"
//              +
//              "                                                                          "
//              + transaccion.getProductoReceptor().getTitulo() + "\n" +
//              "                                                                       </td>\n" +
//              "                                                                    </tr>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                                                    </tr>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 14px; line-height:24px; color: #666666; text-align:center;\" st-content=\"2colcontent2\">\n"
//              +
//              "                                                                          "
//              + transaccion.getProductoReceptor().getDescripcion() + " \n" +
//              "                                                                       </td>\n" +
//              "                                                                    </tr>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                                                    </tr>\n" +
//              "                                                                 </tbody>\n" +
//              "                                                              </table>\n" +
//              "                                                           </td>\n" +
//              "                                                        </tr>\n" +
//              "                                                        <!-- end of Content -->\n" +
//              "                                                        <!-- end of content -->\n" +
//              "                                                     </tbody>\n" +
//              "                                                  </table>\n" +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                            <!-- end of text content table -->\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      <!-- end of right column -->\n" +
//              "                                   </td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"10\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                             </tbody>\n" +
//              "                          </table>\n" +
//              "                       </td>\n" +
//              "                    </tr>\n" +
//              "                 </tbody>\n" +
//              "              </table>\n" +
//              "           </td>\n" +
//              "        </tr>\n" +
//              "     </tbody>\n" +
//              "    </table>\n" +
//              "  \n" +
//              "  <!--mio1-->\n" +
//              "<table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"seperator\">\n"
//              +
//              "  <tbody>\n" +
//              "     <tr>\n" +
//              "        <td>\n" +
//              "           <table width=\"600\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"devicewidth\">\n"
//              +
//              "              <tbody>\n" +
//              "                 <tr>\n" +
//              "                    <td align=\"center\" height=\"30\" style=\"font-size:1px; line-height:1px;\" bgcolor=\"#EDF4FF\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "                 <tr>\n" +
//              "                    <td width=\"550\" align=\"center\" height=\"1\" bgcolor=\"#d1d1d1\" style=\"font-size:1px; line-height:1px;\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "                 <tr>\n" +
//              "                    <td align=\"center\" height=\"30\" style=\"font-size:1px; line-height:1px;\" bgcolor=\"#CADFFF\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "              </tbody>\n" +
//              "           </table>\n" +
//              "        </td>\n" +
//              "     </tr>\n" +
//              "  </tbody>\n" +
//              "</table>\n" +
//              "  \n" +
//              "  \n" +
//              "  <!-- end of 2 columns -->\n" +
//              "<table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"3columns\">\n"
//              +
//              "     <tbody>\n" +
//              "        <tr>\n" +
//              "           <td>\n" +
//              "              <table bgcolor=\"#CADFFF\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" modulebg=\"edit\">\n"
//              +
//              "                 <tbody>\n" +
//              "                    <tr>\n" +
//              "                       <td width=\"100%\" height=\"0\"></td>\n" +
//              "                    </tr>\n" +
//              "                    <tr>\n" +
//              "                       <td>\n" +
//              "                          <table width=\"540\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                             <tbody>\n" +
//              "                                <tr>\n" +
//              "                                   <td>\n" +
//              "                                      <!-- col 1 -->\n" +
//              "                                      <table width=\"170\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <!-- image 2 -->\n" +
//              "                                            \n" +
//              "                                            <!-- end of image2 -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td>\n" +
//              "                                                  <!-- start of text content table -->  \n"
//              +
//              "                                                  <table width=\"170\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                                     <tbody>\n" +
//              "                                                        <!-- Spacing -->\n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        <!-- title2 -->\n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        <tr>\n" +
//              "                                                           <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                                                        </tr>\n" +
//              "                                                        <!-- Spacing -->\n" +
//              "                                                        <!-- button -->\n" +
//              "                                                        <tr>\n" +
//              "                                                           <td>\n" +
//              "                                                              <table height=\"30\" align=\"center\" valign=\"middle\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tablet-button\" st-button=\"edit\">\n"
//              +
//              "                                                                 <tbody>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"auto\" align=\"center\" valign=\"middle\" height=\"30\" style=\" background-color:#0db9ea; border-top-left-radius:4px; border-bottom-left-radius:4px; border-top-right-radius:4px; border-bottom-right-radius:4px;background-clip: padding-box;font-size:13px; font-family:Helvetica, arial, sans-serif; text-align:center;  color:#ffffff; font-weight: 300; padding-left:18px; padding-right:18px;\">\n"
//              +
//              "                                                                       \n" +
//              "                                                                          <span style=\"color: #ffffff; font-weight: 300;\">\n"
//              +
//              "                                                                             <a style=\"color: #ffffff; text-align:center;text-decoration: none;\" href=\"#\">Rechazar</a>\n"
//              +
//              "                                                                          </span>\n"
//              +
//              "                                                                       </td>\n" +
//              "                                                                    </tr>\n" +
//              "                                                                 </tbody>\n" +
//              "                                                              </table>\n" +
//              "                                                           </td>\n" +
//              "                                                        </tr>\n" +
//              "                                                        <!-- /button -->\n" +
//              "                                                     </tbody>\n" +
//              "                                                  </table>\n" +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                            <!-- end of text content table -->\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      <!-- spacing -->\n" +
//              "                                      <table width=\"15\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"removeMobile\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <tr>\n" +
//              "                                               <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                                            </tr>\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      <!-- end of spacing -->\n" +
//              "                                      <!-- col 2 -->\n" +
//              "<table width=\"170\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <!-- image 2 -->\n" +
//              "                                            \n" +
//              "                                            <!-- end of image2 -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td>\n" +
//              "                                                  <!-- start of text content table -->  \n"
//              +
//              "                                                  <table width=\"170\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                                     <tbody>\n" +
//              "                                                        <!-- Spacing -->\n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        <!-- title2 -->\n" +
//              "                                                        \n" +
//              "                                                        <tr>\n" +
//              "                                                           <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                                                        </tr>\n" +
//              "                                                        <!-- Spacing -->\n" +
//              "                                                        <!-- button -->\n" +
//              "                                                        <tr>\n" +
//              "                                                           <td>\n" +
//              "                                                              <table height=\"30\" align=\"center\" valign=\"middle\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tablet-button\" st-button=\"edit\">\n"
//              +
//              "                                                                 <tbody>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"auto\" align=\"center\" valign=\"middle\" height=\"30\" style=\" background-color:#0db9ea; border-top-left-radius:4px; border-bottom-left-radius:4px; border-top-right-radius:4px; border-bottom-right-radius:4px;background-clip: padding-box;font-size:13px; font-family:Helvetica, arial, sans-serif; text-align:center;  color:#ffffff; font-weight: 300; padding-left:18px; padding-right:18px;\">\n"
//              +
//              "                                                                       \n" +
//              "                                                                          <span style=\"color: #ffffff; font-weight: 300;\">\n"
//              +
//              "                                                                             <a style=\"color: #ffffff; text-align:center;text-decoration: none;\" href=\"#\">Aceptar</a>\n"
//              +
//              "                                                                          </span>\n"
//              +
//              "                                                                       </td>\n" +
//              "                                                                    </tr>\n" +
//              "                                                                 </tbody>\n" +
//              "                                                              </table>\n" +
//              "                                                           </td>\n" +
//              "                                                        </tr>\n" +
//              "                                                        <!-- /button -->\n" +
//              "                                                     </tbody>\n" +
//              "                                                  </table>\n" +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                            <!-- end of text content table -->\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      \n" +
//              "                                      <!-- end of col 2 -->\n" +
//              "                                      <!-- spacing -->\n" +
//              "                                      <table width=\"15\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"removeMobile\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <tr>\n" +
//              "                                               <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                                            </tr>\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      <!-- end of spacing -->\n" +
//              "                                      <table width=\"170\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidth\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <!-- image 2 -->\n" +
//              "                                            \n" +
//              "                                            <!-- end of image2 -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td>\n" +
//              "                                                  <!-- start of text content table -->  \n"
//              +
//              "                                                  <table width=\"170\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                                     <tbody>\n" +
//              "                                                        <!-- Spacing -->\n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        <!-- title2 -->\n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        \n" +
//              "                                                        <tr>\n" +
//              "                                                           <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                                                        </tr>\n" +
//              "                                                        <!-- Spacing -->\n" +
//              "                                                        <!-- button -->\n" +
//              "                                                        <tr>\n" +
//              "                                                           <td>\n" +
//              "                                                              <table height=\"30\" align=\"center\" valign=\"middle\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tablet-button\" st-button=\"edit\">\n"
//              +
//              "                                                                 <tbody>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"auto\" align=\"center\" valign=\"middle\" height=\"30\" style=\" background-color:#0db9ea; border-top-left-radius:4px; border-bottom-left-radius:4px; border-top-right-radius:4px; border-bottom-right-radius:4px;background-clip: padding-box;font-size:13px; font-family:Helvetica, arial, sans-serif; text-align:center;  color:#ffffff; font-weight: 300; padding-left:18px; padding-right:18px;\">\n"
//              +
//              "                                                                       \n" +
//              "                                                                          <span style=\"color: #ffffff; font-weight: 300;\">\n"
//              +
//              "                                                                             <a style=\"color: #ffffff; text-align:center;text-decoration: none;\" href=\"#\">Modificar</a>\n"
//              +
//              "                                                                          </span>\n"
//              +
//              "                                                                       </td>\n" +
//              "                                                                    </tr>\n" +
//              "                                                                 </tbody>\n" +
//              "                                                              </table>\n" +
//              "                                                           </td>\n" +
//              "                                                        </tr>\n" +
//              "                                                        <!-- /button -->\n" +
//              "                                                     </tbody>\n" +
//              "                                                  </table>\n" +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                            <!-- end of text content table -->\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "<!-- col 3 -->\n" +
//              "                                      \n" +
//              "                                   </td>\n" +
//              "                                   <!-- spacing -->\n" +
//              "                                   <!-- end of spacing -->\n" +
//              "                                </tr>\n" +
//              "                             </tbody>\n" +
//              "                          </table>\n" +
//              "                       </td>\n" +
//              "                    </tr>\n" +
//              "                    <tr>\n" +
//              "                       <td width=\"100%\" height=\"20\"></td>\n" +
//              "                    </tr>\n" +
//              "                 </tbody>\n" +
//              "              </table>\n" +
//              "           </td>\n" +
//              "        </tr>\n" +
//              "     </tbody>\n" +
//              "  </table>\n" +
//              "  \n" +
//              "<table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"seperator\">\n"
//              +
//              "  <tbody>\n" +
//              "     <tr>\n" +
//              "        <td>\n" +
//              "           <table width=\"600\" align=\"center\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" class=\"devicewidth\">\n"
//              +
//              "              <tbody>\n" +
//              "                 <tr>\n" +
//              "                    <td align=\"center\" height=\"30\" style=\"font-size:1px; line-height:1px;\" bgcolor=\"#CADFFF\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "                 <tr>\n" +
//              "                    <td width=\"550\" align=\"center\" height=\"1\" bgcolor=\"#d1d1d1\" style=\"font-size:1px; line-height:1px;\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "                 <tr>\n" +
//              "                    <td align=\"center\" height=\"30\" style=\"font-size:1px; line-height:1px;\" bgcolor=\"#EDF4FF\">&nbsp;</td>\n"
//              +
//              "                 </tr>\n" +
//              "              </tbody>\n" +
//              "           </table>\n" +
//              "        </td>\n" +
//              "     </tr>\n" +
//              "  </tbody>\n" +
//              "</table>\n" +
//              "<!-- Start of seperator --><!-- Start of seperator -->\n" +
//              "  \n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"header\">\n"
//              +
//              "     <tbody>\n" +
//              "        <tr>\n" +
//              "           <td>\n" +
//              "              <table bgcolor=\"#EDF4FF\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" modulebg=\"edit\">\n"
//              +
//              "                 <tbody>\n" +
//              "                    <!-- Spacing -->\n" +
//              "                    <tr>\n" +
//              "                       <td height=\"20\"></td>\n" +
//              "                    </tr>\n" +
//              "                    <!-- Spacing -->\n" +
//              "                    <tr>\n" +
//              "                       <td>\n" +
//              "                          <table width=\"540\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                             <tbody>\n" +
//              "                                <tr>\n" +
//              "                                   <td>\n" +
//              "                                      <!-- start of text content table -->\n" +
//              "                                      <table width=\"200\" align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <!-- image -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td width=\"200\" height=\"180\" align=\"center\">\n"
//              +
//              "                                                  <img src=\"http://truecapp.herokuapp.com/fotoPerfil/"
//              + transaccion.getUsuariosInvolucrados().get(0).getId()
//              + "\" border=\"0\" width=\"180\" height=\"180\" class=\"avatar avatar-xl rounded-circle\">\n"
//              +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      <!-- mobile spacing -->\n" +
//              "                                      <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"mobilespacing\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <tr>\n" +
//              "                                               <td width=\"100%\" height=\"15\" style=\"font-size:1px; line-height:1px; mso-line-height-rule: exactly;\">&nbsp;</td>\n"
//              +
//              "                                            </tr>\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      <!-- mobile spacing -->\n" +
//              "                                      <!-- start of right column -->\n" +
//              "                                      <table width=\"330\" align=\"right\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"devicewidthinner\">\n"
//              +
//              "                                         <tbody>\n" +
//              "                                            <!-- title -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 18px; color: #333333; text-align:left;line-height: 20px;\" st-title=\"leftimage-title\">\n"
//              +
//              "                                                  "
//              + transaccion.getUsuariosInvolucrados().get(0).getNombre() + "\n" +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                            <!-- end of title -->\n" +
//              "                                            <!-- Spacing -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td width=\"100%\" height=\"20\"></td>\n"
//              +
//              "                                            </tr>\n" +
//              "                                            <!-- Spacing -->\n" +
//              "                                            <!-- content -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td style=\"font-family: Helvetica, arial, sans-serif; font-size: 13px; color: #95a5a6; text-align:left;line-height: 24px;\" st-content=\"leftimage-paragraph\">\n"
//              +
//              "                                                  Entra a su perfil y mira todos sus objetos a truequear disponibles\n"
//              +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                            <!-- end of content -->\n" +
//              "                                            <!-- Spacing -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td width=\"100%\" height=\"10\"></td>\n"
//              +
//              "                                            </tr>\n" +
//              "                                            <!-- button -->\n" +
//              "                                            <tr>\n" +
//              "                                               <td>\n" +
//              "                                                  <table height=\"30\" align=\"left\" valign=\"middle\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tablet-button\" st-button=\"edit\">\n"
//              +
//              "                                                                 <tbody>\n" +
//              "                                                                    <tr>\n" +
//              "                                                                       <td width=\"auto\" align=\"center\" valign=\"middle\" height=\"30\" style=\" background-color:#0db9ea; border-top-left-radius:4px; border-bottom-left-radius:4px;border-top-right-radius:4px; border-bottom-right-radius:4px; background-clip: padding-box;font-size:13px; font-family:Helvetica, arial, sans-serif; text-align:center;  color:#ffffff; font-weight: 300; padding-left:18px; padding-right:18px;\">\n"
//              +
//              "                                                                       \n" +
//              "                                                                          <span style=\"color: #ffffff; font-weight: 300;\">\n"
//              +
//              "                                                                             <a style=\"color: #ffffff; text-align:center;text-decoration: none;\" href=\"#\">Ingresar</a>\n"
//              +
//              "                                                                          </span>\n"
//              +
//              "                                                                       </td>\n" +
//              "                                                                    </tr>\n" +
//              "                                                                 </tbody>\n" +
//              "                                                              </table>\n" +
//              "                                               </td>\n" +
//              "                                            </tr>\n" +
//              "                                            <!-- /button -->\n" +
//              "                                         </tbody>\n" +
//              "                                      </table>\n" +
//              "                                      <!-- end of right column -->\n" +
//              "                                   </td>\n" +
//              "                                </tr>\n" +
//              "                             </tbody>\n" +
//              "                          </table>\n" +
//              "                       </td>\n" +
//              "                    </tr>\n" +
//              "                    <!-- Spacing -->\n" +
//              "                    <tr>\n" +
//              "                       <td height=\"20\"></td>\n" +
//              "                    </tr>\n" +
//              "                    <!-- Spacing -->\n" +
//              "                 </tbody>\n" +
//              "              </table>\n" +
//              "           </td>\n" +
//              "        </tr>\n" +
//              "     </tbody>\n" +
//              "  </table>\n" +
//              "  <!-- End of seperator -->  \n" +
//              "  <!-- Start of Postfooter -->\n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"postfooter\">\n"
//              +
//              "     <tbody>\n" +
//              "        <tr>\n" +
//              "           <td>\n" +
//              "              <table width=\"600\" bgcolor=\"#eacb3c\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                 <tbody>\n" +
//              "                    <tr>\n" +
//              "                       <td width=\"100%\">\n" +
//              "                          <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                             <tbody>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"15\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "  \n" +
//              "                                <tr>\n" +
//              "                                   <td align=\"center\" valign=\"middle\" style=\"font-family: Helvetica, arial, sans-serif; font-size: 14px;color: #666666\" st-content=\"postfooter\">Para Verificar tu mail haz click aquí:<a href=\"#\" style=\"text-decoration: none; color: #0a8cce\"> Verificar</a> \n"
//              +
//              "                                   </td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                                <tr>\n" +
//              "                                   <td width=\"100%\" height=\"15\"></td>\n" +
//              "                                </tr>\n" +
//              "                                <!-- Spacing -->\n" +
//              "                             </tbody>\n" +
//              "                          </table>\n" +
//              "                       </td>\n" +
//              "                    </tr>\n" +
//              "                 </tbody>\n" +
//              "              </table>\n" +
//              "           </td>\n" +
//              "        </tr>\n" +
//              "     </tbody>\n" +
//              "  </table>\n" +
//              "  <!-- End of postfooter -->\n" +
//              "  <table width=\"100%\" bgcolor=\"#F7FAFF \" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" st-sortable=\"preheader\">\n"
//              +
//              "   <tbody>\n" +
//              "      <tr>\n" +
//              "         <td>\n" +
//              "            <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\" bgcolor=\"#F7FAFF\">\n"
//              +
//              "               <tbody>\n" +
//              "                  <tr>\n" +
//              "                     <td width=\"100%\">\n" +
//              "                        <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" class=\"devicewidth\">\n"
//              +
//              "                           <tbody>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              <tr>\n" +
//              "                                 <td width=\"100%\" height=\"65\"></td>\n" +
//              "                              </tr>\n" +
//              "                              <!-- Spacing -->\n" +
//              "                              \n" +
//              "                              \n" +
//              "                              \n" +
//              "                              \n" +
//              "                           </tbody>\n" +
//              "                        </table>\n" +
//              "                     </td>\n" +
//              "                  </tr>\n" +
//              "               </tbody>\n" +
//              "            </table>\n" +
//              "         </td>\n" +
//              "      </tr>\n" +
//              "   </tbody>\n" +
//              "</table>\n" +
//              "</body></html>",
//              "text/html");
//
//      // Send message
//      Transport.send(message);
//
//      System.out.println("Sent message successfully....");
//
//    } catch (MessagingException e) {
//      e.printStackTrace();
//      throw new RuntimeException(e);
//    }
//
//  }
//}
