package Common.Network.requests;

import Common.Network.CommandNames;

public class RemoveByIdRequest extends Request{
    private int id;
    public RemoveByIdRequest(int id){
        super(CommandNames.REMOVE_BY_ID);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
