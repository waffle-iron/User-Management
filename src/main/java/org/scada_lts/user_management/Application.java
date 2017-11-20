/*
 * (c) 2017 Abil'I.T. http://abilit.eu/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.scada_lts.user_management;

import org.apache.commons.cli.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.scada_lts.user_management.dao.definition.UserDao;
import org.scada_lts.user_management.model.definition.User;
import org.scada_lts.user_management.service.populate.PopulateTestDataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author Grzegorz Bylica grzegorz.bylica@gmail.com
 * @author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 */
@SpringBootApplication
public class Application {

    private static final Log LOG = LogFactory.getLog(Application.class);

    @Resource
    private UserDao userDao;

    @Resource
    private PopulateTestDataService populateTestDataService;

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

            Options options = new Options();
            options.addOption("p", false, "populate test data");

            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = null;
            try {
                LOG.info("Parse command line arguments");
                cmd = parser.parse(options, args);
            } catch (ParseException e) {
                LOG.error(e);
            }

            if (cmd != null && cmd.hasOption("p")) {
                LOG.info("Populate test data");
                populateTestDataService.populateTestData();
            }

            LOG.info("Initializing users");

            userDao.create(new User("admin",""));

            LOG.info("started");

        };
    }

}
