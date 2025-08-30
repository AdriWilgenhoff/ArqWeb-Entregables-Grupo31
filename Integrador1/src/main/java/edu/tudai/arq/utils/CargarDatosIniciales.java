package edu.tudai.arq.utils;

import edu.tudai.arq.dao.ClienteDAO;
import edu.tudai.arq.entity.Cliente;
import edu.tudai.arq.factory.DBType;
import edu.tudai.arq.factory.DaoFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CargarDatosIniciales {

    private final ClienteDAO clienteDAO;

    public CargarDatosIniciales() {
        DaoFactory f = DaoFactory.getInstance(DBType.MYSQL);
        this.clienteDAO = f.getClienteDAO();
    }

    public void run() {
        cargarClientes("data/clientes.csv");
        //cargarProductos("data/productos.csv");
        //cargarPedidos("data/pedidos.csv");
    }

    private void cargarClientes(String resourcePath) {
        int lineNo = 0;
        try (InputStream is = mustGetResource(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                lineNo++;
                if (first) {
                    first = false;
                    continue;
                }
                if (line.equals("")) continue;

                String[] p = line.split(",", -1);

                int id = Integer.parseInt(p[0].trim());
                String nombre = p[1].trim();
                String email = p[2].trim();

                clienteDAO.insert(new Cliente(id, nombre, email));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo '" + resourcePath + "' en línea " + lineNo, e);
        }
    }

    private InputStream mustGetResource(String resourcePath) throws java.io.IOException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        InputStream is = cl.getResourceAsStream(resourcePath);
        if (is == null) {
            throw new java.io.FileNotFoundException("Recurso no encontrado en classpath: " + resourcePath);
        }
        return is;
    }
}
