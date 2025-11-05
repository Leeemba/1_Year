package common.network.requests;


import common.models.Organization;
import common.models.User;
import common.network.CommandNames;

public class UpdateRequest extends Request{
    private final int id;
    private final Organization updOrg;
    public UpdateRequest(int id, Organization updOrg, User user){
        super(CommandNames.UPDATE,user);
        this.id = id;
        this.updOrg= updOrg;
    }

    public int getId() {
        return id;
    }

    public Organization getUpdOrg(){
        return updOrg;
    }
}
