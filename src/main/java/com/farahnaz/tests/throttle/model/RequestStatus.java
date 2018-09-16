package com.farahnaz.tests.throttle.model;

public class RequestStatus {

    public Long accessCode;
    public String accessPermissionDescription;

    public Long getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(Long accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessPermissionDescription() {
        return accessPermissionDescription;
    }

    public void setAccessPermissionDescription(String accessPermissionDescription) {
        this.accessPermissionDescription = accessPermissionDescription;
    }
}
