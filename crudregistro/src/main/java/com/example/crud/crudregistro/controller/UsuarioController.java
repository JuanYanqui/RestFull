package com.example.crud.crudregistro.controller;

import com.example.crud.crudregistro.model.Usuario;
import com.example.crud.crudregistro.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/li")
    public ResponseEntity<List<Usuario>> obtenerLista() {
        return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/cre")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping("/eli/{id}")
    public ResponseEntity <?> eliminar(@PathVariable Integer id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/actu/{id}")
    public ResponseEntity <Usuario> actulizar(@RequestBody Usuario usuario, @PathVariable Integer id) {
        Usuario usu = usuarioService.findById(id);
        if (usu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            usu.setNombre(usuario.getNombre());
            usu.setClave(usuario.getClave());
            usu.setEmail(usuario.getEmail());
            usu.setEstado(usuario.isEstado());
            return new ResponseEntity<>(usuarioService.save(usu), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
