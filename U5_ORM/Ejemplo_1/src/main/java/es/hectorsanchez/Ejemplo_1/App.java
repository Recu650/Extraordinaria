package es.hectorsanchez.Ejemplo_1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
	public static void main(String[] args) {
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrimerProyecto");

		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();

		// Iniciamos una transacción
		em.getTransaction().begin();

		// Construimos un objeto Alumno
		Alumno alumno1 = new Alumno();
		//alumno1.setId(1); No hace falta porque es autoincrementable
		alumno1.setNombre("Recu");
		alumno1.setNota(5);
		Direccion direccion1 = new Direccion();
		direccion1.setCalle("Oreña");
		direccion1.setNumero(242);
		alumno1.setDireccion(direccion1);
		Direccion direccionFacturacion1 = new Direccion();
		direccionFacturacion1.setCalle("Reocin");
		direccionFacturacion1.setNumero(77);
		alumno1.setDireccionFacturacion(direccionFacturacion1);

		// Construimos otro objeto Alumno
		Alumno alumno2 = new Alumno();
		//alumno2.setId(2); No hace falta porque es autoincrementable
		alumno2.setNombre("María");
		alumno2.setNota(9);
		Direccion direccion2 = new Direccion();
		direccion1.setCalle("Villapresente");
		direccion1.setNumero(1);
		alumno1.setDireccion(direccion2);
		Direccion direccionFacturacion2 = new Direccion();
		direccionFacturacion1.setCalle("Reocin");
		direccionFacturacion1.setNumero(77);
		alumno1.setDireccionFacturacion(direccionFacturacion2);


		// Persistimos los objetos
		em.persist(alumno1);
		em.persist(alumno2);

		// Commiteamos la transacción
		em.getTransaction().commit();

		// Cerramos el EntityManager
		em.close();
	}
}
