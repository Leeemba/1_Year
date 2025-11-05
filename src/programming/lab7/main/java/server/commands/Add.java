package server.commands;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.AddRequest;
import common.network.requests.Request;
import common.network.responses.AddResponse;
import common.network.responses.Response;
import server.db.OrganizationRepo;
import server.managers.CollectionManager;


public class Add extends Command implements  Executable{
    private final CollectionManager collectionManager;


    public Add(CollectionManager collectionManager) {
        super(CommandNames.ADD,CommandNames.Descriptions.DESCR_ADD);
        this.collectionManager = collectionManager;

    }


    @Override
    public Response execute(Request request)  {
        var requestData = (AddRequest) request;
        try {

            if (!(requestData.getOrganization().validate())) {
                return new AddResponse(-1, "Поля объекта невалидны,объект не создан!", ResponseStatus.ERROR);
            }
            var org = requestData.getOrganization();
            var orgId = OrganizationRepo.insert(org);
            if (orgId == null) return new AddResponse(-1,"Поля невалидны",ResponseStatus.ERROR);
            if (collectionManager.checkExist(orgId)) return new AddResponse(-1,"Поле id дублируется",ResponseStatus.ERROR);
            requestData.getOrganization().setId(orgId);
            collectionManager.addElement(org);
            return new AddResponse(orgId, null, ResponseStatus.OK);
        }catch (Exception e){
            return  new AddResponse(-1,e.toString(),ResponseStatus.ERROR);
        }
    }

}
