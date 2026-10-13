package pe.edu.upeu.proyect_final.interfaces;

import pe.edu.upeu.proyect_final.modelo.Habitaciones;

import java.util.List;

public interface IHabitacionDao {
public boolean insert(Habitaciones h);
public boolean update (Habitaciones h);
public Habitaciones searchById(int id);
public boolean delete(Habitaciones c);
public List<Habitaciones> listAll();

public Habitaciones searchByNumero(String numero);
}
