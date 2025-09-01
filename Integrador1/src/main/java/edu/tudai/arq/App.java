package edu.tudai.arq;

import edu.tudai.arq.dao.ClienteDAO;
//import edu.tudai.arq.dao.FacturaProductoDAO;
import edu.tudai.arq.dao.FacturaProductoDAO;
import edu.tudai.arq.dao.ProductoDAO;
import edu.tudai.arq.dto.ClienteConFacturacionDTO;
import edu.tudai.arq.dto.ProductoMayorRecaudacionDTO;
import edu.tudai.arq.factory.DBType;
import edu.tudai.arq.factory.DaoFactory;
import edu.tudai.arq.utils.CargarDatosIniciales;
import edu.tudai.arq.utils.strategies.insert.data.*;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        //new BorrarDatos().run();
        System.out.println("Borrado de tablas finalizado");

        DaoFactory factory = DaoFactory.getInstance(DBType.MYSQL);

        List<InsertStrategy> estrategias = new ArrayList<>();
        estrategias.add(new InsertClientesStrategy(factory.getClienteDAO(), "data/clientes.csv"));
        estrategias.add(new InsertFacturasStrategy(factory.getFacturaDAO(),"data/facturas.csv"));
        estrategias.add(new InsertProductosStrategy(factory.getProductoDAO(),"data/productos.csv"));
        estrategias.add(new InsertFacturasProductosStrategy(factory.getFacturaProductoDAO(),"data/facturas-productos.csv"));

        CargarDatosIniciales loader = new CargarDatosIniciales();
        loader.run(estrategias);

        System.out.println("Carga inicial finalizada.");

        ClienteDAO clienteDAO = factory.getClienteDAO();
        ProductoDAO productoDAO = factory.getProductoDAO();

        System.out.println(clienteDAO.findById(1L).getEmail());

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

    }
}
