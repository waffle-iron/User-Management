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
package org.scada_lts.user_management.service.acl;

import org.scada_lts.user_management.dao.acl.SidDao;
import org.scada_lts.user_management.model.acl.Sid;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk arkadiusz.parafiniuk@gmail.com
 *
 * Business logic for Sid
 * @see Sid
 */
@Service
public class SidServiceImpl implements SidService {

    @Resource
    SidDao sidDao;

    @Override
    public List<Sid> getAll() {
        return sidDao.getAll();
    }

    @Override
    public Sid add(Sid sid) {
        return sidDao.create(sid);
    }

    @Override
    public void delete(Sid sid) {
        sidDao.delete(sid);
    }

    @Override
    public Sid getSid(Long id) {
        return sidDao.get(id);
    }

    @Override
    public void update(Sid sid) {
        sidDao.update(sid);
    }
}
