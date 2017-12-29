package com.thzSirius.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by THZ on 2017/12/13.
 */

@Path("users")
public interface UserRest {

        @GET
        @Path("queryuser")
        public String getUser(String id);
}
