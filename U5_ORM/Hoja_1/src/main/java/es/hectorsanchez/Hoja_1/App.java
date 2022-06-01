package es.hectorsanchez.Hoja_1;

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
		empleado1.setSalario(3000);
		empleado1.setFechaAlta(LocalDate.now());

		// Construimos Empleado
		Empleado empleado2 = new Empleado();
		empleado2.setId((long) 2);
		empleado2.setNombre("Leras");
		empleado2.setOficio("Ingeniero");
		empleado2.setSalario(4000);
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
