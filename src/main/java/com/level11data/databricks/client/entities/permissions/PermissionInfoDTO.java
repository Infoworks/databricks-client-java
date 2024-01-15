package com.level11data.databricks.client.entities.permissions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.level11data.databricks.permissions.PermissionLevel;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionInfoDTO {
    @JsonProperty("user_name")
    public String UserName;
    @JsonProperty("group_name")
    public String GroupName;
    @JsonProperty("service_principal_name")
    public String ServicePrincipalName;
    @JsonProperty("permission_level")
    public PermissionLevel PermissionLevel;


}
