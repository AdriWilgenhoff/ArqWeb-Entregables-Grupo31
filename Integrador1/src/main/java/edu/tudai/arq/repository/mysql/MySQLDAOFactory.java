package edu.tudai.arq.repository.mysql;

import edu.tudai.arq.dao.ClienteDAO;
import edu.tudai.arq.factory.ConnectionManager;
import edu.tudai.arq.factory.DaoFactory;

public class MySQLDAOFactory extends DaoFactory {

    @Override
    public ClienteDAO getClienteDAO() {
        // Devuelve la implementación concreta MySQL de UsuarioDAO
        return new MySQLClienteDAO (ConnectionManager.getInstance().getConnection());

    }

    /*@Override
    public ProductoDAO createProductoDAO() {
        // Devuelve la implementación concreta MySQL de ProductoDAO
        return new MySQLProductoDAO(ConnectionManager.getInstance().getConnectionLine());;
    }

    @Override
    public PedidoDAO createPedidoDAO() {
        return new MySQLPedidoDAO(ConnectionManager.getInstance().getConnectionLine());;
    }

    @Override
    public DetallePedidoDAO createDetallePedidoDAO() {
        return new MySQLDetallePedidoDAO(ConnectionManager.getInstance().getConnectionLine()
        );
    }*/

}
