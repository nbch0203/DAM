package app;

import dao.*;
import modelos.*;
import modelos.Inscripcion.EstadoInscripcion;
import servicios.*;
import utils.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Aplicación Principal para Testing del Sistema Universitario
 * 
 * Esta clase ejecuta todos los casos de prueba:
 * - CRUD completo de todas las entidades
 * - Relaciones OneToOne, OneToMany, ManyToOne
 * - Queries JPQL simples y complejas
 * - Cascadas y orphanRemoval
 * - Validaciones
 */
public class App {

    private static Scanner scanner = new Scanner(System.in);
    
    // Servicios
    private static ProfesorService profesorService;
    private static CursoService cursoService;
    private static EstudianteService estudianteService;
    
    // IDs para testing
    private static Long profesorId;
	private static Long cursoId;
    private static Long estudianteId;

    public static void main(String[] args) {
        
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA UNIVERSITARIO - TESTING HIBERNATE/JPA    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");

        inicializarServicios();

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerOpcion();

            switch (opcion) {
                case 1 -> testCompleto();
                case 2 -> testProfesorOficina();
                case 3 -> testCursoEstudiantes();
                case 4 -> testQueriesAvanzadas();
                case 5 -> testCascadasOrphanRemoval();
                case 6 -> testValidaciones();
                case 7 -> limpiarBaseDatos();
                case 0 -> System.out.println("\n✅ Saliendo del sistema...");
                default -> System.out.println("❌ Opción inválida");
            }

        } while (opcion != 0);

