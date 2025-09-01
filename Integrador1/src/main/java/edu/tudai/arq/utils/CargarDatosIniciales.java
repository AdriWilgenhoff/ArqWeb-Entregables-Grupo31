package edu.tudai.arq.utils;

import edu.tudai.arq.dao.ClienteDAO;
import edu.tudai.arq.dao.FacturaProductoDAO;
import edu.tudai.arq.factory.DBType;
import edu.tudai.arq.factory.DaoFactory;
import edu.tudai.arq.utils.strategies.insert.data.InsertStrategy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CargarDatosIniciales {

    private final ClienteDAO clienteDAO;
    private final FacturaProductoDAO facturaProductoDAO;

    public CargarDatosIniciales() {
        DaoFactory f = DaoFactory.getInstance(DBType.MYSQL);
        this.clienteDAO = f.getClienteDAO();
        this.facturaProductoDAO = f.getFacturaProductoDAO();
    }

    public CargarDatosIniciales(DBType dbType) {
        DaoFactory f = DaoFactory.getInstance(dbType);
        this.clienteDAO = f.getClienteDAO();
        this.facturaProductoDAO = f.getFacturaProductoDAO();
    }

    public void run(List<InsertStrategy> estrategias) {
        for (InsertStrategy strategy : estrategias) {
            loadWithStrategy(strategy);
        }
    }

    private void loadWithStrategy(InsertStrategy strategy){
        int lineNo = 0;
        try (InputStream is = mustGetResource(strategy.getResourcePath());
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

                String[] campos = line.split(",", -1);
                strategy.insert(campos,lineNo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo '" + strategy.getResourcePath() + "' en línea " + lineNo, e);
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
