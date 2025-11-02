package org.example;

import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection conn = ConexionBD.getConexion();

        if (conn != null) {
            System.out.println(" Prueba exitosa: la conexión funciona correctamente.");
            ConexionBD.cerrarConexion(conn);
        } else {
            System.err.println(" Error: la conexión no se pudo establecer.");
        }
    }
}
