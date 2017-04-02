package com.sevenj.web.model.converter;

public interface ViewModelConverter<B,V> {

	public V convertToViewModel(B businessModel) throws Exception;
	public B convertToBusinessModel(V viewModel) throws Exception;
}
