package com.redhat.cee.dashboard.controller;

import java.util.ArrayList;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.redhat.cee.dashboard.dao.EmployeeDao;
import com.redhat.cee.dashboard.domain.Engineer;
import org.slf4j.Logger;

/**
 * Author: Ingo Weiss <iweiss@redhat.com>
 */

@Path("/user")
public class EmployeeController
{
    @Inject
    private Logger LOG;

    @Inject
    private EmployeeDao dao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") long id)
    {
        LOG.info("Trying to get user by ID...");

        if(id > 0)
        {
            return Response.ok().entity(dao.findById(id)).build();
        }
        else
        {
            return Response.ok().entity("Null ID").build();
        }
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response save()
    {
        Engineer employee = new Engineer();

        employee.setEmail("asd@asd.com");
        employee.setName("ASD ASD");
        employee.setOffice("FAB");
        employee.setPosition("TSE");
        ArrayList<String> sbrs = new ArrayList<>();
        sbrs.add("sbr-networking");
        sbrs.add("sbr-storage");
        employee.setSbrs(sbrs);

        boolean result = dao.save(employee);

        StringBuilder response = new StringBuilder();
        if(result)
        {
            response.append("Engineer: ")
                .append(employee.getName())
                .append(" was saved successfully!")
                .append("\n")
                .append("Result: ")
                .append(result);
        }
        else
        {
            response.append("Engineer: ")
                .append(employee.getName())
                .append(" was not saved successfully!")
                .append("\n")
                .append("Result: ")
                .append(result);
        }

//        return response.toString();
        return Response.ok().entity(employee).build();
    }
}
