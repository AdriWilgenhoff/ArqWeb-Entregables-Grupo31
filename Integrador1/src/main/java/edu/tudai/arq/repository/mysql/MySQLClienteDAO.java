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

public class MySQLClienteDAO extends AbstractDAO implements ClienteDAO{

    public MySQLClienteDAO(Connection cn) {
      super(cn);
        //createTableIfNotExists();
    }

    @Override
    protected void createTableIfNotExists() {

    }

    @Override
    public Cliente findById(Long id) {
        final String sql = "SELECT idCliente, nombre, email FROM Cliente WHERE idCliente = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? new Cliente(
                        rs.getInt("idCliente"),
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

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {

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




    /*private void createTableIfNotExists() {
        final String sql = "CREATE TABLE IF NOT EXISTS compras (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "usuario_id INT NOT NULL, " +
                "producto_id INT NOT NULL, " +
                "cantidad INT NOT NULL, " +
                "FOREIGN KEY (usuario_id) REFERENCES usuarios(id), " +
                "FOREIGN KEY (producto_id) REFERENCES productos(id)" +
                ")";

        try (Statement st = cn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla 'compras'", e);
        }
    }

    @Override
    public Compra findById(Long id) {
        final String sql = "SELECT id, usuario_id, producto_id, cantidad FROM compras WHERE id = ?";

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
    public void create(Compra c) {
        final String sql = "INSERT INTO compras (usuario_id, producto_id, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, c.getUsuarioId());
            ps.setLong(2, c.getProductoId());
            ps.setInt(3, c.getCantidad());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    c.setId(keys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en create", e);
        }
    }

    @Override
    public void update(Compra c) {
        final String sql = "UPDATE compras SET usuario_id = ?, producto_id = ?, cantidad = ? WHERE id = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setLong(1, c.getUsuarioId());
            ps.setLong(2, c.getProductoId());
            ps.setInt(3, c.getCantidad());
            ps.setLong(4, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error en update", e);
        }
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM compras WHERE id = ?";

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
            st.executeUpdate("DELETE FROM compras");
            st.executeUpdate("ALTER TABLE compras AUTO_INCREMENT = 1");
        } catch (SQLException e) {
            throw new RuntimeException("Error borrando todas las compras", e);
        }
    }
*/
}