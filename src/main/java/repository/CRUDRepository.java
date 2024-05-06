package repository;

import model.Pokemon;

import java.util.List;

public interface CRUDRepository<T> {
    public List<Pokemon> getAll() throws Exception;
    public boolean add(T t) throws Exception;
    public boolean update(T t) throws Exception;
    public boolean delete(T t) throws Exception;
}