        cerrarRecursos();
    }

    private static void inicializarServicios() {
        System.out.println("🔧 Inicializando servicios...");
        
        ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();
        CursoDAOImpl cursoDAO = new CursoDAOImpl();
        EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl();
        
        profesorService = new ProfesorService(profesorDAO);
        cursoService = new CursoService(cursoDAO);
        estudianteService = new EstudianteService(estudianteDAO);
        
        System.out.println("✅ Servicios inicializados correctamente\n");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           MENÚ PRINCIPAL              ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 1. Test Completo (Todos los casos)   ║");
        System.out.println("║ 2. Test Profesor-Oficina (OneToOne)  ║");
        System.out.println("║ 3. Test Curso-Estudiantes (ManyToMany)║");
        System.out.println("║ 4. Test Queries Avanzadas (JPQL)     ║");
        System.out.println("║ 5. Test Cascadas y OrphanRemoval     ║");
        System.out.println("║ 6. Test Validaciones                 ║");
        System.out.println("║ 7. Limpiar Base de Datos             ║");
        System.out.println("║ 0. Salir                             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("➤ Seleccione una opción: ");
    }

    // ═══════════════════════════════════════════════════════════
    // TEST 1: COMPLETO - Todos los casos
    // ═══════════════════════════════════════════════════════════
    private static void testCompleto() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║          TEST COMPLETO INICIADO               ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        try {
            // Paso 1: Crear Profesor con Oficina (OneToOne)
            System.out.println("📝 PASO 1: Creando Profesor con Oficina...");
            Profesor profesor = crearProfesorConOficina();
            profesorId = profesor.getId();
            System.out.println("✅ Profesor creado: " + profesor.getNombreCompleto() + " [ID: " + profesorId + "]");
            System.out.println("   Oficina: " + profesor.getOficina().getUbicacion());

            pausa();

            // Paso 2: Crear Cursos (OneToMany)
            System.out.println("\n📝 PASO 2: Creando Cursos para el Profesor...");
            List<Curso> cursos = crearCursosParaProfesor(profesor);
            setCursoId(cursos.get(0).getId());
            System.out.println("✅ Se crearon " + cursos.size() + " cursos:");
            cursos.forEach(c -> System.out.println("   - " + c.getCodigo() + ": " + c.getNombre()));

            pausa();

            // Paso 3: Crear Estudiantes
            System.out.println("\n📝 PASO 3: Creando Estudiantes...");
            List<Estudiante> estudiantes = crearEstudiantes();
            setEstudianteId(estudiantes.get(0).getId());
            System.out.println("✅ Se crearon " + estudiantes.size() + " estudiantes:");
            estudiantes.forEach(e -> System.out.println("   - " + e.getMatricula() + ": " + e.getNombreCompleto()));

            pausa();

            // Paso 4: Inscribir Estudiantes en Cursos
            System.out.println("\n📝 PASO 4: Inscribiendo Estudiantes en Cursos...");
            inscribirEstudiantes(cursos, estudiantes);
            System.out.println("✅ Estudiantes inscritos exitosamente");

            pausa();

            // Paso 5: Consultar información
            System.out.println("\n📝 PASO 5: Consultando Información...");
            mostrarInformacionCompleta();

            pausa();

            // Paso 6: Queries JPQL
            System.out.println("\n📝 PASO 6: Ejecutando Queries JPQL...");
            ejecutarQueriesJPQL();

            pausa();

            // Paso 7: Actualizar datos
            System.out.println("\n📝 PASO 7: Actualizando Datos...");
            actualizarDatos(profesor, cursos.get(0), estudiantes.get(0));

            pausa();

            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║       ✅ TEST COMPLETO FINALIZADO            ║");
            System.out.println("╚════════════════════════════════════════════════╝");

        } catch (Exception e) {
            System.err.println("❌ ERROR en Test Completo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ═══════════════════════════════════════════════════════════
    // TEST 2: OneToOne (Profesor-Oficina)
    // ═══════════════════════════════════════════════════════════
    private static void testProfesorOficina() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║     TEST RELACIÓN ONE-TO-ONE INICIADO         ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        try {
            // Crear profesor sin oficina
            System.out.println("📝 Creando Profesor sin Oficina...");
            Profesor profesor = new Profesor("Carlos", "Rodríguez", "carlos.r@universidad.com", "Física");
            profesorService.registrarProfesor(profesor);
            System.out.println("✅ Profesor creado: " + profesor.getNombreCompleto());
            System.out.println("   Tiene oficina: " + (profesor.getOficina() != null ? "SÍ" : "NO"));

            pausa();

            // Asignar oficina
            System.out.println("\n📝 Asignando Oficina al Profesor...");
            Oficina oficina = new Oficina("305", "Edificio Ciencias", 3, 2);
            profesor.setOficina(oficina);
            profesorService.actualizarProfesor(profesor);
            
            // Verificar
            Profesor profesorConOficina = profesorService.obtenerProfesorConRelaciones(profesor.getId());
            System.out.println("✅ Oficina asignada: " + profesorConOficina.getOficina().getUbicacion());

            pausa();

            // Cambiar oficina
            System.out.println("\n📝 Cambiando Oficina del Profesor...");
            Oficina nuevaOficina = new Oficina("401", "Edificio Principal", 4, 3);
            profesorConOficina.setOficina(nuevaOficina);
            profesorService.actualizarProfesor(profesorConOficina);
            
            Profesor profesorActualizado = profesorService.obtenerProfesorConRelaciones(profesor.getId());
            System.out.println("✅ Nueva oficina: " + profesorActualizado.getOficina().getUbicacion());

            pausa();

            // Eliminar oficina (orphanRemoval)
            System.out.println("\n📝 Eliminando Oficina (orphanRemoval)...");
            profesorActualizado.setOficina(null);
            profesorService.actualizarProfesor(profesorActualizado);
            
            Profesor profesorSinOficina = profesorService.obtenerProfesorConRelaciones(profesor.getId());
            System.out.println("✅ Oficina eliminada: " + (profesorSinOficina.getOficina() == null ? "SÍ" : "NO"));

            System.out.println("\n✅ Test OneToOne completado exitosamente");

        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════
    // TEST 3: ManyToMany (Curso-Estudiantes vía Inscripcion)
    // ═══════════════════════════════════════════════════════════
    private static void testCursoEstudiantes() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║    TEST RELACIÓN MANY-TO-MANY INICIADO        ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        try {
            // Crear profesor y curso
            System.out.println("📝 Creando Curso...");
            Profesor profesor = crearProfesorConOficina();
            Curso curso = new Curso("MAT301", "Cálculo Diferencial", 4, 30);
            curso.setProfesor(profesor);
            curso.setFechaInicio(LocalDate.now());
            curso.setFechaFin(LocalDate.now().plusMonths(4));
            cursoService.registrarCurso(curso);
            System.out.println("✅ Curso creado: " + curso.getNombre());
            System.out.println("   Cupo: " + curso.getCupoMaximo() + " estudiantes");

            pausa();

            // Crear estudiantes
            System.out.println("\n📝 Creando Estudiantes...");
            List<Estudiante> estudiantes = crearEstudiantes();
            System.out.println("✅ " + estudiantes.size() + " estudiantes creados");

            pausa();

            // Inscribir estudiantes
            System.out.println("\n📝 Inscribiendo Estudiantes en el Curso...");
            int inscritos = 0;
            for (Estudiante est : estudiantes) {
                Inscripcion inscripcion = new Inscripcion(est, curso);
                est.addInscripcion(inscripcion);
                estudianteService.actualizarEstudiante(est);
                inscritos++;
                System.out.println("   ✓ " + est.getNombreCompleto() + " inscrito");
            }
            System.out.println("✅ Total inscritos: " + inscritos);

            pausa();

            // Consultar curso con estudiantes
            System.out.println("\n📝 Consultando Estudiantes del Curso...");
            Curso cursoConEstudiantes = cursoService.obtenerCursoConInscripciones(curso.getId());
            System.out.println("📊 Curso: " + cursoConEstudiantes.getNombre());
            System.out.println("   Inscritos: " + cursoConEstudiantes.getInscripciones().size());
            System.out.println("   Cupo disponible: " + cursoConEstudiantes.getCupoDisponible());

            pausa();

            // Poner notas
            System.out.println("\n📝 Asignando Notas a Estudiantes...");
            double[] notas = {8.5, 9.0, 7.5, 8.0, 9.5};
            int idx = 0;
            for (Inscripcion insc : cursoConEstudiantes.getInscripciones()) {
                insc.setNotaFinal(notas[idx % notas.length]);
                insc.setEstado(notas[idx % notas.length] >= 7.0 ? 
                    EstadoInscripcion.APROBADA : EstadoInscripcion.REPROBADA);
                idx++;
                System.out.println("   ✓ " + insc.getEstudiante().getNombreCompleto() + 
                                 ": " + insc.getNotaFinal());
            }
            cursoService.actualizarCurso(cursoConEstudiantes);
            System.out.println("✅ Notas asignadas");

            pausa();

            // Retirar un estudiante
            System.out.println("\n📝 Retirando un Estudiante del Curso...");
            Estudiante estudianteARetirar = estudiantes.get(0);
            Inscripcion inscripcionARetirar = estudianteARetirar.getInscripciones().get(0);
            inscripcionARetirar.setEstado(EstadoInscripcion.RETIRADA);
            estudianteService.actualizarEstudiante(estudianteARetirar);
            System.out.println("✅ Estudiante retirado: " + estudianteARetirar.getNombreCompleto());

            System.out.println("\n✅ Test ManyToMany completado exitosamente");

        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════
    // TEST 4: Queries JPQL Avanzadas
    // ═══════════════════════════════════════════════════════════
    private static void testQueriesAvanzadas() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║       TEST QUERIES JPQL AVANZADAS             ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        try {
            ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();
            CursoDAOImpl cursoDAO = new CursoDAOImpl();
            EstudianteDAOImpl estudianteDAO = new EstudianteDAOImpl();

            // Query 1: Buscar por nombre
            System.out.println("📊 Query 1: Buscar profesores por nombre");
            List<Profesor> profesoresPorNombre = profesorDAO.buscarPorNombre("García");
            System.out.println("   Encontrados: " + profesoresPorNombre.size());
            profesoresPorNombre.forEach(p -> System.out.println("   - " + p.getNombreCompleto()));

            pausa();

            // Query 2: Profesores con oficina
            System.out.println("\n📊 Query 2: Profesores con oficina asignada");
            List<Profesor> profesoresConOficina = profesorDAO.obtenerProfesoresConOficina();
            System.out.println("   Total: " + profesoresConOficina.size());
            profesoresConOficina.forEach(p -> 
                System.out.println("   - " + p.getNombreCompleto() + 
                                 " → " + p.getOficina().getNumero()));

            pausa();

            // Query 3: Contar cursos por profesor
            System.out.println("\n📊 Query 3: Contar cursos por profesor");
            if (profesorId != null) {
                Long totalCursos = profesorDAO.contarCursosPorProfesor(profesorId);
                System.out.println("   Total de cursos: " + totalCursos);
            }

            pausa();

            // Query 4: Cursos con cupo disponible
            System.out.println("\n📊 Query 4: Cursos con cupo disponible");
            List<Curso> cursosDisponibles = cursoDAO.obtenerCursosConCupoDisponible();
            System.out.println("   Cursos disponibles: " + cursosDisponibles.size());
            cursosDisponibles.forEach(c -> 
                System.out.println("   - " + c.getCodigo() + ": " + c.getNombre() + 
                                 " (Disponibles: " + c.getCupoDisponible() + ")"));

            pausa();

            // Query 5: Estudiantes por carrera
            System.out.println("\n📊 Query 5: Estudiantes por carrera");
            List<Estudiante> estudiantesPorCarrera = estudianteDAO.buscarPorCarrera("Ingeniería");
            System.out.println("   Estudiantes de Ingeniería: " + estudiantesPorCarrera.size());

            System.out.println("\n✅ Test Queries JPQL completado");

        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════
    // TEST 5: Cascadas y OrphanRemoval
    // ═══════════════════════════════════════════════════════════
    private static void testCascadasOrphanRemoval() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║    TEST CASCADAS Y ORPHAN REMOVAL             ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        try {
            // Test Cascade ALL
            System.out.println("📝 Test 1: Cascade ALL (Profesor → Oficina)");
            Profesor profesor = crearProfesorConOficina();
            Long idProfesor = profesor.getId();
            System.out.println("✅ Profesor creado con oficina");

            pausa();

            System.out.println("\n📝 Eliminando Profesor (debe eliminar Oficina por cascade)...");
            profesorService.eliminarProfesor(idProfesor);
            System.out.println("✅ Profesor eliminado (y su oficina también)");

            pausa();

            // Test orphanRemoval
            System.out.println("\n📝 Test 2: orphanRemoval (Profesor → Cursos)");
            Profesor profesor2 = crearProfesorConOficina();
            List<Curso> cursos = crearCursosParaProfesor(profesor2);
            System.out.println("✅ Profesor con " + cursos.size() + " cursos");

            pausa();

            System.out.println("\n📝 Eliminando primer curso de la lista (orphanRemoval)...");
            Profesor prof = profesorService.obtenerProfesorConRelaciones(profesor2.getId());
            prof.removeCurso(prof.getCursos().get(0));
            profesorService.actualizarProfesor(prof);
            
            Profesor profActualizado = profesorService.obtenerProfesorConRelaciones(profesor2.getId());
            System.out.println("✅ Cursos restantes: " + profActualizado.getCursos().size());

            System.out.println("\n✅ Test Cascadas completado");

        } catch (Exception e) {
            System.err.println("❌ ERROR: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════
    // TEST 6: Validaciones
    // ═══════════════════════════════════════════════════════════
    private static void testValidaciones() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║           TEST VALIDACIONES                   ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");

        // Test 1: Nombre vacío
        System.out.println("📝 Test 1: Validar nombre vacío");
        try {
            Profesor profesor = new Profesor("", "Apellido", "test@test.com", "Matemáticas");
            profesorService.registrarProfesor(profesor);
            System.out.println("❌ ERROR: Debería haber lanzado excepción");
        } catch (Exception e) {
            System.out.println("✅ Validación correcta: " + e.getMessage());
        }

        pausa();

        // Test 2: Email duplicado
        System.out.println("\n📝 Test 2: Validar email duplicado");
        try {
            Profesor p1 = new Profesor("Juan", "Pérez", "juan@test.com", "Física");
            profesorService.registrarProfesor(p1);
            
            Profesor p2 = new Profesor("Pedro", "González", "juan@test.com", "Química");
            profesorService.registrarProfesor(p2);
            System.out.println("❌ ERROR: Debería haber lanzado excepción");
        } catch (Exception e) {
            System.out.println("✅ Validación correcta: Email duplicado detectado");
        }

        pausa();

        // Test 3: Cupo excedido
        System.out.println("\n📝 Test 3: Validar cupo de curso excedido");
        try {
            Profesor prof = crearProfesorConOficina();
            Curso curso = new Curso("TEST01", "Curso Test", 3, 2); // Cupo: 2
            curso.setProfesor(prof);
            cursoService.registrarCurso(curso);

            // Inscribir 3 estudiantes (excede cupo)
            for (int i = 0; i < 3; i++) {
                Estudiante est = new Estudiante("E00" + i, "Nombre" + i, "Apellido" + i, 
                                               "est" + i + "@test.com", "Test");
                estudianteService.registrarEstudiante(est);
                
                if (!curso.tieneCupoDisponible()) {
                    throw new RuntimeException("Cupo lleno");
                }
                
                Inscripcion insc = new Inscripcion(est, curso);
                est.addInscripcion(insc);
                estudianteService.actualizarEstudiante(est);
            }
            
        } catch (Exception e) {
            System.out.println("✅ Validación correcta: " + e.getMessage());
        }

        System.out.println("\n✅ Test Validaciones completado");
    }

    // ═══════════════════════════════════════════════════════════
    // MÉTODOS AUXILIARES
    // ═══════════════════════════════════════════════════════════

    private static Profesor crearProfesorConOficina() {
        Profesor profesor = new Profesor("Juan", "García", "juan.garcia@universidad.com", "Matemáticas");
        Oficina oficina = new Oficina("201", "Edificio A", 2, 4);
        profesor.setOficina(oficina);
        profesorService.registrarProfesor(profesor);
        return profesor;
    }

    private static List<Curso> crearCursosParaProfesor(Profesor profesor) {
        Curso curso1 = new Curso("MAT101", "Álgebra Lineal", 4, 40);
        curso1.setProfesor(profesor);
        curso1.setFechaInicio(LocalDate.now());
        curso1.setFechaFin(LocalDate.now().plusMonths(4));

        Curso curso2 = new Curso("MAT201", "Cálculo Integral", 5, 35);
        curso2.setProfesor(profesor);
        curso2.setFechaInicio(LocalDate.now().plusWeeks(2));
        curso2.setFechaFin(LocalDate.now().plusMonths(5));

        Curso curso3 = new Curso("MAT301", "Ecuaciones Diferenciales", 4, 30);
        curso3.setProfesor(profesor);
        curso3.setFechaInicio(LocalDate.now().plusMonths(1));
        curso3.setFechaFin(LocalDate.now().plusMonths(5));

        profesor.addCurso(curso1);
        profesor.addCurso(curso2);
        profesor.addCurso(curso3);
        
        profesorService.actualizarProfesor(profesor);
        
        return List.of(curso1, curso2, curso3);
    }

    private static List<Estudiante> crearEstudiantes() {
        Estudiante e1 = new Estudiante("2024001", "María", "López", "maria.lopez@estudiantes.com", "Ingeniería");
        Estudiante e2 = new Estudiante("2024002", "Pedro", "Martínez", "pedro.martinez@estudiantes.com", "Ingeniería");
        Estudiante e3 = new Estudiante("2024003", "Ana", "Rodríguez", "ana.rodriguez@estudiantes.com", "Ciencias");
        Estudiante e4 = new Estudiante("2024004", "Luis", "Fernández", "luis.fernandez@estudiantes.com", "Matemáticas");
        Estudiante e5 = new Estudiante("2024005", "Carmen", "Sánchez", "carmen.sanchez@estudiantes.com", "Física");

        estudianteService.registrarEstudiante(e1);
        estudianteService.registrarEstudiante(e2);
        estudianteService.registrarEstudiante(e3);
        estudianteService.registrarEstudiante(e4);
        estudianteService.registrarEstudiante(e5);

        return List.of(e1, e2, e3, e4, e5);
    }

    private static void inscribirEstudiantes(List<Curso> cursos, List<Estudiante> estudiantes) {
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante est = estudiantes.get(i);
            Curso curso = cursos.get(i % cursos.size());
            
            Inscripcion inscripcion = new Inscripcion(est, curso);
            est.addInscripcion(inscripcion);
            estudianteService.actualizarEstudiante(est);
        }
    }

    private static void mostrarInformacionCompleta() {
        System.out.println("\n📊 === INFORMACIÓN COMPLETA DEL SISTEMA ===\n");

        // Profesores
        ProfesorDAOImpl profDAO = new ProfesorDAOImpl();
        List<Profesor> profesores = profDAO.obtenerTodos();
        System.out.println("👨‍🏫 Total Profesores: " + profesores.size());
        profesores.forEach(p -> {
            System.out.println("   - " + p.getNombreCompleto());
            System.out.println("     Email: " + p.getEmail());
            System.out.println("     Oficina: " + (p.getOficina() != null ? p.getOficina().getNumero() : "Sin asignar"));
        });

        // Cursos
        CursoDAOImpl cursoDAO = new CursoDAOImpl();
        List<Curso> cursos = cursoDAO.obtenerTodos();
        System.out.println("\n📚 Total Cursos: " + cursos.size());
        cursos.forEach(c -> {
            System.out.println("   - " + c.getCodigo() + ": " + c.getNombre());
            System.out.println("     Profesor: " + c.getProfesor().getNombreCompleto());
            System.out.println("     Cupo: " + c.getInscripciones().size() + "/" + c.getCupoMaximo());
        });

        // Estudiantes
        EstudianteDAOImpl estDAO = new EstudianteDAOImpl();
        List<Estudiante> estudiantes = estDAO.obtenerTodos();
        System.out.println("\n🎓 Total Estudiantes: " + estudiantes.size());
        estudiantes.forEach(e -> {
            System.out.println("   - " + e.getMatricula() + ": " + e.getNombreCompleto());
            System.out.println("     Inscripciones: " + e.getInscripciones().size());
        });
    }

    private static void ejecutarQueriesJPQL() {
        ProfesorDAOImpl profDAO = new ProfesorDAOImpl();
        
        System.out.println("🔍 Query 1: Profesores con más de 2 cursos");
        List<Profesor> profesoresActivos = profDAO.obtenerProfesoresConMasDe(2);
        System.out.println("   Resultado: " + profesoresActivos.size() + " profesores");

        System.out.println("\n🔍 Query 2: Profesores sin oficina");
        List<Profesor> profsSinOficina = profDAO.obtenerProfesoresSinOficina();
        System.out.println("   Resultado: " + profsSinOficina.size() + " profesores");
    }

    private static void actualizarDatos(Profesor profesor, Curso curso, Estudiante estudiante) {
        // Actualizar profesor
        System.out.println("📝 Actualizando especialidad del profesor...");
        profesor.setEspecialidad("Matemáticas Avanzadas");
        profesorService.actualizarProfesor(profesor);
        System.out.println("✅ Especialidad actualizada");

        // Actualizar curso
        System.out.println("\n📝 Actualizando cupo del curso...");
        curso.setCupoMaximo(50);
        cursoService.actualizarCurso(curso);
        System.out.println("✅ Cupo actualizado a 50");

        // Actualizar estudiante
        System.out.println("\n📝 Actualizando carrera del estudiante...");
        estudiante.setCarrera("Ingeniería de Software");
        estudianteService.actualizarEstudiante(estudiante);
        System.out.println("✅ Carrera actualizada");
    }

    private static void limpiarBaseDatos() {
        System.out.println("\n⚠️  ¿Está seguro de limpiar toda la base de datos? (S/N): ");
        String respuesta = scanner.nextLine();
        
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("\n🗑️  Limpiando base de datos...");
            
            try {
                // Eliminar en orden inverso por dependencias
                EstudianteDAOImpl estDAO = new EstudianteDAOImpl();
                List<Estudiante> estudiantes = estDAO.obtenerTodos();
                estudiantes.forEach(e -> estudianteService.eliminarEstudiante(e.getId()));
                System.out.println("✅ Estudiantes eliminados: " + estudiantes.size());

                CursoDAOImpl cursoDAO = new CursoDAOImpl();
                List<Curso> cursos = cursoDAO.obtenerTodos();
                cursos.forEach(c -> cursoService.eliminarCurso(c.getId()));
                System.out.println("✅ Cursos eliminados: " + cursos.size());

                ProfesorDAOImpl profDAO = new ProfesorDAOImpl();
                List<Profesor> profesores = profDAO.obtenerTodos();
                profesores.forEach(p -> profesorService.eliminarProfesor(p.getId()));
                System.out.println("✅ Profesores eliminados: " + profesores.size());

                System.out.println("\n✅ Base de datos limpiada correctamente");
                
            } catch (Exception e) {
                System.err.println("❌ Error al limpiar: " + e.getMessage());
            }
        } else {
            System.out.println("❌ Operación cancelada");
        }
    }

    private static void pausa() {
        System.out.print("\n[Presione ENTER para continuar...]");
        scanner.nextLine();
    }

    private static int leerOpcion() {
        try {
            String linea = scanner.nextLine();
            return Integer.parseInt(linea);
        } catch (Exception e) {
            return -1;
        }
    }

    private static void cerrarRecursos() {
        System.out.println("\n🔒 Cerrando recursos...");
        HibernateUtil.shutdown();
        scanner.close();
        System.out.println("✅ Recursos liberados correctamente");
        System.out.println("\n👋 ¡Hasta pronto!");
    }

	public static Long getEstudianteId() {
		return estudianteId;
	}

	public static void setEstudianteId(Long estudianteId) {
		App.estudianteId = estudianteId;
	}

	public static Long getCursoId() {
		return cursoId;
	}

	public static void setCursoId(Long cursoId) {
		App.cursoId = cursoId;
	}
}