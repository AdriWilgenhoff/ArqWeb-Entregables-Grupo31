package edu.tudai.arq.repository.mysql;

import edu.tudai.arq.dao.AbstractDAO;
import edu.tudai.arq.dao.ClienteDAO;
import edu.tudai.arq.dto.ClienteConFacturacionDTO;
import edu.tudai.arq.entity.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLClienteDAO extends AbstractDAO implements ClienteDAO {

    protected MySQLClienteDAO(Connection cn) {
        super(cn);
        //this.createTableIfNotExists();
    }

    @Override
    protected void createTableIfNotExists() {
        final String sql = "CREATE TABLE IF NOT EXISTS clientes (" +
                "idCliente LONG NOT NULL " +
                "nombre STRING NOT NULL" +
                "email STRING NOT NULL" +
                ")";
        try (Statement st = cn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear clientes");
        }
    }

    @Override
    public Cliente findById(Long id) {
        final String sql = "SELECT idCliente, nombre, email FROM Cliente WHERE idCliente = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? new Cliente(
                        rs.getLong("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email")
                ) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en findById", e);
        }
    }

    @Override
    public List<Cliente> findAll() {
        return null;
    }

    @Override
    public void create(Cliente c) {
        final String sql = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?,?,?)";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en create", e);
        }
    }

    @Override
    public void update(Cliente entity) {
        final String sql = "UPDATE cliente SET nombre=?, email=? WHERE idCliente=?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getNombre());
            ps.setString(3, entity.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en update", e);
        }
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM clientes WHERE idCliente=?";
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
            final String sql = "DELETE FROM clientes";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error borrando los clientes", e);
        }

    }

    public List<ClienteConFacturacionDTO> getClientesPorMayorFacturacionDesc() {
        final String sql =
                "SELECT c.idCliente, c.nombre, c.email, " +
                        "       SUM(p.valor * fp.cantidad) AS totalFacturado " +
                        "FROM Cliente c " +
                        "JOIN Factura f            USING (idCliente) " +
                        "JOIN Factura_Producto fp  USING (idFactura) " +
                        "JOIN Producto p           USING (idProducto) " +
                        "GROUP BY c.idCliente, c.nombre, c.email " +
                        "ORDER BY totalFacturado DESC";

        List<ClienteConFacturacionDTO> clientes = new ArrayList<>();

        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clientes.add(new ClienteConFacturacionDTO(
                        rs.getInt("idCliente"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getFloat("totalFacturado")
                ));
            }

            return clientes;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener Clientes por facturación desc!", e);
        }
    }
}

