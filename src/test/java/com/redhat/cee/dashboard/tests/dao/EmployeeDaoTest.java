package com.redhat.cee.dashboard.tests.dao;

import javax.inject.Inject;
import com.redhat.cee.dashboard.dao.EmployeeDao;
import com.redhat.cee.dashboard.domain.Employee;
import static junit.framework.TestCase.assertNotNull;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Author: Ingo Weiss <iweiss@redhat.com>
 */

@RunWith(Arquillian.class)
public class EmployeeDaoTest
{
    @Inject
    EmployeeDao dao;

    @Deployment
    public static WebArchive createDeployment()
    {
        WebArchive deployment = ShrinkWrap.create(WebArchive.class)
                                    .addClasses(EmployeeDao.class, Employee.class)
                                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return deployment;
    }

    @Test
    public void injectContainer()
    {
        assertNotNull(dao);
    }
}
