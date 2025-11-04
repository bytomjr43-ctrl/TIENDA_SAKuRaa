package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDao{

    public void registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, email, pass, direccion, telefono) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = new ConexionBD().getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getPasswordHash());
            ps.setString(4, cliente.getDireccionEnvio());
            ps.setString(5, cliente.getTelefono());
            ps.executeUpdate();

            System.out.println(" Cliente guardado en la base de datos.");

        } catch (SQLException e) {
            System.err.println(" Error al guardar cliente: " + e.getMessage());
        }
    }
}
