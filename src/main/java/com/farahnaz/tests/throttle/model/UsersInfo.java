package com.farahnaz.tests.throttle.model;

import java.util.Objects;

public class UsersInfo {

    public Integer numberOfUsers;
    public String description;

    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(Integer numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersInfo usersInfo = (UsersInfo) o;
        return Objects.equals(numberOfUsers, usersInfo.numberOfUsers) &&
                Objects.equals(description, usersInfo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfUsers, description);
    }
}
