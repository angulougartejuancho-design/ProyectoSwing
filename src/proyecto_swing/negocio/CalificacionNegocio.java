/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.negocio;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import proyecto_swing.excepciones.MatriculaInvalidaException;
import proyecto_swing.excepciones.NotaException;
import proyecto_swing.modelo.Calificaciones;
import proyecto_swing.modelo.Curso;
import proyecto_swing.modelo.Estudiante;
import proyecto_swing.modelo.Matricula;
import proyecto_swing.repositorio.CalificacionRepositorio;

/**
 *
 * @author Anyel
 */
public class CalificacionNegocio {
    private final CalificacionRepositorio repositorio;
    private final MatriculaNegocio matriculaNegocio;
    private final Stack<String> historial;

    public CalificacionNegocio() {

        repositorio = new CalificacionRepositorio();
        matriculaNegocio = new MatriculaNegocio();
        historial = new Stack<>();

    }

    public void registrar(Calificaciones calificacion)
            throws NotaException, MatriculaInvalidaException {

        validarNota(calificacion.getNota());

        validarMatricula(calificacion.getEstudiante(), calificacion.getCurso());

        if (repositorio.existeCalificacion(
                calificacion.getEstudiante().getId(),
                calificacion.getCurso().getId())) {

            throw new MatriculaInvalidaException(
                    "Ya existe una calificación registrada para ese estudiante en ese curso.");

        }

        repositorio.agregar(calificacion);

        historial.push(
                "Calificación registrada: "
                + calificacion.getEstudiante().getNombre()
                + " -> "
                + calificacion.getCurso().getNombre()
                + " (" + calificacion.getNota() + ")"
        );

    }

    public void editar(Calificaciones calificacion)
            throws NotaException, MatriculaInvalidaException {

        validarNota(calificacion.getNota());

        validarMatricula(calificacion.getEstudiante(), calificacion.getCurso());

        if (repositorio.existeCalificacionEnOtroRegistro(
                calificacion.getEstudiante().getId(),
                calificacion.getCurso().getId(),
                calificacion.getId())) {

            throw new MatriculaInvalidaException(
                    "Ya existe otra calificación para ese estudiante en ese curso.");

        }

        repositorio.actualizar(calificacion);

        historial.push(
                "Calificación editada: "
                + calificacion.getEstudiante().getNombre()
                + " -> "
                + calificacion.getCurso().getNombre()
                + " (" + calificacion.getNota() + ")"
        );

    }

    public void eliminar(int id) {

        Calificaciones calificacion = repositorio.buscarPorId(id);

        if (calificacion == null) {
            return;
        }

        repositorio.eliminar(id);

        historial.push(
                "Calificación eliminada: "
                + calificacion.getEstudiante().getNombre()
                + " -> "
                + calificacion.getCurso().getNombre()
        );

    }

    public List<Calificaciones> obtenerTodas() {

        return repositorio.listar();

    }

    public List<Calificaciones> buscarPorEstudiante(String nombre) {

        List<Calificaciones> resultado = new ArrayList<>();

        for (Calificaciones c : repositorio.listar()) {

            if (c.getEstudiante().getNombre()
                    .toLowerCase()
                    .contains(nombre.toLowerCase())) {

                resultado.add(c);

            }

        }

        return resultado;
    }

    public List<Calificaciones> buscarPorCurso(String nombre) {

        List<Calificaciones> resultado = new ArrayList<>();

        for (Calificaciones c : repositorio.listar()) {

            if (c.getCurso().getNombre()
                    .toLowerCase()
                    .contains(nombre.toLowerCase())) {

                resultado.add(c);

            }

        }

        return resultado;
    }

    public List<Calificaciones> ordenarPorNota() {

        List<Calificaciones> copia = new ArrayList<>(repositorio.listar());

        copia.sort(Comparator.comparingDouble(Calificaciones::getNota));

        return copia;

    }

