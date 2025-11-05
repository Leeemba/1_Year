package Common.Network.responses;

import Client.Commands.Head;
import Common.Models.Organization;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;

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
