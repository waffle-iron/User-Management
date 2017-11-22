package org.scada_lts.user_management.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.scada_lts.user_management.dao.acl.EntityClassDao;
import org.scada_lts.user_management.dao.acl.EntityIdentityDao;
import org.scada_lts.user_management.dao.acl.EntryDao;
import org.scada_lts.user_management.dao.acl.SidDao;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.model.security.Permission;
import org.scada_lts.user_management.service.acl.PermissionEvaluatorServiceImp;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class AclTest {

    private SidDao sidDao;
    private EntityClassDao entityClassDao;
    private EntityIdentityDao entityIdentityDao;
    private EntryDao entryDao;


    @Before
    public void init() {

        // sid
        sidDao = new SidDao();
        sidDao.create(new Sid(1L,"john"));
        sidDao.create(new Sid(1L,"jane"));
        sidDao.create(new Sid(1L, "mike"));

        // class
        entityClassDao = new EntityClassDao();
        entityClassDao.create(new EntityClass("org.scada_lts.domain.DataSource"));
        entityClassDao.create(new EntityClass("org.scada_lts.domain.Point"));
        entityClassDao.create(new EntityClass("org.scada_lts.domain.View"));

        // entity identity
        entityIdentityDao = new EntityIdentityDao();

        entityIdentityDao.create(
                new EntityIdentity(
                        "simple1",
                        entityClassDao.get(1L),
                        null,
                        sidDao.get(1L)
                )
        );
        entityIdentityDao.create(
                new EntityIdentity(
                        "simple2",
                        entityClassDao.get(1L),
                        null,
                        sidDao.get(1L)
                )
        );
        entityIdentityDao.create(
                new EntityIdentity(
                        "simple3",
                        entityClassDao.get(1L),
                        null,
                        sidDao.get(1L)
                )
        );

        entityIdentityDao.create(
                new EntityIdentity(
                        "next1",
                        entityClassDao.get(2L),
                        entityIdentityDao.get(1L),
                        null,
                        sidDao.get(1L)
                )
        );

        entityIdentityDao.create(
                new EntityIdentity(
                        "next2",
                        entityClassDao.get(2L),
                        entityIdentityDao.get(2L),
                        null,
                        sidDao.get(1L)
                )
        );

        entityIdentityDao.create(
                new EntityIdentity(
                        "next3",
                        entityClassDao.get(2L),
                        entityIdentityDao.get(3L),
                        null,
                        sidDao.get(1L)
                )
        );

        entityIdentityDao.create(
                new EntityIdentity(
                        "next11",
                        entityClassDao.get(3L),
                        entityIdentityDao.get(1L),
                        null,
                        sidDao.get(1L)
                )
        );

        entityIdentityDao.create(
                new EntityIdentity(
                        "next12",
                        entityClassDao.get(3L),
                        entityIdentityDao.get(2L),
                        null,
                        sidDao.get(1L)

                )
        );

        entityIdentityDao.create(
                new EntityIdentity(
                        "next13",
                        entityClassDao.get(3L),
                        entityIdentityDao.get(3L),
                        null,
                        sidDao.get(1L)
                )
        );

        // entry

        entryDao = new EntryDao();

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
                        entityIdentityDao.get(3L),
                        1L,
                        sidDao.get(1L),
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

        //5
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(2L),
                        2L,
                        sidDao.get(1L),
                        (byte) 2
                )
        );

        //6
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(3L),
                        2L,
                        sidDao.get(1L),
                        (byte) 2
                )
        );

        //7
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(4L),
                        1L,
                        sidDao.get(1L),
                        (byte) 1
                )
        );

        //8
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(5L),
                        1L,
                        sidDao.get(1L),
                        (byte) 1
                )
        );

        //9
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(6L),
                        1L,
                        sidDao.get(1L),
                        (byte) 1
                )
        );

        //10
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(7L),
                        1L,
                        sidDao.get(1L),
                        (byte) 1
                )
        );

        //11
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(8L),
                        1L,
                        sidDao.get(1L),
                        (byte) 1
                )
        );

        //12
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(9L),
                        1L,
                        sidDao.get(1L),
                        (byte) 1
                )
        );

        //13
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(7L),
                        2L,
                        sidDao.get(1L),
                        (byte) 2
                )
        );

        //14
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(8L),
                        2L,
                        sidDao.get(1L),
                        (byte) 2
                )
        );

        //15
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(9L),
                        2L,
                        sidDao.get(1L),
                        (byte) 2
                )
        );

        //28
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(4L),
                        3L,
                        sidDao.get(2L),
                        (byte) 1
                )
        );

        //29
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(5L),
                        3L,
                        sidDao.get(2L),
                        (byte) 1
                )
        );

        //30
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(6L),
                        3L,
                        sidDao.get(2L),
                        (byte) 1
                )
        );

        //31
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(4L),
                        4L,
                        sidDao.get(2L),
                        (byte) 2
                )
        );

        //32
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(5L),
                        4L,
                        sidDao.get(2L),
                        (byte) 2
                )
        );

        //33
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(6L),
                        4L,
                        sidDao.get(2L),
                        (byte) 2
                )
        );

        //34
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(7L),
                        3L,
                        sidDao.get(2L),
                        (byte) 1
                )
        );

        //35
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(8L),
                        3L,
                        sidDao.get(2L),
                        (byte) 1
                )
        );

        //36
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(9L),
                        3L,
                        sidDao.get(2L),
                        (byte) 1
                )
        );

        //37
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(7L),
                        4L,
                        sidDao.get(2L),
                        (byte) 2
                )
        );

        //38
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(8L),
                        4L,
                        sidDao.get(2L),
                        (byte) 2
                )
        );

        //39
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(9L),
                        4L,
                        sidDao.get(2L),
                        (byte) 2
                )
        );

        //40
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(7L),
                        5L,
                        sidDao.get(3L),
                        (byte) 1
                )
        );

        //41
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(8L),
                        5L,
                        sidDao.get(3L),
                        (byte) 1
                )
        );

        //42
        entryDao.create(
                new Entry(
                        entityIdentityDao.get(9L),
                        5L,
                        sidDao.get(3L),
                        (byte) 1
                )
        );
    }

    @Test
    public void testFilterDataBaseOnACL() {
        //TODO rewrite to mockito
        PermissionEvaluatorServiceImp permissionEvaluatorService = new PermissionEvaluatorServiceImp();
        permissionEvaluatorService.setEntryDao(entryDao);

        Sid sid = sidDao.get(1L);
        EntityClass entityClass = entityClassDao.get(1L);
        Permission permission = new Permission((byte) 1);

        List<Entry> lst = permissionEvaluatorService.filterDataBaseOnACL(sid, entityClass, permission);
        assertTrue(lst.size()==3);
        //assertTrue(false);
    }

    //@Test
    public void testHasPermissionSid() {
        PermissionEvaluatorServiceImp permissionEvaluatorService = new PermissionEvaluatorServiceImp();
        permissionEvaluatorService.setEntryDao(entryDao);

        Sid sid = sidDao.get(1L);
        EntityIdentity entityIdentity = entityIdentityDao.get(1L);

        Permission permission = new Permission((byte) 1);

        boolean hasPermission = permissionEvaluatorService.hasPermission(sid,entityIdentity,permission);

        assertTrue(hasPermission);

    }

    //TODO
    //@Test
    public void testHasPermissionRole() {
        assertTrue(true);
    }
}