    public List<Calificaciones> ordenarPorEstudiante() {

        List<Calificaciones> copia = new ArrayList<>(repositorio.listar());

        copia.sort(Comparator.comparing(c -> c.getEstudiante().getNombre()));

        return copia;

    }

    public double calcularPromedioPorEstudiante(int idEstudiante) {

        double suma = 0;
        int cantidad = 0;

        for (Calificaciones c : repositorio.listar()) {

            if (c.getEstudiante().getId() == idEstudiante) {

                suma += c.getNota();
                cantidad++;

            }

        }

        if (cantidad == 0) {
            return 0;
        }

        return suma / cantidad;

    }

    public double calcularPromedioPorCurso(int idCurso) {

        double suma = 0;
        int cantidad = 0;

        for (Calificaciones c : repositorio.listar()) {

            if (c.getCurso().getId() == idCurso) {

                suma += c.getNota();
                cantidad++;

            }

        }

        if (cantidad == 0) {
            return 0;
        }

        return suma / cantidad;

    }

    public Map<String, Double> obtenerPromediosPorCurso() {

        Map<String, Double> sumas = new HashMap<>();
        Map<String, Integer> cantidades = new HashMap<>();

        for (Calificaciones c : repositorio.listar()) {

            String nombreCurso = c.getCurso().getNombre();

            sumas.put(nombreCurso,
                    sumas.getOrDefault(nombreCurso, 0.0) + c.getNota());

            cantidades.put(nombreCurso,
                    cantidades.getOrDefault(nombreCurso, 0) + 1);

        }

        Map<String, Double> promedios = new HashMap<>();

        for (String curso : sumas.keySet()) {

            promedios.put(curso, sumas.get(curso) / cantidades.get(curso));

        }

        return promedios;

    }

    public Map<String, Double> obtenerPromediosPorEstudiante() {

        Map<String, Double> sumas = new HashMap<>();
        Map<String, Integer> cantidades = new HashMap<>();

        for (Calificaciones c : repositorio.listar()) {

            String nombreEstudiante = c.getEstudiante().getNombre();

            sumas.put(nombreEstudiante,
                    sumas.getOrDefault(nombreEstudiante, 0.0) + c.getNota());

            cantidades.put(nombreEstudiante,
                    cantidades.getOrDefault(nombreEstudiante, 0) + 1);

        }

        Map<String, Double> promedios = new HashMap<>();

        for (String estudiante : sumas.keySet()) {

            promedios.put(estudiante, sumas.get(estudiante) / cantidades.get(estudiante));

        }

        return promedios;

    }

    public List<Calificaciones> obtenerAprobados() {

        List<Calificaciones> resultado = new ArrayList<>();

        for (Calificaciones c : repositorio.listar()) {

            if (c.getNota() >= 70) {

                resultado.add(c);

            }

        }

        return resultado;

    }

    public List<Calificaciones> obtenerReprobados() {

        List<Calificaciones> resultado = new ArrayList<>();

        for (Calificaciones c : repositorio.listar()) {

            if (c.getNota() < 70) {

                resultado.add(c);

            }

        }

        return resultado;

    }

    public Stack<String> getHistorial() {

        return historial;

    }

    private void validarNota(double nota) throws NotaException {

        if (nota < 0 || nota > 100) {

            throw new NotaException(
                    "La nota debe estar entre 0 y 100.");

        }

    }

    private void validarMatricula(Estudiante estudiante, Curso curso)
            throws MatriculaInvalidaException {

        boolean matriculado = false;

        for (Matricula m : matriculaNegocio.obtenerTodas()) {

            if (m.getEstudiante().getId() == estudiante.getId()
                    && m.getCurso().getId() == curso.getId()) {

                matriculado = true;
                break;

            }

        }

        if (!matriculado) {

            throw new MatriculaInvalidaException(
                    "El estudiante no está matriculado en el curso seleccionado.");

        }

    }
}
