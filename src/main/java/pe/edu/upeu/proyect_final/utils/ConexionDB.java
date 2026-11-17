package pe.edu.upeu.proyect_final.utils;

import java.io.File;
import java.sql.*;

public class ConexionDB {
    private static Connection connection;

    //CONEXION MODO DEV
    /*public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("org.sqlite.JDBC");
                String url = "jdbc:sqlite:C:/sqlite/db/reserva.db";
                connection = DriverManager.getConnection(url);
                connection.setAutoCommit(true);

                try (Statement stmt = connection.createStatement()) {
                    stmt.execute("PRAGMA foreing_keys = ON");
                }
                System.out.println("Conexion success");
            }
            return connection;

        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a la DB" + e.getMessage());
        }
    }
}
     */
    //connection mod Prod
    //C:\Program Files (x86)
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()){
                String userHome = System.getProperty("user.home");
                File directories = new File(userHome+"/SistemaHoleroUPeU");
                if (!directories.exists()){
                    directories.mkdirs();
                }
                //la db se guardara C:\Users\SistemaHoteleroUPeU\reserva.db\
                String dbPath =directories.getAbsolutePath()+"/reserva.db";
                String url = "jdbc:sqlite:"+dbPath;
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(url);
                initDatabase();
                System.out.println("conexion success");
            }
            return connection;

        } catch (Exception e) {
            throw new RuntimeException("Error al conectar a la DB" + e.getMessage());
        }
    }
    private static void initDatabase() {
        // Usamos CREATE TABLE IF NOT EXISTS para que no de error si ya existen
        String sqlCliente = "CREATE TABLE IF NOT EXISTS cliente (" +
                "idcliente INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, apellidos TEXT, dni TEXT UNIQUE, email TEXT, genero TEXT);";

        String sqlUsuario = "CREATE TABLE IF NOT EXISTS usuario (" +
                "idusuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user TEXT UNIQUE, password TEXT, tipoUser TEXT, nombre TEXT, apellidos TEXT, dni TEXT);";

        String sqlHabitacion = "CREATE TABLE IF NOT EXISTS habitacion (" +
                "idhabitacion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "numero TEXT NOT NULL UNIQUE, numeroPiso TEXT, numeroHabitacion TEXT, " +
                "tipo TEXT NOT NULL, precio REAL NOT NULL, estado TEXT DEFAULT 'Disponible');";

        String sqlReserva = "CREATE TABLE IF NOT EXISTS reserva (" +
                "idreserva INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "clienteId INTEGER NOT NULL, habitacionId INTEGER NOT NULL, " +
                "fecha_reserva DATE NOT NULL, fecha_ingreso DATE NOT NULL, fecha_salida DATE NOT NULL, " +
                "estado TEXT DEFAULT 'R', total REAL NOT NULL, usuarioId INTEGER NOT NULL, " +
                "FOREIGN KEY(clienteId) REFERENCES cliente(idcliente), " +
                "FOREIGN KEY(usuarioId) REFERENCES usuario(idusuario), " +
                "FOREIGN KEY(habitacionId) REFERENCES habitacion(idhabitacion));";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sqlCliente);
            stmt.execute(sqlUsuario);
            stmt.execute(sqlHabitacion);
            stmt.execute(sqlReserva);

            // 3. INSERTAR USUARIO ADMIN POR DEFECTO (Si la tabla está vacía)
            // Esto es vital para que el alumno pueda loguearse tras instalar
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM usuario");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.execute("INSERT INTO usuario (user, password, tipoUser, nombre) " +
                        "VALUES ('admin', 'admin123', 'ADMIN', 'Administrador');");
                System.out.println("Usuario inicial 'admin' creado.");
            }

        } catch (SQLException e) {
            System.out.println("Error al inicializar tablas: " + e.getMessage());
        }
    }
}