package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/tienda_sakura?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public static Connection getConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(" Conexión establecida correctamente con la base de datos.");
        } catch (SQLException e) {
            System.err.println(" Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }


    public static void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println(" Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println(" Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
