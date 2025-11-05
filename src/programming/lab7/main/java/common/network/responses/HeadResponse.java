package common.network.responses;

import common.models.Organization;
import common.network.CommandNames;
import common.network.ResponseStatus;

public class HeadResponse extends Response{
    private Organization head;
    public HeadResponse(String errorMessage, ResponseStatus status, Organization head){
        super(CommandNames.HEAD,errorMessage,status);
        this.head = head;
    }

    public Organization getHead() {
        return head;
    }
}
