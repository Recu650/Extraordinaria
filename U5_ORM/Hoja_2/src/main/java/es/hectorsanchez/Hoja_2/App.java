package es.hectorsanchez.Hoja_2;

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

		// Construimos Empleado
		Empleado empleado1 = new Empleado();
		empleado1.setId((long) 1);
		empleado1.setNombre("Recu");
		empleado1.setOficio("Atleta");
		Sueldo sueldo1 = new Sueldo();
		sueldo1.setSalario(30000);
		sueldo1.setComision(2000);
		empleado1.setSueldo(sueldo1);
		empleado1.setFechaAlta(LocalDate.now());

		// Construimos Empleado
		Empleado empleado2 = new Empleado();
		empleado2.setId((long) 2);
		empleado2.setNombre("Leras");
		empleado2.setOficio("Ingeniero");
		Sueldo sueldo2 = new Sueldo();
		sueldo2.setSalario(3000);
		sueldo2.setComision(200);
		empleado1.setSueldo(sueldo2);
		empleado2.setFechaAlta(LocalDate.now());
		
		//Persistimos los objetos
		em.persist(empleado1);
		em.persist(empleado2);
		
		//Comiteamos la transaccion
		em.getTransaction().commit();
		
		//Cerramos el EntityManager
		em.close();
	}
}
