package edu.tudai.arq;

import edu.tudai.arq.dao.ClienteDAO;
import edu.tudai.arq.factory.DBType;
import edu.tudai.arq.factory.DaoFactory;
import edu.tudai.arq.utils.BorrarDatos;
import edu.tudai.arq.utils.CargarDatosIniciales;

public class App
{
    public static void main( String[] args )
    {
        //new BorrarDatos().run();
        //System.out.println("Borrado de tablas finalizado");

        new CargarDatosIniciales().run();
        //System.out.println("Carga inicial finalizada.");

        DaoFactory factory = DaoFactory.getInstance(DBType.MYSQL);

        ClienteDAO clienteDAO = factory.getClienteDAO();

        System.out.println(clienteDAO.findById(1L).getEmail());

        //ProductoDAO productoDAO = factory.getProductoDA0();

        /*
        // Obtiene y muestra el producto con mayor recaudación
        ProductoMayorRecaudacionDTO productoMayorRecaudacion = productoDAO.getProductoMayorRecaudacion();

        if (productoMayorRecaudacion != null) {
            System.out.println("Producto con mayor recaudación: ");
            System.out.println(productoMayorRecaudacion);
        } else {
            System.out.println("No se encontraron productos.");
        }

        // Obtiene y muestra la lista de clientes ordenada por facturación
        List<ClienteConFacturacionDTO> clientesFacturados = clienteDAO.getClientesPorMayorFacturacionDesc();

        if (clientesFacturados.isEmpty()) {
            System.out.println("No se encontraron clientes.");
        } else {
            System.out.println("Clientes ordenados por mayor facturación: ");

            for (ClienteConFacturacionDTO cliente : clientesFacturados) {
                System.out.println(cliente);
            }
        }
*/

    }
}
