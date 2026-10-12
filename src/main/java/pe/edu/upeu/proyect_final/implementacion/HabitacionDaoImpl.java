package pe.edu.upeu.proyect_final.implementacion;

import pe.edu.upeu.proyect_final.interfaces.IHabitacionDao;
import pe.edu.upeu.proyect_final.modelo.Cliente;
import pe.edu.upeu.proyect_final.modelo.Habitaciones;
import pe.edu.upeu.proyect_final.utils.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDaoImpl implements IHabitacionDao {
    @Override
    public boolean insert(Habitaciones h) {
        boolean flag = false;
        String query = "INSERT INTO habitacion(numero,numeroPiso,numeroHabitacion,tipo,precio,estado)VALUES(?,?,?,?,?,?);";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query);) {
            st.setInt(1,h.getNumero());
            st.setInt(2,h.getNumeroPiso());
            st.setInt(3,h.getIdhabitacion());
            st.setString(4,h.getTipo());
            st.setDouble(5,h.getPrecio());
            st.setString(6,h.getEstado());
            st.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            System.out.println("Error al incertar: " + e.getMessage());
        }
        return flag;

    }

    @Override
    public boolean update(Habitaciones h) {
        return false;
    }

    @Override
    public Habitaciones searchById(int id) {
        return null;
    }

    @Override
    public boolean delete(Habitaciones c) {
        return false;
    }

    @Override
    public List<Habitaciones> listAll() {

        List<Habitaciones> lista = new ArrayList<>();
        String query = "SELECT * FROM habitacion;";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
             ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()) {
                Habitaciones hl = new Habitaciones();
                hl.setIdhabitacion(rs.getInt("idhabitacion"));
                hl.setNumero(rs.getInt("numero"));
                hl.setNumeroPiso(rs.getInt("numeroPiso"));
                hl.setNumeroHabitacion(rs.getInt("numeroHabitacion"));
                hl.setTipo(rs.getString("tipo"));
                hl.setPrecio(rs.getDouble("precio"));
                hl.setEstado(rs.getString("estado"));
                lista.add(hl);

            }

        } catch (SQLException e) {
            System.out.println("Error al listar Cliente: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Habitaciones searchByNumHabitacion(String numeroHabitacion) {
        return null;
    }
}
