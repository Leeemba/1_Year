package Server.Managers;

import Common.Utility.OrganizationComparator;
import Server.ConsoleOperations.Console;
import Common.Exceptions.ExitPoint;
import Common.Models.Organization;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


// TODO: 04.10.2025 Mahoraga HELP MEEEEE!


public class CollectionManager {
    private ArrayDeque<Organization> collection = new ArrayDeque<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;
    private Console console;


    public CollectionManager(Console console,FileManager fileManager) {
        lastInitTime = LocalDateTime.now();
        lastSaveTime = null;
        this.fileManager=fileManager;
        this.console = console;
    }

    private ArrayDeque<Organization> getCollection(){
        return new ArrayDeque<>(collection);
    }

    public int addElement(Organization organization){
        var maxId = collection.
                stream().filter(Objects::nonNull)
                .map(Organization::getId)
                .mapToInt(Integer::intValue)
                .max().orElse(0);
        var nextId = maxId+1;

        var element = organization.copyCollWithId(nextId);
        collection.add(element);
        return nextId;
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
    public void editById(int id, Organization newObj){
        Organization pastObj = this.getById(id);
        this.removeElement(pastObj);
        newObj.setId(id);
        this.addElement(newObj);
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
    public void loadCollection() throws ExitPoint {
        ArrayDeque<Organization> collectionFromXml= fileManager.createObject();
        //try {
            for (Organization org:collectionFromXml) {
                if(checkExist(org.getId())){
                    console.printErr("В файле повторяются \"id\"");
                    throw new ExitPoint();
                }
                this.addElement(org);
            }
        /*}catch (InvalidFormException e){
            console.printErr("Невалидные поля объекта");
        }*/
        //updateId();
        lastInitTime = LocalDateTime.now();
    }




    public void saveCollection(){
        fileManager.saveObjects(collection);
        lastSaveTime = LocalDateTime.now();
    }


    public void removeAll(){
       collection.clear();
    }
    public void removeElement(Organization organization){
        collection.remove(organization);
    }
    public void removeFirst(){
        collection.removeFirst();
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
