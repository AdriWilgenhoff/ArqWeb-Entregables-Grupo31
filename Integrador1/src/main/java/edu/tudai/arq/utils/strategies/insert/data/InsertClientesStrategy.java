package edu.tudai.arq.utils.strategies.insert.data;

import edu.tudai.arq.dao.ClienteDAO;
import edu.tudai.arq.entity.Cliente;

public class InsertClientesStrategy extends AbstractInsertStrategy {

    private final ClienteDAO clienteDAO;

    public InsertClientesStrategy(ClienteDAO clienteDAO, String resourcePath) {
        super(resourcePath);
        this.clienteDAO = clienteDAO;
    }

    @Override
    public void insert(String[] campos, int lineNo) {
        int id = Integer.parseInt(campos[0].trim());
        String nombre = campos[1].trim();
        String email = campos[2].trim();
        clienteDAO.create(new Cliente(id, nombre, email));
    }
}

