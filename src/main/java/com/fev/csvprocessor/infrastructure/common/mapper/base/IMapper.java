package com.fev.csvprocessor.infrastructure.common.mapper.base;

public interface IMapper <E, M>{
    E map(M m);
    M reverseMap(E e);
}
