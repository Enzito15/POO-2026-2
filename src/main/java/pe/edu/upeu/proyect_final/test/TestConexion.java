package pe.edu.upeu.proyect_final.test;

import pe.edu.upeu.proyect_final.utils.ConexionDB;

import java.sql.Connection;

public class TestConexion {
    public static void testConexion(){
        try {
            Connection connection = ConexionDB.getConnection();
            if (connection !=null && connection.isClosed()){
                System.out.println("conexion correcta");
            }else{
                System.out.println("error de conexion");
            }

        }catch (Exception e){
            System.out.println("error de conexion"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testConexion();
    }
}
