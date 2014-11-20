package com.redhat.gss.dashboard.dao;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import com.redhat.gss.dashboard.domain.Employee;
import org.slf4j.Logger;

/**
 * Author: Ingo Weiss <iweiss@redhat.com>
 */

@Stateful
public class EmployeeDao
{
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Inject
    private Logger LOG;

    public boolean save(Employee employee)
    {
        if(employee == null) { return false; }

        try
        {
            entityManager.persist(employee);
            return true;
        }
        catch (Exception e)
        {
            LOG.error(e.toString());
            return false;
        }
    }

    public Employee findById(long id)
    {
        if(id <= 0) { return null; }

        return entityManager.find(Employee.class, id);
    }
}
