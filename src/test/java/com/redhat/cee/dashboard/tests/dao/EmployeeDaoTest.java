package com.redhat.cee.dashboard.tests.dao;

import com.redhat.cee.dashboard.dao.EmployeeDao;
import com.redhat.cee.dashboard.domain.Employee;
import com.redhat.cee.dashboard.domain.Engineer;
import com.redhat.cee.dashboard.util.LogFactory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNotNull;


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
    public void injectContainer()
    {
        LOG.info("Testing DAO injection...");
        assertNotNull(dao);
    }

    @Test
    public void daoPersist()
    {
        LOG.info("Testing DAO persist()");

        Engineer employee = new Engineer();

        employee.setEmail("asd@asd.com");
        employee.setName("ASD ASD");
        employee.setOffice("FAB");
        employee.setPosition("TSE");
        ArrayList<String> sbrs = new ArrayList<>();
        sbrs.add("sbr-networking");
        sbrs.add("sbr-storage");
        employee.setSbrs(sbrs);

        assertTrue(dao.save(employee));
    }
}
