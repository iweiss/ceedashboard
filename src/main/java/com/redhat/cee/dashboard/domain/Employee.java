package com.redhat.cee.dashboard.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Author: Ingo Weiss <iweiss@redhat.com>
 */

@Entity
public class Employee implements Serializable
{
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String office;
    @NotNull
    private String position;

    @OneToMany
    private List<PTO> pto;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getOffice()
    {
        return office;
    }

    public void setOffice(String office)
    {
        this.office = office;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public List<PTO> getPto()
    {
        return pto;
    }

    public void setPto(List<PTO> pto)
    {
        this.pto = pto;
    }
}
