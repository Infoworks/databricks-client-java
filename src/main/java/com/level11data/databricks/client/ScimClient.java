package com.level11data.databricks.client;

import com.level11data.databricks.client.entities.jobs.UserDTO;
import com.level11data.databricks.session.WorkspaceSession;

import javax.ws.rs.core.Response;

public class ScimClient extends AbstractDatabricksClient{
    private final String ENDPOINT_TARGET = "api/2.0/preview/scim/v2";

    public ScimClient(WorkspaceSession session) {
        super(session);
    }

    public UserDTO getCurrentUser() throws HttpException {
        String pathSuffix = ENDPOINT_TARGET + "/Me";

        Response response = Session.getRequestBuilder(pathSuffix).get();

        checkResponse(response);
        return response.readEntity(UserDTO.class);
    }
}
