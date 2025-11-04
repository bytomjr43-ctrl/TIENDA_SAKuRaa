package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public void registrarProducto(Producto producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.executeUpdate();

            System.out.println(" Producto guardado en la base de datos.");

        } catch (SQLException e) {
            System.err.println(" Error al guardar producto: " + e.getMessage());
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection conn = new ConexionBD().getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        "2024-01-01",
                        null
                );
                productos.add(p);
            }

        } catch (SQLException e) {
            System.err.println(" Error al listar productos: " + e.getMessage());
        }

        return productos;
    }

    public void actualizarStock(String nombre, int nuevoStock) {
        String sql = "UPDATE producto SET stock = ? WHERE nombre = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, nuevoStock);
            ps.setString(2, nombre);
            ps.executeUpdate();

            System.out.println(" Stock actualizado para " + nombre);

        } catch (SQLException e) {
            System.err.println(" Error al actualizar stock: " + e.getMessage());
        }
    }
}
