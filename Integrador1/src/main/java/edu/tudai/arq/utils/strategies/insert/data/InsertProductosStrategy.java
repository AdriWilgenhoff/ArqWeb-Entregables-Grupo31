package edu.tudai.arq.utils.strategies.insert.data;

import edu.tudai.arq.dao.ProductoDAO;
import edu.tudai.arq.entity.Producto;

public class InsertProductosStrategy extends AbstractInsertStrategy{

    private final ProductoDAO productoDAO;
    public InsertProductosStrategy(ProductoDAO productoDAO, String resourcePath) {
        super(resourcePath);
        this.productoDAO = productoDAO;
    }

    @Override
    public void insert(String[] campos, int lineNo) {
        int id = Integer.parseInt(campos[0].trim());
        String nombre = campos[1].trim();
        Float valor = Float.parseFloat(campos[2].trim());
        productoDAO.create(new Producto(id,nombre,valor));
    }
}
