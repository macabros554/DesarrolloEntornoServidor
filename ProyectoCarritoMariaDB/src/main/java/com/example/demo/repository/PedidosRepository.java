package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pedidos;
import com.example.demo.model.Usuario;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long>{

}