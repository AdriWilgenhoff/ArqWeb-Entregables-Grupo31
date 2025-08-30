package edu.tudai.arq;

import edu.tudai.arq.factory.DBType;
import edu.tudai.arq.utils.BorrarDatos;
import edu.tudai.arq.utils.CargarDatosIniciales;

public class App
{
    public static void main( String[] args )
    {
        new BorrarDatos().run();
        System.out.println("Borrado de tablas finalizado");

        new CargarDatosIniciales().run();
        System.out.println("Carga inicial finalizada.");

        DAOFactory factory = DAOFactory.getInstance(DBType.MYSQL);
        ClienteDAO clienteDAO = factory.createUsuarioDA0();
        ProductoDAO productoDAO = factory.createProductoDA0();
        FacturaDAO pedidoDAO = factory.createPedidoDA0();
        FacturaProductoDAO detallePedidoDAO = factory.createDetallePedidoDA0();

        // Obtiene y muestra el producto con mayor recaudación
        ProductoMayorRecaudacionDTO productoMayorRecaudacion = productoDAO.obtenerProductoMayorRecaudacion();

        if (productoMayorRecaudacion != null) {
            System.out.println("Producto con mayor recaudación: ");
            System.out.println(productoMayorRecaudacion);
        } else {
            System.out.println("No se encontraron productos.");
        }

        // Obtiene y muestra la lista de clientes ordenada por facturación
        List<ClienteConFacturacionDTO> clientesFacturados = clienteDAO.obtenerClientesPorMayorFacturacionDesc();

        if (clientesFacturados.isEmpty()) {
            System.out.println("No se encontraron clientes.");
        } else {
            System.out.println("Clientes ordenados por mayor facturación: ");

            for (ClienteConFacturacionDTO cliente : clientesFacturados) {
                System.out.println(cliente);
            }
        }


    }
}
