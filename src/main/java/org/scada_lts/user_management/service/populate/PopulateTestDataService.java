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
package org.scada_lts.user_management.service.populate;

import org.scada_lts.user_management.dao.acl.EntityClassDao;
import org.scada_lts.user_management.dao.acl.EntityIdentityDao;
import org.scada_lts.user_management.dao.acl.EntryDao;
import org.scada_lts.user_management.dao.acl.SidDao;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.model.acl.Sid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Class to  populate data for test ScadaLTS
 *
 * @author grzegorz bylica grzegorz.bylica@gmail.com
 */
@Service
public class PopulateTestDataService {


    @Resource
    private SidDao sidDao;

    @Resource
    private EntityClassDao entityClassDao;

    @Resource
    private EntityIdentityDao entityIdentityDao;

    @Resource
    private EntryDao entryDao;


    public void populateTestData() {

        //sid
        sidDao.create(new Sid(1L,"admin"));
        sidDao.create(new Sid(2L,"testUser"));

        entityClassDao.create(new EntityClass("org.scada_lts.domain.DataSource"));

        entityIdentityDao.create(
                new EntityIdentity(
                        "data source id 1",
                        entityClassDao.get(1L),
                        null,
                        sidDao.get(1L)
                )
        );
        entityIdentityDao.create(
                new EntityIdentity(
                        "data source id 2",
                        entityClassDao.get(1L),
                        null,
                        sidDao.get(1L)
                )
        );

        // entry

        //1
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(1L),
                        1L,
                        sidDao.get(1L),
                        (byte) 1
                )
        );

        //2
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(2L),
                        1L,
                        sidDao.get(1L),
                        (byte) 1
                )
        );

        //3
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(1L),
                        1L,
                        sidDao.get(2L),
                        (byte) 1
                )
        );

        //4
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(1L),
                        2L,
                        sidDao.get(1L),
                        (byte) 2
                )
        );
    }
}
