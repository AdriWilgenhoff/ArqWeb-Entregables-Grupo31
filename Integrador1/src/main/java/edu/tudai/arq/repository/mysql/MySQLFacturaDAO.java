package edu.tudai.arq.repository.mysql;

import edu.tudai.arq.dao.AbstractDAO;
import edu.tudai.arq.dao.FacturaDAO;
import edu.tudai.arq.entity.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLFacturaDAO extends AbstractDAO implements FacturaDAO {
    protected MySQLFacturaDAO(Connection cn) {
        super(cn);
        this.createTableIfNotExists();
    }

    @Override
    protected void createTableIfNotExists() {
        final String sql = "CREATE TABLE IF NOT EXISTS facturas (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "idCliente INT NOT NULL" +
                ")";

        try (Statement st = cn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla 'facturas'", e);
        }
    }


    @Override
    public void create(Factura entity) {
        final String sql = "INSERT INTO facturas (idCliente) VALUES (?)";

        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entity.getIdCliente());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    entity.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en create", e);
        }
    }

    @Override
    public void update(Factura entity) {
        final String sql = "UPDATE facturas SET idCliente = ? WHERE id = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(3, entity.getIdCliente());
            ps.setLong(3, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en update", e);
        }
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM facturas WHERE id = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en delete", e);
        }
    }

    @Override
    public void deleteAll() {
        try (Statement st = cn.createStatement()) {
            st.executeUpdate("DELETE FROM facturas");
            st.executeUpdate("ALTER TABLE facturas AUTO_INCREMENT = 1");
        } catch (SQLException e) {
            throw new RuntimeException("Error borrando todas las facturas", e);
        }
    }

    @Override
    public Factura findById(Long id) {
        final String sql = "SELECT * FROM facturas WHERE id = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en findById", e);
        }
    }

    @Override
    public List<Factura> findAll() {
        final String sql = "SELECT * FROM facturas";
        List<Factura> facturas = new ArrayList<>();

        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                facturas.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en findAll", e);
        }

        return facturas;
    }

    private Factura mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int idCliente = rs.getInt("idCliente");
        return new Factura(id, idCliente);
    }
}
