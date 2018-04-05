package com.code.web.common.repository;

public interface BaseJpaRepository<T, S> {

	T saveAndFlush(T t);
}
