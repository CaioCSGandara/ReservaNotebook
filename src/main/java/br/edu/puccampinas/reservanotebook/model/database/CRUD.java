package br.edu.puccampinas.reservanotebook.model.database;

import java.util.ArrayList;
import java.util.List;

public interface CRUD<T> {
    void create(T obj);
    ArrayList<T> findAll();
    void update(T obj);
    void delete(String id);
}
