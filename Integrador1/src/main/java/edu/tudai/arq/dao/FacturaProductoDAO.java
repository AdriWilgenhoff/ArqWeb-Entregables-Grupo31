package edu.tudai.arq.dao;


import edu.tudai.arq.entity.Factura;
import edu.tudai.arq.entity.FacturaProducto;

import java.util.List;

public interface FacturaProductoDAO extends GenericDAO<FacturaProducto,Long> {
    void delete(Integer idFactura, Integer idProducto);
    FacturaProducto findById(Integer idFactura, Integer idProducto);
/*
    /// Se declaran todos los métodos necesarios para esta entidad.
    void create(FacturaProducto entity);

    void update(FacturaProducto entity);

    void delete(Integer idFactura, Integer idProducto);

    FacturaProducto findById(Integer idFactura, Integer idProducto);

    List<FacturaProducto> findAll();

    void deleteAll();
*/

}
