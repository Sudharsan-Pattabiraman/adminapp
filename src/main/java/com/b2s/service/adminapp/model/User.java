package com.b2s.service.adminapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class User {

    private String userId;

    private String userPassword;

    private String userName;

    private String userAddress;

    private Optional<String> userCity;

    private String userState;

    private int userZIP;

    private User(UserBuilder userBuilder) {
        this.userId = userBuilder.userId;
        this.userPassword = userBuilder.userPassword;
        this.userName = userBuilder.userName;
        this.userAddress = userBuilder.userAddress;
        this.userCity = userBuilder.userCity;
        this.userState = userBuilder.userState;
        this.userZIP = userBuilder.userZIP;
    }

    @JsonCreator
    public static User create(@JsonProperty("userId") final String theUserId,
                              @JsonProperty("userPassword") final String theUserPassword,
                              @JsonProperty("userName") final String theUserName,
                              @JsonProperty("userAddress") final String theUserAddress,
                              @JsonProperty("userCity") final String theUserCity,
                              @JsonProperty("userState") final String theUserState,
                              @JsonProperty("userZIP") final int theUserZip){
        return builder().withUserId(theUserId).withUserPassword(theUserPassword).withUserName(theUserName)
                .withUserAddress(theUserAddress).withUserCity(theUserCity)
                .withUserState(theUserState).withUserZIP(theUserZip).build();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public Optional<String> getUserCity() {
        return userCity;
    }

    public String getUserState() {
        return userState;
    }

    public int getUserZIP() {
        return userZIP;
    }

    public int getUserZip() {
        return userZIP;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserZIP() != user.getUserZIP()) return false;
        if (!getUserId().equals(user.getUserId())) return false;
        if (!getUserPassword().equals(user.getUserPassword())) return false;
        if (!getUserName().equals(user.getUserName())) return false;
        if (!getUserAddress().equals(user.getUserAddress())) return false;
        if (!getUserCity().equals(user.getUserCity())) return false;
        return getUserState().equals(user.getUserState());

    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getUserPassword().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getUserAddress().hashCode();
        result = 31 * result + getUserCity().hashCode();
        result = 31 * result + getUserState().hashCode();
        result = 31 * result + getUserZIP();
        return result;
    }

    @Override
    public String toString() {
        return "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userCity='" + userCity + '\'' +
                ", userState='" + userState + '\'' +
                ", userZIP=" + userZIP;
    }

    public static class UserBuilder {

        private String userId;

        private String userPassword;

        private String userName;

        private String userAddress;

        private Optional<String> userCity = Optional.empty();

        private String userState;

        private int userZIP;

        public UserBuilder() {
        }

        public UserBuilder withUserId(final String userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder withUserPassword(final String userPassword) {
            this.userPassword = userPassword;
            return this;
        }

        public UserBuilder withUserName(final String userName) {
            this.userName = userName;
            return this;
        }

        public UserBuilder withUserAddress(final String userAddress) {
            this.userAddress = userAddress;
            return this;
        }

        public UserBuilder withUserCity(final String userCity) {
            this.userCity = Optional.ofNullable(userCity);
            return this;
        }

        public UserBuilder withUserState(final String userState) {
            this.userState = userState;
            return this;
        }

        public UserBuilder withUserZIP(final int userZIP) {
            this.userZIP = userZIP;
            return this;
        }

        public User build() {
            return new User(this);
        }
    };
}
