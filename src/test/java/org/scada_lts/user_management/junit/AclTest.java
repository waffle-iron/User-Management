package org.scada_lts.user_management.junit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.scada_lts.user_management.model.acl.EntityClass;
import org.scada_lts.user_management.model.acl.EntityIdentity;
import org.scada_lts.user_management.model.acl.Entry;
import org.scada_lts.user_management.model.acl.Sid;
import org.scada_lts.user_management.dao.acl.EntityClassDao;
import org.scada_lts.user_management.dao.acl.EntityIdentityDao;
import org.scada_lts.user_management.dao.acl.EntryDao;
import org.scada_lts.user_management.dao.acl.SidDao;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class AclTest {

    private SidDao sidService;
    private EntityClassDao entityClassService;
    private EntityIdentityDao entityIdentityService;
    private EntryDao entryService;

    @Before
    public void init() {

        // sid
        sidService = new SidDao();
        sidService.create(new Sid(1L,"john"));
        sidService.create(new Sid(1L,"jane"));
        sidService.create(new Sid(1L, "mike"));

        // class
        entityClassService = new EntityClassDao();
        entityClassService.create(new EntityClass("org.scada_lts.domain.DataSource"));
        entityClassService.create(new EntityClass("org.scada_lts.domain.Point"));
        entityClassService.create(new EntityClass("org.scada_lts.domain.View"));

        // entity identity
        entityIdentityService = new EntityIdentityDao();

        entityIdentityService.create(
                new EntityIdentity(
                        "simple1",
                        entityClassService.get(1L),
                        null,
                        sidService.get(1L)
                )
        );
        entityIdentityService.create(
                new EntityIdentity(
                        "simple2",
                        entityClassService.get(1L),
                        null,
                        sidService.get(1L)
                )
        );
        entityIdentityService.create(
                new EntityIdentity(
                        "simple3",
                        entityClassService.get(1L),
                        null,
                        sidService.get(1L)
                )
        );

        entityIdentityService.create(
                new EntityIdentity(
                        "next1",
                        entityClassService.get(2L),
                        entityIdentityService.get(1L),
                        null,
                        sidService.get(1L)
                )
        );

        entityIdentityService.create(
                new EntityIdentity(
                        "next2",
                        entityClassService.get(2L),
                        entityIdentityService.get(2L),
                        null,
                        sidService.get(1L)
                )
        );

        entityIdentityService.create(
                new EntityIdentity(
                        "next3",
                        entityClassService.get(2L),
                        entityIdentityService.get(3L),
                        null,
                        sidService.get(1L)
                )
        );

        entityIdentityService.create(
                new EntityIdentity(
                        "next11",
                        entityClassService.get(3L),
                        entityIdentityService.get(1L),
                        null,
                        sidService.get(1L)
                )
        );

        entityIdentityService.create(
                new EntityIdentity(
                        "next12",
                        entityClassService.get(3L),
                        entityIdentityService.get(2L),
                        null,
                        sidService.get(1L)

                )
        );

        entityIdentityService.create(
                new EntityIdentity(
                        "next13",
                        entityClassService.get(3L),
                        entityIdentityService.get(3L),
                        null,
                        sidService.get(1L)
                )
        );

        // entry

        entryService = new EntryDao();

        //1
        entryService.create(
                new Entry(
                        entityIdentityService.get(1L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //2
        entryService.create(
                new Entry(
                        entityIdentityService.get(2L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //3
        entryService.create(
                new Entry(
                        entityIdentityService.get(3L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //4
        entryService.create(
                new Entry(
                        entityIdentityService.get(1L),
                        2L,
                        sidService.get(1L),
                        (byte) 2
                )
        );

        //5
        entryService.create(
                new Entry(
                        entityIdentityService.get(2L),
                        2L,
                        sidService.get(1L),
                        (byte) 2
                )
        );

        //6
        entryService.create(
                new Entry(
                        entityIdentityService.get(3L),
                        2L,
                        sidService.get(1L),
                        (byte) 2
                )
        );

        //7
        entryService.create(
                new Entry(
                        entityIdentityService.get(4L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //8
        entryService.create(
                new Entry(
                        entityIdentityService.get(5L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //9
        entryService.create(
                new Entry(
                        entityIdentityService.get(6L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //10
        entryService.create(
                new Entry(
                        entityIdentityService.get(7L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //11
        entryService.create(
                new Entry(
                        entityIdentityService.get(8L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //12
        entryService.create(
                new Entry(
                        entityIdentityService.get(9L),
                        1L,
                        sidService.get(1L),
                        (byte) 1
                )
        );

        //13
        entryService.create(
                new Entry(
                        entityIdentityService.get(7L),
                        2L,
                        sidService.get(1L),
                        (byte) 2
                )
        );

        //14
        entryService.create(
                new Entry(
                        entityIdentityService.get(8L),
                        2L,
                        sidService.get(1L),
                        (byte) 2
                )
        );

        //15
        entryService.create(
                new Entry(
                        entityIdentityService.get(9L),
                        2L,
                        sidService.get(1L),
                        (byte) 2
                )
        );

        //28
        entryService.create(
                new Entry(
                        entityIdentityService.get(4L),
                        3L,
                        sidService.get(2L),
                        (byte) 1
                )
        );

        //29
        entryService.create(
                new Entry(
                        entityIdentityService.get(5L),
                        3L,
                        sidService.get(2L),
                        (byte) 1
                )
        );

        //30
        entryService.create(
                new Entry(
                        entityIdentityService.get(6L),
                        3L,
                        sidService.get(2L),
                        (byte) 1
                )
        );

        //31
        entryService.create(
                new Entry(
                        entityIdentityService.get(4L),
                        4L,
                        sidService.get(2L),
                        (byte) 2
                )
        );

        //32
        entryService.create(
                new Entry(
                        entityIdentityService.get(5L),
                        4L,
                        sidService.get(2L),
                        (byte) 2
                )
        );

        //33
        entryService.create(
                new Entry(
                        entityIdentityService.get(6L),
                        4L,
                        sidService.get(2L),
                        (byte) 2
                )
        );

        //34
        entryService.create(
                new Entry(
                        entityIdentityService.get(7L),
                        3L,
                        sidService.get(2L),
                        (byte) 1
                )
        );

        //35
        entryService.create(
                new Entry(
                        entityIdentityService.get(8L),
                        3L,
                        sidService.get(2L),
                        (byte) 1
                )
        );

        //36
        entryService.create(
                new Entry(
                        entityIdentityService.get(9L),
                        3L,
                        sidService.get(2L),
                        (byte) 1
                )
        );

        //37
        entryService.create(
                new Entry(
                        entityIdentityService.get(7L),
                        4L,
                        sidService.get(2L),
                        (byte) 2
                )
        );

        //38
        entryService.create(
                new Entry(
                        entityIdentityService.get(8L),
                        4L,
                        sidService.get(2L),
                        (byte) 2
                )
        );

        //39
        entryService.create(
                new Entry(
                        entityIdentityService.get(9L),
                        4L,
                        sidService.get(2L),
                        (byte) 2
                )
        );

        //40
        entryService.create(
                new Entry(
                        entityIdentityService.get(7L),
                        5L,
                        sidService.get(3L),
                        (byte) 1
                )
        );

        //41
        entryService.create(
                new Entry(
                        entityIdentityService.get(8L),
                        5L,
                        sidService.get(3L),
                        (byte) 1
                )
        );

        //42
        entryService.create(
                new Entry(
                        entityIdentityService.get(9L),
                        5L,
                        sidService.get(3L),
                        (byte) 1
                )
        );


    }

    @Test
    public void test() {

        assertTrue(true);
    }
}
