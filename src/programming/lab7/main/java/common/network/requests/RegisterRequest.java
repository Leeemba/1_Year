package common.network.requests;

import common.models.User;
import common.network.CommandNames;

public class RegisterRequest extends Request {

    public RegisterRequest(User user){
        super(CommandNames.REGISTER,user);
    }
    @Override
    public boolean isAuth(){
        return true;
    }

}
