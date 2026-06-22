# Sistema Académico en Java Swing

## Descripción del sistema

El Sistema Académico en Java Swing es una aplicación de escritorio desarrollada para gestionar información relacionada con estudiantes, cursos, matrículas, calificaciones y reportes. El sistema aplica una arquitectura por capas (modelo, repositorio, negocio y presentación) y utiliza Java Swing para la interfaz gráfica.

Entre sus principales funciones se encuentran el registro, edición, eliminación, búsqueda, ordenamiento y consulta de información, así como la generación de reportes estadísticos y la exportación de datos.

# Integrantes del grupo

- Juan 
- Daniel 
- Daryelin
- Anyel
# Requisitos para ejecutar

- Java JDK 17 o superior.
- NetBeans IDE (versión compatible con Java Swing).
- Sistema operativo Windows, Linux o macOS.
- Git (opcional, para clonar el repositorio).
- Espacio disponible para almacenar los archivos de datos del proyecto.

# Instrucciones de uso

1. Clonar o descargar el proyecto.
2. Abrir el proyecto en NetBeans.
3. Compilar el proyecto.
4. Ejecutar la clase MainFrame.
5. Utilizar las pestañas del sistema para acceder a:
   - Gestión de estudiantes.
   - Gestión de cursos.
   - Gestión de matrículas.
   - Gestión de calificaciones.
   - Reportes.
6. Registrar la información correspondiente.
7. Consultar y generar reportes desde el módulo de Reportes.
8. Exportar la información cuando sea necesario.


# Estructura de carpetas

ProyectoSwingAcademico/
│
├── src/
│   ├── modelo/
│   ├── repositorio/
│   ├── negocio/
│   ├── presentacion/
│   ├── excepciones/
│   
│
├── datos/
│   
│   
│   
│   
│   
│
├── documentacion/
│
├── README.md
│
└── .gitignore
---

# Módulos desarrollados

## Módulo de Estudiantes
- Registro de estudiantes.
- Edición de información.
- Eliminación con confirmación.
- Búsqueda por nombre, carrera y estado.
- Ordenamiento por nombre y edad.

## Módulo de Cursos
- Registro de cursos.
- Edición y eliminación.
- Consulta y búsqueda.
- Ordenamiento por código y nombre.

## Módulo de Matrículas
- Registro de matrículas.
- Consulta de matrículas.
- Eliminación.
- Control de duplicados.
- Registro en historial de acciones.

## Módulo de Calificaciones
- Registro y edición de notas.
- Eliminación de calificaciones.
- Cálculo de promedios.
- Identificación de aprobados y reprobados.

## Módulo de Reportes
- Cantidad de estudiantes por carrera.
- Cantidad de estudiantes activos e inactivos.
- Cantidad de cursos activos e inactivos.
- Cantidad de matrículas por curso.
- Promedio general por curso.
- Cantidad de estudiantes aprobados.
- Cantidad de estudiantes reprobados.
- Historial de acciones.
- Exportación de reportes.

---

# Explicación del uso de Git

El proyecto fue desarrollado utilizando Git para el control de versiones y GitHub para el trabajo colaborativo.

Se emplearon ramas independientes para el desarrollo de funcionalidades específicas, las cuales posteriormente fueron integradas mediante Pull Requests.

Ramas utilizadas:

- main: versión estable del proyecto.
- develop: integración de funcionalidades.
- feature/estudiantes: desarrollo del módulo de estudiantes.
- feature/cursos: desarrollo del módulo de cursos.
- feature/matricula: desarrollo del módulo de matrículas.
- feature/calificaciones-reportes`: desarrollo del módulo de calificaciones y reportes.

Cada integrante realizó commits descriptivos para registrar el avance de sus tareas y facilitar el seguimiento de cambios.


