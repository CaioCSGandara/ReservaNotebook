package br.edu.puccampinas.reservanotebook.model.database;

import java.util.List;

public interface CRUD<T> {
    void create(T obj);
    List<T> findAll();
    void update(T obj);
    void delete(String id);
    T findById(String id);
}
