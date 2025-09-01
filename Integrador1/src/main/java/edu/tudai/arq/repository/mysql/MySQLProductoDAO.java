package edu.tudai.arq.repository.mysql;

import edu.tudai.arq.dao.AbstractDAO;
import edu.tudai.arq.dao.ClienteDAO;
import edu.tudai.arq.dao.ProductoDAO;
import edu.tudai.arq.dto.ClienteConFacturacionDTO;
import edu.tudai.arq.dto.ProductoMayorRecaudacionDTO;
import edu.tudai.arq.entity.Cliente;
import edu.tudai.arq.entity.FacturaProducto;
import edu.tudai.arq.entity.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductoDAO extends AbstractDAO implements ProductoDAO {


    protected MySQLProductoDAO(Connection cn) {
        super(cn);
    }

    @Override
    protected void createTableIfNotExists() {
        final String sql = "CREATE TABLE IF NOT EXISTS Producto (" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(255) NOT NULL, " +
                "valor FLOAT NOT NULL, " +
                "PRIMARY KEY (idProducto)" +
                ")";

        try (Statement st = cn.createStatement()) {
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Error creando tabla 'Producto'", e);
        }
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
        final String sql = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdProducto());
            ps.setString(2, entity.getNombre());
            ps.setFloat(3, entity.getValor());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el producto", e);
        }
    }

    @Override
    public void update(Producto entity) {
        final String sql = "UPDATE Producto SET nombre = ?, valor = ? WHERE idProducto = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, entity.getNombre());
            ps.setFloat(2, entity.getValor());
            ps.setInt(3, entity.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el producto", e);
        }
    }

    @Override
    public void delete(Long aLong) {} //

    @Override
    public void delete(Integer idProducto) {
        final String sql = "DELETE FROM Producto WHERE idProducto = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el producto", e);
        }
    }

    @Override
    public void deleteAll() {
        try (Statement st = cn.createStatement()) {
            st.executeUpdate("DELETE FROM Producto");
        } catch (SQLException e) {
            throw new RuntimeException("Error borrando todos los productos", e);
        }
    }

    public Producto findById(Long aLong) {
        return null;
    } //

    @Override
    public Producto findById(Integer idProducto) {
        final String sql = "SELECT * FROM Producto WHERE idProducto = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idProducto);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el producto por ID", e);
        }
    }


    private Producto mapRow(ResultSet rs) throws SQLException {
        int idProducto = rs.getInt("idProducto");
        String nombre = rs.getString("nombre");
        float valor = rs.getFloat("valor");
        return new Producto(idProducto, nombre, valor);
    }

    @Override
    public List<Producto> findAll() {
        final String sql = "SELECT * FROM Producto";
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productos.add(mapRow(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error en findAll", e);
        }

        return productos;
    }
}