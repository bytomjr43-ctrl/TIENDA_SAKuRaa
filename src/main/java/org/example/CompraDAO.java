package org.example;

import java.sql.*;
import java.time.LocalDate;

public class CompraDAO {

    public void registrarCompra(Compra compra) {
        String sqlCompra = "INSERT INTO compra (id_cliente, fecha, total) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, compra.getCliente().getId());
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setDouble(3, compra.getTotal());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idCompra = rs.getInt(1);
                registrarLineasCompra(conn, idCompra, compra);
            }

            System.out.println(" Compra registrada correctamente.");

        } catch (SQLException e) {
            System.err.println(" Error al registrar compra: " + e.getMessage());
        }
    }

    private void registrarLineasCompra(Connection conn, int idCompra, Compra compra) throws SQLException {
        String sqlLinea = "INSERT INTO linea_compra (id_compra, nombre_producto, cantidad, subtotal) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sqlLinea)) {
            for (LineaCompra lc : compra.getLineas()) {
                ps.setInt(1, idCompra);
                ps.setString(2, lc.getProducto().getNombre());
                ps.setInt(3, lc.getCantidad());
                ps.setDouble(4, lc.getSubtotal());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
