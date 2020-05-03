package com.navent.pedidos.service;

import com.navent.pedidos.exception.BusinessException;
import com.navent.pedidos.model.Pedido;

public interface PedidoService {

	Pedido createPedidos(Pedido dto);
	
	Pedido updatePedidos(Pedido dto)  throws BusinessException;
	
	Pedido getPedidos(Long id)  throws BusinessException;

	void deletePedidos(Long id)  throws BusinessException;
}
