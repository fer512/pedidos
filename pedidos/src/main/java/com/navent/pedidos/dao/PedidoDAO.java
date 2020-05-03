package com.navent.pedidos.dao;

import com.navent.pedidos.model.Pedido;

public class PedidoDAO {
	
	// inserta un nuevo pedido en la base de datos 
	//o modifica un pedido existente (en cado de crear 
	//uno nuevo, el pedido pasado como parámetro se completa con el nuevo id).
	static public void insertOrUpdate(Pedido pedido){
		
	}
	
	// elimina el pedido que corresponde al id recibido.
	static public void delete(Pedido pedido) {
		
	}
	
	// busca un pedido por id.	
	static public Pedido select(Long idPedido){
		return null;
	}
}
