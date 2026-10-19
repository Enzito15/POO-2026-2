package pe.edu.upeu.proyect_final.implementacion;

import pe.edu.upeu.proyect_final.interfaces.IReservaDao;
import pe.edu.upeu.proyect_final.modelo.Cliente;
import pe.edu.upeu.proyect_final.modelo.Habitaciones;
import pe.edu.upeu.proyect_final.modelo.Reserva;
import pe.edu.upeu.proyect_final.utils.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDaoImpl implements IReservaDao {

    @Override
    public boolean insert(Reserva r) {
        boolean flag = false;
        String query = "INSERT INTO reserva (clienteId,habitacionId,fecha_reserva,fecha_ingreso," +
                "fecha_salida,estado,total) VALUES(?,?,?,?,?,?,?)";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query);) {
            st.setInt(1, r.getCliente().getIdcliente());
            st.setInt(2, r.getHabitacion().getIdhabitacion());
            //El LocalDate .toString devuelve "yyyy-MM-DD"
            st.setString(3, r.getFecha_reserva().toString());
            st.setString(4, r.getFecha_ingreso().toString());
            st.setString(5, r.getFecha_salida().toString());
            st.setString(6, r.getEstado());
            st.setDouble(7, r.getTotal());
            st.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            System.out.println("Error al incertar: " + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean update(Reserva r) {
        return false;
    }

    @Override
    public Reserva rearchById(int id) {
        return null;
    }

    @Override
    public boolean delete(Reserva r) {
        return false;
    }

    @Override
    public List<Reserva> rlistAll() {
        List<Reserva> lista = new ArrayList<>();
        String query = " SELECT r.*,c.nombre|| '' ||c.apellidos AS nombre_completo," +
                "c.dni,c.email,h.numero,h.numeroPiso," +
                "h.numeroHabitacion,h.tipo,h.estado AS estado_hab" +
                "FROM reserva r,cliente c,habitacion h" +
                "WHERE r.clienteId = c.idcliente" +
                "AND r.habitacionId = h.idhabitacion";
        try (Connection cn = ConexionDB.getConnection();
             PreparedStatement st = cn.prepareStatement(query);
             ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()) {
                //instancia de objetos relacionados
                Cliente cl = new Cliente();
                cl.setIdcliente(rs.getInt("idcliente"));
                cl.setNombre(rs.getString("nombre_completo"));
                cl.setDni(rs.getString("dni"));
                cl.setEmail(rs.getString("email"));

                Habitaciones h = new Habitaciones();
                h.setIdhabitacion(rs.getInt("habitacionId"));
                h.setNumero(rs.getString("numero"));
                h.setPrecio(rs.getDouble("precio"));
                h.setTipo(rs.getString("tipo"));
                //crear la reserva y asignar objetos
                Reserva r = new Reserva();
                r.setIdreserva(rs.getInt("idreserva"));
                r.setFecha_reserva(LocalDate.parse(rs.getString("fecha_reserva")));
                r.setFecha_ingreso(LocalDate.parse(rs.getString("fecha_ingreso")));
                r.setFecha_salida(LocalDate.parse(rs.getString("fecha_salida")));
                r.setTotal(rs.getDouble("total"));
                r.setEstado(rs.getString("estado"));
                //conectamos los objetos
                r.setCliente(cl);
                r.setHabitacion(h);
                lista.add(r);

            }

        } catch (SQLException e) {
            System.out.println("Error al listar Reserva: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean updateStatus(Reserva r) {
        return false;
    }

    @Override
    public boolean validateHabitacion(Reserva r) {
        return false;
    }
}
