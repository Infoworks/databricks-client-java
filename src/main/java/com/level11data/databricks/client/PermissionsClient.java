package com.level11data.databricks.client;

import com.level11data.databricks.client.entities.permissions.PermissionInfoDTO;
import com.level11data.databricks.client.entities.permissions.PermissionsDTO;
import com.level11data.databricks.permissions.RequestObjectType;
import com.level11data.databricks.session.WorkspaceSession;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;


public class PermissionsClient extends AbstractDatabricksClient {
    private final String ENDPOINT_TARGET = "api/2.0/permissions";
    public PermissionsClient(WorkspaceSession session) {
        super(session);
    }

    /**
     *
     * @param requestObjectType
     * can be one of authorization, clusters, cluster-policies, directories, experiments, files, instance-pools, jobs,
     * notebooks, pipelines, registered-models, repos, serving-endpoints, or sql-warehouses
     * @param objectId
     * this is objectId of requestedObject to get permissions
     * @return PermissionsDTO
     */
    public PermissionsDTO getPermissions(RequestObjectType requestObjectType, String objectId) throws HttpException {
        String pathSuffix = getPathSuffix(requestObjectType) + objectId;
        Response response = Session.getRequestBuilder(pathSuffix).get();
        checkResponse(response);
        return response.readEntity(PermissionsDTO.class);
    }

    public PermissionsDTO setPermissions(RequestObjectType requestObjectType, String objectId, PermissionInfoDTO[] accessList) throws HttpException {
        String pathSuffix = getPathSuffix(requestObjectType) + objectId;
        PermissionsDTO permissionsDTO = new PermissionsDTO();
        permissionsDTO.AccessControlList = accessList;
        Response response = Session.getRequestBuilder(pathSuffix).put(Entity.json(permissionsDTO));
        checkResponse(response);
        return response.readEntity(PermissionsDTO.class);
    }

    public PermissionsDTO updatePermissions(RequestObjectType requestObjectType, String objectId, PermissionInfoDTO[] accessList) throws HttpException {
        String pathSuffix = getPathSuffix(requestObjectType) + objectId;
        PermissionsDTO permissionsDTO = new PermissionsDTO();
        permissionsDTO.AccessControlList = accessList;
        Response response = Session.getRequestBuilder(pathSuffix).method("PATCH", Entity.json(permissionsDTO));
        checkResponse(response);
        return response.readEntity(PermissionsDTO.class);
    }

    private String getPathSuffix(RequestObjectType requestObjectType) {
        switch (requestObjectType) {
            case AUTHORIZATION:
                return ENDPOINT_TARGET + "/authorization";
            case CLUSTERS:
                return ENDPOINT_TARGET + "/clusters";
            case CLUSTER_POLICIES:
                return ENDPOINT_TARGET + "/cluster-policies";
            case DIRECTORIES:
                return ENDPOINT_TARGET + "/directories";
            case EXPERIMENTS:
                return ENDPOINT_TARGET + "/experiments";
            case FILES:
                return ENDPOINT_TARGET + "/files";
            case INSTANCE_POOLS:
                return ENDPOINT_TARGET + "/instance-pools";
            case JOBS:
                return ENDPOINT_TARGET + "/jobs";
            case NOTEBOOKS:
                return ENDPOINT_TARGET + "/notebooks";
            case PIPELINES:
                return ENDPOINT_TARGET + "/pipelines";
            case REGISTERED_MODELS:
                return ENDPOINT_TARGET + "/registered-models";
            case REPOS:
                return ENDPOINT_TARGET + "/repos";
            case SERVING_ENDPOINTS:
                return ENDPOINT_TARGET + "/serving-endpoints";
            case SQL_WAREHOUSES:
                return ENDPOINT_TARGET + "/sql-warehouses";
            default:
                return ENDPOINT_TARGET;
        }
    }

}
