/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.repositorio;

import java.util.ArrayList;
import java.util.List;
import proyecto_swing.modelo.Curso;

/**
 *
 * @author Daryelin
 */
public class CursoRepositorio {

    private static List<Curso> cursos = new ArrayList<>();

    public void agregar(Curso curso) {
        cursos.add(curso);
    }

    public void actualizar(Curso curso) {

        for (int i = 0; i < cursos.size(); i++) {

            if (cursos.get(i).getId() == curso.getId()) {

                cursos.set(i, curso);
                return;

            }

        }

    }

    public void eliminar(int id) {

        cursos.removeIf(c -> c.getId() == id);

    }

    public List<Curso> listar() {

        return cursos;

    }

    public boolean existeCodigo(String codigo) {

        for (Curso c : cursos) {

            if (c.getCodigo().equalsIgnoreCase(codigo)) {

                return true;

            }

        }

        return false;

    }

    public boolean existeCodigoEnOtroCurso(String codigo, int id) {

        for (Curso c : cursos) {

            if (c.getCodigo().equalsIgnoreCase(codigo)
                    && c.getId() != id) {

                return true;

            }

        }

        return false;

    }
}
