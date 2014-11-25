package com.redhat.cee.dashboard.domain;

import java.util.ArrayList;
import javax.persistence.Entity;

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
