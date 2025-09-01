package edu.tudai.arq.dao;

import edu.tudai.arq.dto.ClienteConFacturacionDTO;
import edu.tudai.arq.entity.Cliente;

import java.util.List;

public interface ClienteDAO extends GenericDAO<Cliente,Long>{

    List<ClienteConFacturacionDTO> getClientesPorMayorFacturacionDesc();
}
