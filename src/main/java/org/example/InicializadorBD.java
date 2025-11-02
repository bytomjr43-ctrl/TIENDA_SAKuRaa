package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorBD {

    public static void crearEstructura() {
        // 🔹 Código SQL para crear la base de datos y tablas si no existen
        String crearBD = "CREATE DATABASE IF NOT EXISTS tienda_sakura;";
        String usarBD = "USE tienda_sakura;";

        String crearTablaCliente = """
                CREATE TABLE IF NOT EXISTS cliente (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100),
                    email VARCHAR(100),
                    pass VARCHAR(100),
                    direccion VARCHAR(150),
                    telefono VARCHAR(20)
                );
                """;

        String crearTablaProducto = """
                CREATE TABLE IF NOT EXISTS producto (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(100),
                    precio DECIMAL(10,2),
                    stock INT
                );
                """;

        String crearTablaCompra = """
                CREATE TABLE IF NOT EXISTS compra (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    id_cliente INT,
                    fecha DATE,
                    total DECIMAL(10,2),
                    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
                );
                """;

        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(crearBD);
            stmt.executeUpdate(usarBD);
            stmt.executeUpdate(crearTablaCliente);
            stmt.executeUpdate(crearTablaProducto);
            stmt.executeUpdate(crearTablaCompra);

            System.out.println("✅ Base de datos y tablas creadas correctamente (si no existían).");

        } catch (SQLException e) {
            System.err.println("❌ Error al crear la estructura de la base de datos: " + e.getMessage());
        }
    }
}
