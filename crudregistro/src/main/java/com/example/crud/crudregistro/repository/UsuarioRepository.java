package com.example.crud.crudregistro.repository;

import com.example.crud.crudregistro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
