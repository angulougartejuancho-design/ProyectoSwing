/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.repositorio;

import java.util.ArrayList;
import java.util.List;

import proyecto_swing.modelo.Calificaciones;
/**
 *
 * @author Anyel
 */
public class CalificacionRepositorio {
    private static final List<Calificaciones> calificaciones = new ArrayList<>();

    public void agregar(Calificaciones calificacion) {

        calificaciones.add(calificacion);

    }

    public void actualizar(Calificaciones calificacion) {

        for (int i = 0; i < calificaciones.size(); i++) {

            if (calificaciones.get(i).getId() == calificacion.getId()) {

                calificaciones.set(i, calificacion);
                return;

            }

        }
    }

    public void eliminar(int id) {

        calificaciones.removeIf(c -> c.getId() == id);

    }

    public List<Calificaciones> listar() {

        return calificaciones;

    }

    public Calificaciones buscarPorId(int id) {

        for (Calificaciones c : calificaciones) {

            if (c.getId() == id) {

                return c;

            }

        }

        return null;

    }

    public boolean existeCalificacion(int idEstudiante, int idCurso) {

        for (Calificaciones c : calificaciones) {

            if (c.getEstudiante().getId() == idEstudiante
                    && c.getCurso().getId() == idCurso) {

                return true;

            }

        }

        return false;

    }

    public boolean existeCalificacionEnOtroRegistro(int idEstudiante, int idCurso, int id) {

        for (Calificaciones c : calificaciones) {

            if (c.getEstudiante().getId() == idEstudiante
                    && c.getCurso().getId() == idCurso
                    && c.getId() != id) {

                return true;

            }

        }

        return false;

    }
}
