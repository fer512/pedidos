package com.navent.pedidos.service;

public interface MutexService<K> {
	Object getMutex(K key);
}
