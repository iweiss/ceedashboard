package com.redhat.cee.dashboard.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Author: Ingo Weiss <iweiss@redhat.com>
 */

@Entity
public class PTO implements Serializable
{
    @Id
    private long id;

    private Date start;
    private Date end;
    private String reason;
}
