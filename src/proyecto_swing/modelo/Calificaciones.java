/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_swing.modelo;

/**
 *
 * @author Anyel
 */
public class Calificaciones {
    private int id;
    private Estudiante estudiante;
    private Curso curso;
    private double nota;
    private String observacion;

    public Calificaciones() {
    }

    public Calificaciones(int id, Estudiante estudiante, Curso curso, double nota, String observacion) {
        this.id = id;
        this.estudiante = estudiante;
        this.curso = curso;
        this.nota = nota;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return nota >= 70 ? "Aprobado" : "Reprobado";
    }

    @Override
    public String toString() {
        return estudiante.getNombre() + " - " + curso.getNombre() + " (" + nota + ")";
    }
}

