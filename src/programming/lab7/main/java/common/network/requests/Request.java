package common.network.requests;


import common.models.User;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private String name;
    private User user;


    public Request(String name, User user){
        this.name = name;
        this.user = user;
    }

    public String getName(){
        return name;
    }

    public User getUser() {
        return user;
    }
    public boolean isAuth(){
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request request)) return false;
        return Objects.equals(getName(), request.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
