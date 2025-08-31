package edu.tudai.arq.utils.strategies.insert.data;

import edu.tudai.arq.dao.FacturaProductoDAO;
import edu.tudai.arq.entity.FacturaProducto;

public class InsertFacturasProductosStrategy extends AbstractInsertStrategy{

    private final FacturaProductoDAO facturaProductoDAO;
    public InsertFacturasProductosStrategy(FacturaProductoDAO facturaProductoDAO, String resourcePath) {
        super(resourcePath);
        this.facturaProductoDAO = facturaProductoDAO;
    }

    @Override
    public void insert(String[] campos, int lineNo) {
        int idFactura = Integer.parseInt(campos[0].trim());
        int idProducto =  Integer.parseInt(campos[1].trim());
        int cantidad = Integer.parseInt(campos[2].trim());
        this.facturaProductoDAO.create(new FacturaProducto(idFactura,idProducto,cantidad));
    }
}
