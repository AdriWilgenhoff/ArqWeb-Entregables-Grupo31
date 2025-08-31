package edu.tudai.arq.utils.strategies.insert.data;

import edu.tudai.arq.dao.FacturaDAO;
import edu.tudai.arq.entity.Factura;

public class InsertFacturasStrategy extends AbstractInsertStrategy {

    private final FacturaDAO facturaDao;
    public InsertFacturasStrategy(FacturaDAO facturaDAO, String resourcePath) {
        super(resourcePath);
        this.facturaDao = facturaDAO;
    }

    @Override
    public void insert(String[] campos, int lineNo) {
        int id = Integer.parseInt(campos[0].trim());
        int idCliente = Integer.parseInt(campos[1].trim());
        facturaDao.create(new Factura(id, idCliente));
    }
}
