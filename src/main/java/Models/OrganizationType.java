package Models;

public enum OrganizationType {
    PUBLIC,
    GOVERNMENT,
    TRUST;


    public static String names(){
        StringBuilder sb = new StringBuilder();
        for (OrganizationType o: values()) {
            sb.append(o.ordinal()).append("-").append(o.name()).append(" ");
        }
        return sb.toString();
    }
}
