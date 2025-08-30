package edu.tudai.arq.utils;

public class BorrarDatos {

    /*private final UsuarioDAO usuarioDAO;
    private final ProductoDAO productoDAO;
    private final PedidoDAO pedidoDAO;
    private final DetallePedidoDAO detallePedidoDAO;


    public BorrarDatos() {
        DAOFactory f = DAOFactory.getInstance();

        this.usuarioDAO = f.getUsuarioDA0();
        this.productoDAO = f.getProductoDA0();
        this.pedidoDAO = f.getPedidoDA0();
        this.detallePedidoDAO = f.getDetallePedidoDA0();
    }

    public void run() {
        // Orden recomendado por FKs:
        // 1) Detalles (FK a pedidos y productos)
        // 2) Pedidos .. (padre de detalles; además tiene FK a usuarios)
        // 3) Productos (referenciados por detalles)
        // 4) Usuarios (padre de pedidos)
        try {
            detallePedidoDAO.deleteAll();
            pedidoDAO.deleteAll();
            productoDAO.deleteAll();
            usuarioDAO.deleteAll();
            System.out.println("Borrado completo de usuarios, productos, pedidos y detalles.");
        } catch (Exception e) {
            throw new RuntimeException("Error durante el borrado masivo.", e);
        }
    }*/

}
