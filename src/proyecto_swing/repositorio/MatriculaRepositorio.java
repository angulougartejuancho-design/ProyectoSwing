/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.repositorio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import proyecto_swing.modelo.Matricula;

/**
 *
 * @author PC
 */

public class MatriculaRepositorio {

    private static final List<Matricula> matriculas = new ArrayList<>();
    private static final Set<String> controlDuplicados = new HashSet<>();

    public boolean agregar(Matricula matricula) {

        String clave
                = matricula.getEstudiante().getId()
                + "-"
                + matricula.getCurso().getId();

        if (controlDuplicados.contains(clave)) {
            return false;
        }

        matriculas.add(matricula);
        controlDuplicados.add(clave);

        return true;
    }

    public void eliminar(Matricula matricula) {

        String clave
                = matricula.getEstudiante().getId()
                + "-"
                + matricula.getCurso().getId();

        matriculas.remove(matricula);

        controlDuplicados.remove(clave);
    }

    public List<Matricula> listar() {
        return matriculas;
    }
}
