package com.navent.pedidos.service.impl;

import org.springframework.util.ConcurrentReferenceHashMap;

import com.navent.pedidos.service.MutexService;


public class MutexServiceImpl<K>  implements MutexService<K>{

    private ConcurrentReferenceHashMap<K, Object> map;

    public MutexServiceImpl() {
        this.map = new ConcurrentReferenceHashMap<>();
    }

	@Override
	public Object getMutex(K key) {
		return this.map.compute(key, (k, v) -> v == null ? new Object() : v);
	}
}
