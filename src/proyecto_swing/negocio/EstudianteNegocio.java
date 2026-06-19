/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.negocio;

/**
 *
 * @author angul
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import proyecto_swing.excepciones.DatoInvalidoException;
import proyecto_swing.excepciones.RegistroDuplicadoException;
import proyecto_swing.modelo.Estudiante;
import proyecto_swing.repositorio.EstudianteRepositorio;

public class EstudianteNegocio {

     private final EstudianteRepositorio repositorio;

    public EstudianteNegocio() {
        repositorio = new EstudianteRepositorio();
    }

    public void registrar(Estudiante estudiante)
            throws DatoInvalidoException, RegistroDuplicadoException {

        validar(estudiante);

        if (repositorio.existeCorreo(estudiante.getCorreo())) {
            throw new RegistroDuplicadoException(
                    "Ya existe un estudiante registrado con ese correo."
            );
        }

        repositorio.agregar(estudiante);
    }

    public void editar(Estudiante estudiante)
            throws DatoInvalidoException, RegistroDuplicadoException {

        validar(estudiante);

        if (repositorio.existeCorreoEnOtroEstudiante(
                estudiante.getCorreo(), estudiante.getId())) {
            throw new RegistroDuplicadoException(
                    "El correo ya pertenece a otro estudiante."
            );
        }

        repositorio.actualizar(estudiante);
    }

    public void eliminar(int id) {
        repositorio.eliminar(id);
    }

    public void exportarCSV(String ruta) throws IOException {

    FileWriter writer = new FileWriter(ruta);

    writer.write("ID,Nombre,Edad,Carrera,Genero,Activo,Correo,Telefono,Observaciones\n");

    for (Estudiante e : repositorio.listar()) {

        writer.write(
                e.getId() + "," +
                e.getNombre() + "," +
                e.getEdad() + "," +
                e.getCarrera() + "," +
                e.getGenero() + "," +
                e.isActivo() + "," +
                e.getCorreo() + "," +
                e.getTelefono() + "," +
                e.getObservaciones() + "\n"
        );
    }

    writer.close();
}

    public List<Estudiante> obtenerTodos() {
        return repositorio.listar();
    }

    public List<Estudiante> buscarPorNombre(String nombre) {
        List<Estudiante> resultado = new ArrayList<>();

        for (Estudiante e : repositorio.listar()) {
            if (e.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    public List<Estudiante> buscarPorCarrera(String carrera) {
        List<Estudiante> resultado = new ArrayList<>();

        for (Estudiante e : repositorio.listar()) {
            if (e.getCarrera().equalsIgnoreCase(carrera)) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    public List<Estudiante> buscarPorEstado(boolean activo) {
        List<Estudiante> resultado = new ArrayList<>();

        for (Estudiante e : repositorio.listar()) {
            if (e.isActivo() == activo) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    public List<Estudiante> ordenarPorNombre() {
        List<Estudiante> copia = new ArrayList<>(repositorio.listar());
        copia.sort(Comparator.comparing(Estudiante::getNombre));
        return copia;
    }

    public List<Estudiante> ordenarPorEdad() {
        List<Estudiante> copia = new ArrayList<>(repositorio.listar());
        copia.sort(Comparator.comparingInt(Estudiante::getEdad));
        return copia;
    }

    private void validar(Estudiante e) throws DatoInvalidoException {

        // ID
        if (e.getId() <= 0) {
            throw new DatoInvalidoException(
                    "El ID debe ser mayor que 0."
            );
        }

        // Nombre
        if (e.getNombre() == null || e.getNombre().trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El nombre es obligatorio."
            );
        }

        if (e.getNombre().trim().length() < 3) {
            throw new DatoInvalidoException(
                    "El nombre debe tener mínimo 3 caracteres."
            );
        }

        // Edad
        if (e.getEdad() <= 0) {
            throw new DatoInvalidoException(
                    "La edad debe ser mayor que 0."
            );
        }
        if (e.getEdad() >80) {
            throw new DatoInvalidoException(
                    "La edad no es válida."
            );
        }
        

        // Carrera
        if (e.getCarrera() == null || e.getCarrera().trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "La carrera es obligatoria."
            );
        }

        // Género
        if (e.getGenero() == null || e.getGenero().trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "Debe seleccionar el género del estudiante."
            );
        }

        // Correo
        if (e.getCorreo() == null || e.getCorreo().trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El correo es obligatorio."
            );
        }

        if (!e.getCorreo().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new DatoInvalidoException(
                    "El correo no tiene un formato válido."
            );
        }

        // Teléfono
        if (e.getTelefono() == null || e.getTelefono().trim().isEmpty()) {
            throw new DatoInvalidoException(
                    "El teléfono es obligatorio."
            );
        }

        if (!e.getTelefono().matches("\\d{8}")) {
            throw new DatoInvalidoException(
                    "El teléfono debe contener exactamente 8 dígitos numéricos."
            );
        }
        
        

    }
}
