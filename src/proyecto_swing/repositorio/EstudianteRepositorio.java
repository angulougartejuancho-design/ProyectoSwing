/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.repositorio;

/**
 *
 * @author angul
 */
import java.util.ArrayList;
import java.util.List;
import proyecto_swing.modelo.Estudiante;

public class EstudianteRepositorio {

    private static final List<Estudiante> estudiantes = new ArrayList<>();

    public void agregar(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void actualizar(Estudiante estudiante) {

        for (int i = 0; i < estudiantes.size(); i++) {

            if (estudiantes.get(i).getId() == estudiante.getId()) {

                estudiantes.set(i, estudiante);
                return;

            }

        }
    }

    public void eliminar(int id) {

        estudiantes.removeIf(
                e -> e.getId() == id
        );

    }

    public List<Estudiante> listar() {

        return estudiantes;

    }

    public Estudiante buscarPorId(int id) {

        for (Estudiante e : estudiantes) {

            if (e.getId() == id) {

                return e;

            }

        }

        return null;

    }

    public boolean existeCorreo(String correo) {

        for (Estudiante e : estudiantes) {

            if (e.getCorreo().equalsIgnoreCase(correo)) {

                return true;

            }

        }

        return false;

    }

    public boolean existeCorreoEnOtroEstudiante(
            String correo,
            int id) {

        for (Estudiante e : estudiantes) {

            if (e.getCorreo().equalsIgnoreCase(correo)
                    && e.getId() != id) {

                return true;

            }

        }

        return false;

    }

}
