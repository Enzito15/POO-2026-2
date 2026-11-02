package pe.edu.upeu.proyect_final.interfaces;

import pe.edu.upeu.proyect_final.modelo.Usuario;

public interface IUsuarioDao {
    public Usuario login(String user, String password);
    public boolean insert_new_user(Usuario u);
}
