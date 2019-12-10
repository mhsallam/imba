package com.mhsallam.imba.common;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "enums")
@Getter
public class AppEnumProperties {
    private Map<String, Integer> organisationTypes;
    private Map<String, Integer> identityTypes;

    public Map<String, Integer> getOrganisationTypes() {
        return organisationTypes;
    }

    public void setOrganisationTypes(Map<String, Integer> organisationTypes) {
        this.organisationTypes = organisationTypes;
    }

    public Map<String, Integer> getIdentityTypes() {
        return identityTypes;
    }

    public void setIdentityTypes(Map<String, Integer> identityTypes) {
        this.identityTypes = identityTypes;
    }
}
