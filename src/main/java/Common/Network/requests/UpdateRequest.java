package Common.Network.requests;

import Common.Models.Organization;
import Common.Network.CommandNames;

public class UpdateRequest extends Request{
    private final int id;
    private final Organization updOrg;
    public UpdateRequest(int id,Organization updOrg){
        super(CommandNames.UPDATE);
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
