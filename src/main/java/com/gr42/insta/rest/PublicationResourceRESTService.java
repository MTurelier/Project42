/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gr42.insta.rest;


import com.gr42.insta.model.NewMediaRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

/**
 * JAX-RS Example
        * <p/>
        * This class produces a RESTful service to read/write the contents of the members table.
        */
@Path("/publications")
@RequestScoped
public class PublicationResourceRESTService {

    @Inject
    private Logger log;

    @Inject
    private Validator validator;


    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPublication(@MultipartForm MultipartFormDataInput request) {
        Response.ResponseBuilder builder = null;
        NewMediaRequest req = new NewMediaRequest();
        byte [] img = null;
        try {
            req.setComment(request.getFormDataMap().get("comment").get(0).getBodyAsString());
            InputStream inputStream = request.getFormDataMap().get("image").get(0).getBody(InputStream.class, null);
            if (inputStream.available() != 0)
                img = IOUtils.toByteArray(inputStream);
            FileUtils.writeByteArrayToFile(new File("pathname"), img);
        } catch (IOException e) {
            e.printStackTrace();
            builder = Response.noContent();
            return builder.build();
        }
        builder = Response.ok();
        return builder.build();
    }

}
