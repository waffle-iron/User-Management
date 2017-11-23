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
package org.scada_lts.user_management.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.scada_lts.user_management.dao.definition.UserDao;
import org.scada_lts.user_management.model.definition.User;
import org.scada_lts.user_management.service.definition.UsersService;
import org.scada_lts.user_management.service.definition.UsersServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UsersService usersService = new UsersServiceImpl();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = new ArrayList<>();

        when(userDao.getAll()).thenReturn(users);
        assertTrue(usersService.getAll().size()==0);

        User user1 = new User();
        user1.setId(1L);
        user1.setName("testUser1");
        users.add(user1);
        User user2 = new User();
        user2.setId(2L);
        user2.setName("testUser2");
        users.add(user2);

        assertTrue(usersService.getAll().size()==2);
        assertEquals(usersService.getAll().get(1).getName(), "testUser2");
    }

    @Test
    public void testGetUser() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("testUser");
        when(userDao.get(1L)).thenReturn(user);

        assertEquals(usersService.getUser(1L).getName(), "testUser");
    }

    //@Test TODO
    public void testCreateUser() throws Exception {

    }

    //@Test TODO
    public void testUpdateUser() throws Exception {

    }

    //@Test TODO
    public void testDeleteUser() throws Exception {


    }

}
