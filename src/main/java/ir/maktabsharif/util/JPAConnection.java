package ir.maktabsharif.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAConnection {
    private static volatile JPAConnection instance;
    private EntityManagerFactory emf;
    private static final Object lock = new Object();

    private JPAConnection() {
        emf = Persistence.createEntityManagerFactory("postgres-pu");
    }

    public EntityManager getEntityManager() {

        return emf.createEntityManager();
    }

    public static JPAConnection getJPAInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new JPAConnection();
                }
            }
        }
        return instance;
    }

    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
