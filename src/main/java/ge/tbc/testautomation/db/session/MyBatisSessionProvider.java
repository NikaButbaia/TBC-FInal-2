package ge.tbc.testautomation.db.session;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisSessionProvider {

    private static final SqlSessionFactory SESSION_FACTORY;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            SESSION_FACTORY = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize MyBatis SessionFactory", e);
        }
    }

    public static SqlSessionFactory get() {
        return SESSION_FACTORY;
    }
}