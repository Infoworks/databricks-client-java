package com.level11data.databricks.client.entities.permissions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionsDTO {

    @JsonProperty("object_id")
    public String ObjectId;

    @JsonProperty("object_type")
    public String ObjectType;

    @JsonProperty("access_control_list")
    public PermissionInfoDTO[] AccessControlList;

}
