//package com.example.truecapp3.controllers;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/")
//public class PhotoController {
//  @Autowired
//  UsuarioServicio usuarioServicio;
//
//  @Autowired
//  usuarioRepositorio usuariorepositorio;
//
//  @Autowired
//  ProductoServicio productoServicio;
//
//  @Autowired
//  EmpresaServicio empresaServicio;
//
//  @Autowired
//  ProductoServicio productoservicio;
//
//  @GetMapping("fotoPerfil/{id}")
//  public ResponseEntity<byte[]> fotoPerfil(@PathVariable String id) throws ErrorServicio {
//    System.out.println(id);
//    Usuario usuario = usuariorepositorio.findById(id).get();
//
//    Foto foto = usuario.getFotoPerfil();
//
//    if (foto != null) {
//      final HttpHeaders headers = new HttpHeaders();
//
//      headers.setContentType(MediaType.IMAGE_JPEG);
//
//      return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
//
//    }
//    return null;
//  }
//
//
//  @GetMapping("fotoProducto/{id}")
//  public ResponseEntity<byte[]> fotoProducto(@PathVariable String id) throws ErrorServicio {
//
//    Producto producto = productoServicio.consultarProductoId(id);
//
//    Foto foto = producto.getFotos().get(0);
//    final HttpHeaders headers = new HttpHeaders();
//
//    headers.setContentType(MediaType.IMAGE_JPEG);
//
//    return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
//  }
//
//
//  @GetMapping("fotoObjeto/{id}")
//  public ResponseEntity<byte[]> fotoObjeto(@PathVariable String id) throws ErrorServicio {
//
//
//    Producto producto = productoServicio.consultarProductoId(id);
//
//
//    Foto foto = producto.getFotos().get(0);
//    final HttpHeaders headers = new HttpHeaders();
//
//    headers.setContentType(MediaType.IMAGE_JPEG);
//
//    return new ResponseEntity<>(foto.getContenido(), headers, HttpStatus.OK);
//  }
//
//
//}
