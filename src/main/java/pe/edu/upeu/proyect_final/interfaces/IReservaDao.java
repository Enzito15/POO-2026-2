package pe.edu.upeu.proyect_final.interfaces;

import pe.edu.upeu.proyect_final.modelo.Reserva;
import java.util.List;

public interface IReservaDao {
        public boolean insert(Reserva r);
        public boolean update (Reserva r);
        public Reserva rearchById(int id);
        public boolean delete(Reserva r);
        public List<Reserva>rlistAll();
        public boolean updateStatus (Reserva r);
        public boolean validateHabitacion(Reserva r);

    }

