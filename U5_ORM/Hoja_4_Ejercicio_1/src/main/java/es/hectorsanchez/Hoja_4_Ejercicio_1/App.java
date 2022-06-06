package es.hectorsanchez.Hoja_4_Ejercicio_1;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
	public static void main(String[] args) {
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Hoja1");

		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();

		// Iniciamos una transacción
		em.getTransaction().begin();

		// Construimops dos deprtamentos
		Departamento dep1 = new Departamento();
		dep1.setNombre("RecuEntreprise");
		dep1.setLocalidad("Oreña");

		Departamento dep2 = new Departamento();
		dep2.setNombre("IngenierosPorElMundo");
		dep2.setLocalidad("Santander");
		
		// Construimos dos estudios
		Estudio est1 = new Estudio();
		est1.setNombre("Atletismo");
		Estudio est2 = new Estudio();
		est2.setNombre("Enfermeria");
		Estudio est3 = new Estudio();
		est3.setNombre("Ingenieria");

		// Construimos Empleado
		Empleado empleado1 = new Empleado();
		empleado1.setNombre("Recu");
		empleado1.setOficio("Atleta");
		Sueldo sueldo1 = new Sueldo();
		sueldo1.setSalario(30000);
		sueldo1.setComision(2000);
		empleado1.setSueldo(sueldo1);
		empleado1.setFechaAlta(LocalDate.now());
		empleado1.setDepartamento(dep1);
		empleado1.addEstudio(est1);

		// Construimos Empleado
		Empleado empleado2 = new Empleado();
		empleado2.setNombre("Leras");
		empleado2.setOficio("Ingeniero");
		Sueldo sueldo2 = new Sueldo();
		sueldo2.setSalario(3000);
		sueldo2.setComision(200);
		empleado2.setSueldo(sueldo2);
		empleado2.setFechaAlta(LocalDate.now());
		empleado2.setDepartamento(dep2);
		empleado2.addEstudio(est3);

		// Construimos Empleado
		Empleado empleado3 = new Empleado();
		empleado3.setNombre("Maria");
		empleado3.setOficio("Enfermera");
		Sueldo sueldo3 = new Sueldo();
		sueldo3.setSalario(5000);
		sueldo3.setComision(300);
		empleado3.setSueldo(sueldo3);
		empleado3.setFechaAlta(LocalDate.now());
		empleado3.setDepartamento(dep1);
		empleado3.addEstudio(est2);

		// Construimos Empleado
		Empleado empleado4 = new Empleado();
		empleado4.setNombre("Alex");
		empleado4.setOficio("Ingeniero");
		Sueldo sueldo4 = new Sueldo();
		sueldo4.setSalario(3000);
		sueldo4.setComision(100);
		empleado4.setSueldo(sueldo4);
		empleado4.setFechaAlta(LocalDate.now());
		empleado4.setDepartamento(dep2);
		empleado4.addEstudio(est3);

		// Persistimos los objetos
		em.persist(empleado1);
		em.persist(empleado2);
		em.persist(empleado3);
		em.persist(empleado4);

		// Comiteamos la transaccion
		em.getTransaction().commit();

		// Cerramos el EntityManager
		em.close();
	}
}
