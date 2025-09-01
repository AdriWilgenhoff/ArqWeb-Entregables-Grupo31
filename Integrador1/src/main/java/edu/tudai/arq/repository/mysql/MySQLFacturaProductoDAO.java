package edu.tudai.arq.repository.mysql;

import edu.tudai.arq.dao.AbstractDAO;
import edu.tudai.arq.dao.FacturaProductoDAO;
import edu.tudai.arq.entity.FacturaProducto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLFacturaProductoDAO extends AbstractDAO implements FacturaProductoDAO {

    protected MySQLFacturaProductoDAO(Connection cn) {
        super(cn);
        this.createTableIfNotExists();
    }

    @Override
    protected void createTableIfNotExists() {
        final String sql = "CREATE TABLE IF NOT EXISTS facturas_productos (" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "PRIMARY KEY (idFactura, idProducto)" +
                ")";

        try (Statement st = cn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla 'facturas_productos'", e);
        }
    }

    @Override
    public void create(FacturaProducto entity) {
        final String sql = "INSERT INTO facturas_productos (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdFactura());
            ps.setInt(2, entity.getIdProducto());
            ps.setInt(3, entity.getCantidad());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en create", e);
        }
    }

    @Override
    public void update(FacturaProducto entity) {
        final String sql = "UPDATE facturas_productos SET cantidad = ? WHERE idFactura = ? AND idProducto = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, entity.getCantidad());
            ps.setInt(2, entity.getIdFactura());
            ps.setInt(3, entity.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en update", e);
        }
    }

    @Override
    public void delete(Long aLong) {

    }


    public void delete(Integer idFactura, Integer idProducto) {
        final String sql = "DELETE FROM facturas_productos WHERE idFactura = ? AND idProducto = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en delete", e);
        }
    }

    @Override
    public void deleteAll() {
        try (Statement st = cn.createStatement()) {
            st.executeUpdate("DELETE FROM facturas_productos");
        } catch (SQLException e) {
            throw new RuntimeException("Error borrando todas las facturas_productos", e);
        }
    }

    @Override
    public FacturaProducto findById(Long aLong) {
        return null;
    }

    public FacturaProducto findById(Integer idFactura, Integer idProducto) {
        final String sql = "SELECT * FROM facturas_productos WHERE idFactura = ? AND idProducto = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idFactura);
            ps.setInt(2, idProducto);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en findById", e);
        }
    }

    private FacturaProducto mapRow(ResultSet rs) throws SQLException {
        int idFactura = rs.getInt("idFactura");
        int idProducto = rs.getInt("idProducto");
        int cantidad = rs.getInt("cantidad");
        return new FacturaProducto(idFactura, idProducto, cantidad);
    }

    @Override
    public List<FacturaProducto> findAll() {
        final String sql = "SELECT * FROM facturas_productos";
        List<FacturaProducto> facturaProductos = new ArrayList<>();

        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                facturaProductos.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en findAll", e);
        }

        return facturaProductos;
    }


}