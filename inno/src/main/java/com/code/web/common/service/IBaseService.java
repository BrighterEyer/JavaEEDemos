package com.code.web.common.service;

public interface IBaseService<T, S> {
	void deleteById(String id);

	T findDTOById(String id) throws Exception;

	T creatDTO(T dto) throws Exception;

	T updateDTO(T dto) throws Exception;
}
