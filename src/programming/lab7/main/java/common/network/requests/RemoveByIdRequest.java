package common.network.requests;


import common.models.User;
import common.network.CommandNames;

public class RemoveByIdRequest extends Request{
    private int id;
    public RemoveByIdRequest(int id, User user){
        super(CommandNames.REMOVE_BY_ID,user);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
