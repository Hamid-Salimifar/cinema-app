package ir.maktabsharif.listener;

import ir.maktabsharif.model.UserRole;
import ir.maktabsharif.model.Users;
import ir.maktabsharif.repository.BaseRepository;
import ir.maktabsharif.repository.impl.BaseRepositoryImpl;
import ir.maktabsharif.repository.impl.UserRepositoryImpl;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.service.impl.UserServiceImpl;
import ir.maktabsharif.util.JPAConnection;
import ir.maktabsharif.util.PasswordUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AdminInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BaseRepository userRepository = new UserRepositoryImpl();
        EntityManager em = JPAConnection.getJPAInstance().getEntityManager();
        em.getTransaction().begin();
        TypedQuery<Users> query = em.createQuery("SELECT e from Users e where e.role= : role", Users.class);
        query.setParameter("role", UserRole.ADMIN);
        Users admin = query.getResultList().stream().findFirst().orElse(null);
        em.getTransaction().commit();
        em.close();
        if(admin==null){
            Users user=Users.builder()
                    .role(UserRole.ADMIN)
                    .userName("admin")
                    .password(PasswordUtil.hashPassword("admin"))
                    .build();
            userRepository.save(user);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
