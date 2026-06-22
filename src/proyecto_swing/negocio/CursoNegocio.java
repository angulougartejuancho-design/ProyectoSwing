/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.negocio;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import proyecto_swing.excepciones.DatoInvalidoException;
import proyecto_swing.excepciones.RegistroDuplicadoException;
import proyecto_swing.modelo.Curso;
import proyecto_swing.repositorio.CursoRepositorio;
/**
 *
 * @author Daryelin
 */
public class CursoNegocio {
     private final CursoRepositorio repositorio;

    public CursoNegocio() {

        repositorio = new CursoRepositorio();

    }

    public void registrar(Curso curso)
            throws DatoInvalidoException,
            RegistroDuplicadoException {

        validar(curso);

        if (repositorio.existeCodigo(curso.getCodigo())) {

            throw new RegistroDuplicadoException(
                    "Ya existe un curso con ese código.");

        }

        repositorio.agregar(curso);

    }

    public void editar(Curso curso)
            throws DatoInvalidoException,
            RegistroDuplicadoException {

        validar(curso);

        if (repositorio.existeCodigoEnOtroCurso(
                curso.getCodigo(),
                curso.getId())) {

            throw new RegistroDuplicadoException(
                    "Ese código pertenece a otro curso.");

        }

        repositorio.actualizar(curso);

    }

    public void eliminar(int id) {

        repositorio.eliminar(id);

    }

    public List<Curso> obtenerTodos() {

        return repositorio.listar();

    }

    public List<Curso> buscarPorCodigo(String codigo) {

        List<Curso> resultado = new ArrayList<>();

        for (Curso c : repositorio.listar()) {

            if (c.getCodigo().toLowerCase()
                    .contains(codigo.toLowerCase())) {

                resultado.add(c);

            }

        }

        return resultado;

    }

    public List<Curso> buscarPorNombre(String nombre) {

        List<Curso> resultado = new ArrayList<>();

        for (Curso c : repositorio.listar()) {

            if (c.getNombre().toLowerCase()
                    .contains(nombre.toLowerCase())) {

                resultado.add(c);

            }

        }

        return resultado;

    }

    public List<Curso> ordenarPorCodigo() {

        List<Curso> copia =
                new ArrayList<>(repositorio.listar());

        copia.sort(
                Comparator.comparing(Curso::getCodigo));

        return copia;

    }

    public List<Curso> ordenarPorNombre() {

        List<Curso> copia =
                new ArrayList<>(repositorio.listar());

        copia.sort(
                Comparator.comparing(Curso::getNombre));

        return copia;

    }

    private void validar(Curso curso)
            throws DatoInvalidoException {

        if (curso.getId() <= 0) {

            throw new DatoInvalidoException(
                    "El ID debe ser mayor que 0.");

        }

        if (curso.getCodigo() == null
                || curso.getCodigo().trim().isEmpty()) {

            throw new DatoInvalidoException(
                    "El código es obligatorio.");

        }

        if (curso.getNombre() == null
                || curso.getNombre().trim().isEmpty()) {

            throw new DatoInvalidoException(
                    "El nombre es obligatorio.");

        }

        if (curso.getCreditos() <= 0) {

            throw new DatoInvalidoException(
                    "Los créditos deben ser mayores que 0.");

        }

        if (curso.getProfesor() == null
                || curso.getProfesor().trim().isEmpty()) {

            throw new DatoInvalidoException(
                    "El profesor es obligatorio.");

        }

    }
}
