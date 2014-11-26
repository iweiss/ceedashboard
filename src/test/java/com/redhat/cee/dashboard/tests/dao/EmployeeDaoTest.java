package com.redhat.cee.dashboard.tests.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.inject.Inject;
import com.redhat.cee.dashboard.dao.EmployeeDao;
import com.redhat.cee.dashboard.domain.Employee;
import com.redhat.cee.dashboard.domain.Engineer;
import com.redhat.cee.dashboard.domain.Manager;
import com.redhat.cee.dashboard.domain.PTO;
import com.redhat.cee.dashboard.util.LogFactory;
import static junit.framework.Assert.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;


/**
 * Author: Ingo Weiss <iweiss@redhat.com>
 */

@RunWith(Arquillian.class)
public class EmployeeDaoTest
{
    @Inject
    EmployeeDao dao;

    @Inject
    Logger LOG;

    @Deployment
    public static WebArchive createDeployment()
    {
        WebArchive deployment = ShrinkWrap.create(WebArchive.class, "ceedashboard-test.war")
                                    .addClasses(EmployeeDao.class, LogFactory.class)
                                    .addPackage(Employee.class.getPackage())
                                    .addAsResource("META-INF/persistence.xml")
                                    .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        return deployment;
    }

    @Test
    public void injectDao()
    {
        LOG.info("Testing DAO injection...");
        assertNotNull(dao);
    }

    @Test
    public void daoPersistEngineer()
    {
        LOG.info("Testing DAO persist() for Engineer");

        Engineer employee = createEngineer();

        assertTrue(dao.save(employee));
    }

    @Test
    public void daoFindEngineerById()
    {
        LOG.info("Testing DAO findById() for Engineer");

        Engineer employee = createEngineer();
        dao.save(employee);

        Engineer engineer = (Engineer) dao.findById(employee.getId());

        assertEquals("Joe Engineer", engineer.getName());
        assertEquals("TSE", engineer.getPosition());
        assertTrue(engineer.getSbrs().contains("sbr-storage"));
        assertFalse(engineer.getPto().isEmpty());
        assertEquals(1, engineer.getPto().size());

        PTO pto = engineer.getPto().get(0);
        LOG.error("PTO starts: " + String.valueOf(pto.getStart()));
        LOG.error("PTO ends: " + String.valueOf(pto.getEnd()));
        LOG.error("PTO reason: " + pto.getReason());
    }

    @Test
    public void daoPersistManager()
    {
        LOG.info("Testing DAO persist() for Manager");

        Manager employee = createManager();

        assertTrue(dao.save(employee));
    }

    @Test
    public void daoFindManagerById()
    {
        LOG.info("Testing DAO findById() for Manager");

        Manager employee = createManager();
        dao.save(employee);

        Employee manager = dao.findById(employee.getId());

        assertEquals("Engineer not found.", "Mary Supervisor", manager.getName());
        assertEquals("Engineer not found.", "Supervisor", manager.getPosition());
    }

    private Engineer createEngineer()
    {
        Engineer engineer = new Engineer();

        engineer.setEmail("asd@asd.com");
        engineer.setName("Joe Engineer");
        engineer.setOffice("FAB");
        engineer.setPosition("TSE");

        ArrayList<String> sbrs = new ArrayList<>();
        sbrs.add("sbr-networking");
        sbrs.add("sbr-storage");
        engineer.setSbrs(sbrs);

        List<PTO> ptoList = new ArrayList<>();
        ptoList.add(createPTO());

        engineer.setPto(ptoList);

        return engineer;
    }

    private Manager createManager()
    {
        Manager manager = new Manager();

        manager.setEmail("asd@asd.com");
        manager.setName("Mary Supervisor");
        manager.setOffice("FAB");
        manager.setPosition("Supervisor");

        return manager;
    }

    private PTO createPTO()
    {
        PTO pto = new PTO();

        pto.setReason("Holidays");
        pto.setStart(new Date(new GregorianCalendar(2014, 11, 25).getTimeInMillis()));
        pto.setEnd(new Date(new GregorianCalendar(2014, 11, 28).getTimeInMillis()));

        return pto;
    }
}
