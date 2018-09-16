package com.farahnaz.tests.throttle.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class ClientInfo {

    public Integer ID;
    public LocalDateTime lastAccess;
    public Integer caps;
    public String info;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(LocalDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientInfo that = (ClientInfo) o;
        return Objects.equals(ID, that.ID) &&
                Objects.equals(lastAccess, that.lastAccess) &&
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, lastAccess, info);
    }
}
