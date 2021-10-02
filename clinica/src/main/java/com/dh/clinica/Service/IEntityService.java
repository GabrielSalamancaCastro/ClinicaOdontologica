package com.dh.clinica.Service;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {

    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T t);
    String delete(Long id);
    String update(T t);


}
