package client.utility;

import common.models.User;

public class SessionHandler {

    private volatile static SessionHandler instance;
    private User currentUser;
    private SessionHandler(){

    }
    public static SessionHandler getInstance(){
        if (instance==null){
            synchronized (SessionHandler.class){
                if(instance==null) instance=new SessionHandler();
            }
        }
        return instance;
    }

    public synchronized  User getCurrentUser() {
        return currentUser;
    }

    public synchronized  void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
