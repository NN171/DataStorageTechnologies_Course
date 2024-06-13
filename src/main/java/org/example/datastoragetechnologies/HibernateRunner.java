package org.example.datastoragetechnologies;

import org.example.datastoragetechnologies.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.PostgreSQLDialect;

public class HibernateRunner {

    public static SessionFactory session() {
        return new Configuration()
                .addAnnotatedClass(Booking.class)
                .addAnnotatedClass(Booking_execution.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Product.class)
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5433/Dst")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "postgres")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
                .buildSessionFactory();
    }
}
