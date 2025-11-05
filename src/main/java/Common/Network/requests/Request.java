package Common.Network.requests;


import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
    private String name;


    public Request(String name){
        this.name = name;
    }

    public String getName(){
        return name;
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
