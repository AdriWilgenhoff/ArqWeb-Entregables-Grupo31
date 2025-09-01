package edu.tudai.arq.repository.mysql;

import edu.tudai.arq.dao.AbstractDAO;
import edu.tudai.arq.dao.ClienteDAO;
import edu.tudai.arq.dao.ProductoDAO;
import edu.tudai.arq.dto.ClienteConFacturacionDTO;
import edu.tudai.arq.dto.ProductoMayorRecaudacionDTO;
import edu.tudai.arq.entity.Cliente;
import edu.tudai.arq.entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLProductoDAO extends AbstractDAO implements ProductoDAO {


    protected MySQLProductoDAO(Connection cn) {
        super(cn);
    }

    @Override
    protected void createTableIfNotExists() {

    }

    @Override
    public ProductoMayorRecaudacionDTO getProductoMayorRecaudacion() {
        final String sql =
                "SELECT p.idProducto, p.nombre, p.valor, " +
                        "       SUM(fp.cantidad * p.valor) AS recaudacion " +
                        "FROM Producto p " +
                        "JOIN Factura_Producto fp ON p.idProducto = fp.idProducto " +
                        "GROUP BY p.idProducto, p.nombre, p.valor " +
                        "ORDER BY recaudacion DESC " +
                        "LIMIT 1";

        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return new ProductoMayorRecaudacionDTO(
                        rs.getInt("idProducto"),
                        rs.getString("nombre"),
                        rs.getFloat("valor"),
                        rs.getFloat("recaudacion")
                );
            }
            return null; // si no hay resultados

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener Producto con mayor recaudación!", e);
        }
    }

    @Override
    public void create(Producto entity) {

    }

    @Override
    public void update(Producto entity) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Producto findById(Long aLong) {
        return null;
    }

    @Override
    public List<Producto> findAll() {
        return null;
    }
}
