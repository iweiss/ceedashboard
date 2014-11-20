package com.redhat.gss.dashboard.domain;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Author: Ingo Weiss <iweiss@redhat.com>
 */

@Entity
public class Engineer extends Employee
{
    private ArrayList<String> Sbrs;

    public ArrayList<String> getSbrs()
    {
        return Sbrs;
    }

    public void setSbrs(ArrayList<String> sbrs)
    {
        Sbrs = sbrs;
    }
}
