package com.navent.pedidos.cache;

import org.springframework.stereotype.Service;

@Service
public interface BumexMemcached {
	
	public void set(String key, Object value);

	public Object get(String key);

	public void delete(String key);
	
}
