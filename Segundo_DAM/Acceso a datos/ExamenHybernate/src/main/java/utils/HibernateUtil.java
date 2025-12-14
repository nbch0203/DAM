package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                System.out.println("🔧 Inicializando Hibernate...");
                
                // Create registry
                registry = new StandardServiceRegistryBuilder().configure().build();
                System.out.println("✅ Registry creado");

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);
                System.out.println("✅ MetadataSources creado");

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();
                System.out.println("✅ Metadata creado");

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                System.out.println("✅ SessionFactory creado exitosamente");

            } catch (Exception e) {
                System.err.println("❌ ERROR al crear SessionFactory:");
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}