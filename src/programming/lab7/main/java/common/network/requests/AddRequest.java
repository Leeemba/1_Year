package common.network.requests;

import common.models.Organization;
import common.models.User;
import common.network.CommandNames;

public class AddRequest extends  Request{
    private final Organization organization;

    public AddRequest(Organization organization, User user){
        super(CommandNames.ADD,user);
        this.organization = organization;
    }

    public Organization getOrganization(){
        return organization;
    }

}
