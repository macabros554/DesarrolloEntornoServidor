package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long>{

}