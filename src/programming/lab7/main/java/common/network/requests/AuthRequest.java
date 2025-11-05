package common.network.requests;

import common.models.User;
import common.network.CommandNames;

public class AuthRequest extends  Request{
    public AuthRequest(User user){
        super(CommandNames.AUTHENTICATE,user);

    }
    @Override
    public boolean isAuth(){
        return true;
    }

}
