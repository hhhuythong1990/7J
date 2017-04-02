package com.sevenj.web.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class KeyValue<K, V> {
	private K key;
	private V value;
	
	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@JsonProperty("key")
	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	@JsonProperty("value")
	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
