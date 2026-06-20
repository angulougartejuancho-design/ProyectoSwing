/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.negocio;

/**
 *
 * @author PC
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import proyecto_swing.modelo.Curso;
import proyecto_swing.modelo.Estudiante;
import proyecto_swing.modelo.Matricula;
import proyecto_swing.repositorio.MatriculaRepositorio;

public class MatriculaNegocio {

    private final MatriculaRepositorio repositorio;
    private final Stack<String> historial;

    public MatriculaNegocio() {

        repositorio = new MatriculaRepositorio();
        historial = new Stack<>();

    }

    public void registrar(Matricula matricula)
            throws Exception {

        Estudiante estudiante
                = matricula.getEstudiante();

        Curso curso
                = matricula.getCurso();

        if (!estudiante.isActivo()) {

            throw new Exception(
                    "El estudiante está inactivo."
            );

        }

        if (!curso.isActivo()) {

            throw new Exception(
                    "El curso está inactivo."
            );

        }

        boolean agregado
                = repositorio.agregar(matricula);

        if (!agregado) {

            throw new Exception(
                    "La matrícula ya existe."
            );

        }

        historial.push(
                "Matrícula registrada: "
                + estudiante.getNombre()
                + " -> "
                + curso.getNombre()
        );

    }

    public void eliminar(Matricula matricula) {

        repositorio.eliminar(matricula);

        historial.push(
                "Matrícula eliminada: "
                + matricula.getEstudiante().getNombre()
                + " -> "
                + matricula.getCurso().getNombre()
        );

    }

    public List<Matricula> obtenerTodas() {

        return repositorio.listar();

    }

    public List<Matricula> buscarPorEstudiante(
            String nombre) {

        List<Matricula> resultado
                = new ArrayList<>();

        for (Matricula m : repositorio.listar()) {

            if (m.getEstudiante()
                    .getNombre()
                    .toLowerCase()
                    .contains(
                            nombre.toLowerCase())) {

                resultado.add(m);

            }

        }

        return resultado;
    }

    public List<Matricula> buscarPorCurso(
            String nombre) {

        List<Matricula> resultado
                = new ArrayList<>();

        for (Matricula m : repositorio.listar()) {

            if (m.getCurso()
                    .getNombre()
                    .toLowerCase()
                    .contains(
                            nombre.toLowerCase())) {

                resultado.add(m);

            }

        }

        return resultado;
    }

    public Stack<String> getHistorial() {

        return historial;

    }

}
