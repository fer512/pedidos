package com.navent.pedidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.navent.pedidos.exception.BusinessException;
import com.navent.pedidos.model.Pedido;
import com.navent.pedidos.service.PedidoService;

@Controller
@RequestMapping("${com.navent.rest.pedidos}")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	public PedidoController() {
	}

	@RequestMapping(value = "${com.navent.rest.pedido.save}", method = RequestMethod.POST)
	public @ResponseBody void createPedido(@RequestBody Pedido newPedido) {
		pedidoService.createPedidos(newPedido);
	}

	@RequestMapping(value = "${com.navent.rest.pedido.update}", method = RequestMethod.PUT)
	public @ResponseBody Pedido updatePedido(@RequestBody Pedido existingPedido) throws BusinessException {
		return pedidoService.updatePedidos(existingPedido);
	}

	@RequestMapping(value = "${com.navent.rest.pedido.get}", method = RequestMethod.GET)
	public @ResponseBody Pedido getPedido(@PathVariable("id") long id) throws BusinessException {
		return pedidoService.getPedidos(id);
	}

	@RequestMapping(value = "${com.navent.rest.pedido.delete}", method = RequestMethod.DELETE)
	public @ResponseBody void deletePedido(@PathVariable("id") long id) throws BusinessException {
		pedidoService.deletePedidos(id);
	}
}
