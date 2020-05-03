package com.navent.pedidos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.navent.pedidos.cache.BumexMemcached;
import com.navent.pedidos.dao.PedidoDAO;
import com.navent.pedidos.exception.BusinessException;
import com.navent.pedidos.model.Pedido;
import com.navent.pedidos.service.MutexService;
import com.navent.pedidos.service.PedidoService;


public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private BumexMemcached bumexMemcached;

	@Autowired
	private MutexService<Long> mapMutex;
	
	
	@Override
	public Pedido createPedidos(Pedido pedido) {
		PedidoDAO.insertOrUpdate(pedido);
		bumexMemcached.set(pedido.getId().toString(), pedido);
		return pedido;
	}

	@Override
	public Pedido updatePedidos(Pedido pedido) throws BusinessException {
		synchronized (mapMutex.getMutex(pedido.getId())) {
			try {
				PedidoDAO.insertOrUpdate(pedido);
				bumexMemcached.set(pedido.getId().toString(), pedido);
				return pedido;
			} catch (Exception e) {
				throw new BusinessException("Error al modificar pedido");

			}
		}
	}

	@Override
	public Pedido getPedidos(Long id) throws BusinessException {
		synchronized (mapMutex.getMutex(id)) {
			try {
				Pedido pedido = (Pedido) bumexMemcached.get(id.toString());
				if (pedido == null) {
					pedido = PedidoDAO.select(id);
					bumexMemcached.set(pedido.getId().toString(), pedido);
				}
				return pedido;
			} catch (Exception e) {
				throw new BusinessException("Error al obtener pedido");
			}
		}
	}

	@Override
	public void deletePedidos(Long id) throws BusinessException {
		synchronized (mapMutex.getMutex(id)) {
			try {
				PedidoDAO.delete(new Pedido(id)); // hibernate
				bumexMemcached.delete(id.toString());
			} catch (Exception e) {
				throw new BusinessException("Error al borrar pedido");
			}
		}
	}
	
}
