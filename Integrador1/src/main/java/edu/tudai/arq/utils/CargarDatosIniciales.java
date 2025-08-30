package edu.tudai.arq.utils;

import edu.tudai.arq.factory.DBType;

public class CargarDatosIniciales {

    private final UsuarioDAO usuarioDAO;
    private final ProductoDAO productopAO;
    private final PedidoDAO pedidoDț0;
    private final DetallePedidoDAO detallePedidoDAO;

    public CargarDatosIniciales() {
        DAOFactory f = DAOFactory.getInstance(DBType.MYSQL);
        this.usuarioDAO = f.createUsuarioDA0();
        this.productoDAO = f.createProductoDA0();
        this.pedidoDAO = f.createPedidoDA0();
        this.detallePedidoDAO = f.createDetallePedidoDA0();

    }

        public void run() {
            cargarUsuarios("/data/usuarios.csv");
            cargarProductos("/data/productos.csv");
            cargarPedidos("/data/pedidos.csv");
        }

}
