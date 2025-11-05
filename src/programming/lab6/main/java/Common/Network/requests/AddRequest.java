package Common.Network.requests;

import Common.Models.Organization;
import Common.Network.CommandNames;

public class AddRequest extends  Request{
    private final Organization organization;

    public AddRequest(Organization organization){
        super(CommandNames.ADD);
        this.organization = organization;
    }

    public Organization getOrganization(){
        return organization;
    }

}
