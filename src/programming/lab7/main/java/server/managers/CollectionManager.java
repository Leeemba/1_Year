package server.managers;

import common.exceptions.IncorrectOwnerException;
import common.utility.OrganizationComparator;
import server.consoleOperations.Console;
import common.models.Organization;
import server.db.OrganizationRepo;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


// TODO: 04.10.2025 Mahoraga HELP MEEEEE!


public class CollectionManager {
    private ArrayDeque<Organization> collection = new ArrayDeque<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    private final ReentrantLock lock = new ReentrantLock();

    private Console console;
    private OrganizationRepo repo;


    public CollectionManager(Console console,OrganizationRepo repo) {
        lastInitTime = LocalDateTime.now();
        lastSaveTime = null;

        this.repo = repo;
        this.console = console;
        try {
            loadCollection();
        }catch (Exception e){
            console.printErr("ошибка загрузки из бд"+e.getCause());
            System.exit(16);
        }
    }

    private ArrayDeque<Organization> getCollection(){
        return new ArrayDeque<>(collection);
    }

    public void addElement(Organization organization)  {
        lock.lock();
        collection.add(organization);
        lock.unlock();
        lastSaveTime = LocalDateTime.now();

    }
    public int collectionSize(){
        return collection.size();
    }
    public String collectionType() {
        return collection.getClass().getName();
    }

    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }
    public Organization getFirst(){
        return collection.peekFirst();
    }
    public boolean checkExist(int id) {
        return collection.stream()
                .anyMatch((x) -> x.getId() == id);
    }
    public boolean checkCreator(int id,String login){
        var org = getById(id);
        return (org.getCreator().equals(login));
    }


    /**
     * Метод вначале создает копию из изначальной коллекции. Далее оборачивает копию в неизменяемую коллекцию
     * @return неизменяемую коллекцию-копию изначальной коллекции
     */
    public Collection<Organization> exportCollection(){
        ArrayDeque<Organization> copyOfCollection= new ArrayDeque<>(collection);
        var collAfterSort = copyOfCollection.stream().sorted(new OrganizationComparator()).collect(Collectors.toList());
        return  Collections.unmodifiableCollection(collAfterSort);
    }

    public Organization getById(int id){
        for (Organization organization:collection) {
            if(organization.getId() ==id)return organization;
        }
        return null;
    }
    public boolean editById(Integer id,Organization newObj,String login) throws IncorrectOwnerException  {
        Organization pastObj = this.getById(newObj.getId());
        if (!pastObj.getCreator().equals(newObj.getCreator())) throw new IncorrectOwnerException();
        var success = repo.update(id,newObj,login);
        if (!success) return false;
        lock.lock();
        this.removeElement(pastObj.getId(),login);
        newObj.setId(id);
        this.addElement(newObj);
        lock.unlock();
        lastSaveTime=LocalDateTime.now();
        return success;
    }

    public Map<String,Long> groupByFullName(){
        return collection.stream().
                collect(groupingBy(Organization::getFullName,counting()));
    }

    public Collection<Organization> filterByFullName(String fullName){
        return collection.stream().
                filter(s->s.getFullName().equals(fullName)).toList();
    }


    public long countGreatByAnnTur(Integer aT){
        return collection
                .stream().filter(Objects::nonNull)
                .filter(s->Integer.compare(s.getAnnualTurnover(),aT) >=1)
                .count();
    }
    public void loadCollection()  {
        lock.lock();
        collection = new ArrayDeque<>();
        var orgFromDB = repo.getAllOrganizations();

        for (Organization org:orgFromDB){
            if (checkExist(org.getId())){
                console.printErr("В базе данных есть повторение id");
            }
        this.addElement(org);
        }
        lastInitTime = LocalDateTime.now();
        lock.unlock();
    }



    public void removeAll(String login){
        lock.lock();
        collection.removeIf(organization -> organization.getCreator().equals(login));
        lastSaveTime = LocalDateTime.now();
        lock.unlock();

    }
    public boolean removeElement(int id,String login) throws IncorrectOwnerException{

        var orgtoRemove = getById(id);
        if (!orgtoRemove.getCreator().equals(login)) throw new IncorrectOwnerException();
        var isRemoved = repo.deleteById(id,login);
        if (!isRemoved) return false;
        lock.lock();
        collection.removeIf(organization -> organization.getId() == id && organization.getCreator().equals(login));
        lock.unlock();
        lastSaveTime = LocalDateTime.now();
        return true;
    }
    public boolean removeFirst(String login) throws IncorrectOwnerException{

        var orgtoRemove=getFirst();
        if (!orgtoRemove.getCreator().equals(login)) throw new IncorrectOwnerException();
        var res = removeElement(orgtoRemove.getId(),login);
        return res;
    }




    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста:(";
        var last = collection.getLast();
        StringBuilder info = new StringBuilder();


        //Iterator<Organization> it= collection.descendingIterator();
        for (Organization organization : collection) {
            info.append(organization);
            if (organization!=last) info.append(System.lineSeparator());
        }

        /*while (it.hasNext()){
            info.append(it.next());
            if(it.hasNext()) info.append(System.lineSeparator());
        }
*/

        return info.toString();
    }
}