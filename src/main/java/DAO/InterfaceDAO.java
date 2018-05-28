package DAO;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDAO {
    public Long inserir(Object object);
    public void alterar(Object object);
    public List<Object> consultar(Object object) throws SQLException, ClassNotFoundException;
    public void excluir();
}