package edu.tudai.arq.dao;

import edu.tudai.arq.entity.Cliente;

public interface ClienteDAO {

    Cliente findById(Long id);

    void insert(Cliente u);
    /*void update(Cliente u);
    void delete(long id);
    void deleteAll();*/

}
