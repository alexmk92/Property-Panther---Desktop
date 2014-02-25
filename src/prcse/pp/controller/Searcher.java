package prcse.pp.controller;

import prcse.pp.model.Tenant;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 25/02/14
 * Time: 20:07
 * To change this template use File | Settings | File Templates.
 */
public class Searcher {
    Tenant t = new Tenant();

    public void setTenant(Tenant thisTenant)
    {
        this.t = thisTenant;
    }

    public Tenant getTenant()
    {
        Tenant thisTenant = null;

        if(null != this.t)
        {
            thisTenant = t;
        }

        return thisTenant;
    }
}
