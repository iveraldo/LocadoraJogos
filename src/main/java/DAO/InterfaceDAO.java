package DAO;

import java.util.List;

public interface InterfaceDAO {
    public Long inserir(Object object);
    public void alterar(Object object);
    public Object consultar(Object object);
    public List<Object> consultarTodos(Object object);
    public void excluir();
}