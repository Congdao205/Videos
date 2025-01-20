package Utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	private static EntityManagerFactory factory;

	public static EntityManager EntityManager() {
		if (factory == null || factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("PS40841_ASM");
		}
		return factory.createEntityManager();
	}

	public static void shutDown() {
		if (factory != null && factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}

}
