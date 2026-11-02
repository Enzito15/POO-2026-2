package pe.edu.upeu.proyect_final.implementacion;

import pe.edu.upeu.proyect_final.interfaces.IUsuarioDao;
import pe.edu.upeu.proyect_final.modelo.Usuario;
import pe.edu.upeu.proyect_final.utils.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public Usuario login(String user, String password) {
        Usuario us = null;
        String query = " SELECT * FROM usuario WHERE user = ? AND password = ? ";
        try(Connection cn = ConexionDB.getConnection(); PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1,user);
            st.setString(2,password);
            try (ResultSet rs = st.executeQuery()){
                if (rs.next()){
                    us = new Usuario();
                    //Data Table User
                    us.setIdusuario(rs.getInt("idusuario"));
                    us.setUser(rs.getString("user"));
                    us.setTipoUser(rs.getString("tipoUser"));
                    // Data heredada
                    us.setNombre(rs.getString("nombre"));
                    us.setApellidos(rs.getString("apellidos"));
                    us.setDni(rs.getString("dni"));
                    System.out.println("Login Exitoso");
                }
            }
        }catch (SQLException e){
            System.out.println("Error en el login"+e.getMessage());
        }
        return us;
    }

    @Override
    public boolean insert_new_user(Usuario u) {
        return false;
    }
}
