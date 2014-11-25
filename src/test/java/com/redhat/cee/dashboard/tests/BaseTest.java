package com.redhat.cee.dashboard.tests;

import com.redhat.cee.dashboard.dao.EmployeeDao;
import com.redhat.cee.dashboard.domain.Employee;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * Author: Ingo Weiss <iweiss@redhat.com>
 */

public class BaseTest
{
    @Deployment
    public static WebArchive createDeployment()
    {
        WebArchive deployment = ShrinkWrap.create(WebArchive.class)
                                    .addClasses(EmployeeDao.class, Employee.class)
                                    .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

        return deployment;
    }
}
