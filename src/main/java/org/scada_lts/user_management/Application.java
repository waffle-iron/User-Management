package org.scada_lts.user_management;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scada_lts.user_management.dao.definition.UserDao;
import org.scada_lts.user_management.model.definition.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootApplication
public class Application {

    private static final Log LOG = LogFactory.getLog(Application.class);

    @Resource
    private UserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            LOG.info("Start User Management");


            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

            LOG.info("Initializing users");

            userDao.create(new User("admin",""));

            LOG.info("started");

        };
    }

}
