package Common.Models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import org.openjdk.jol.info.GraphLayout;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XStreamAlias("Organization")
public class Organization implements  Validator, Serializable {
    @Serial
    private static final long serialVersionUID = 12423456L;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private String fullName; //Строка не может быть пустой, Длина строки не должна быть больше 818, Поле не может быть null
    private OrganizationType type; //Поле не может быть null
    private Address postalAddress; //Поле может быть null
    private transient final int INSTANCE_SIZE;


    //private static int nextId = 1;

    public Organization(Integer id,String name,LocalDate creationDate,Coordinates coordinates,Integer annualTurnover, String fullName, OrganizationType type, Address postalAddress) {
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.fullName = fullName;
        this.type = type;
        this.postalAddress = postalAddress;
        this.id = id;
        this.creationDate = creationDate;
        this.INSTANCE_SIZE = (int) GraphLayout.parseInstance(this).totalSize();
    }

    /*public static int incNextId(){
         return nextId++;
    }*/
    /*public static void updateNextId(ArrayDeque<Organization> collection){
        var maxId = collection.
                stream().filter(Objects::nonNull)
                .map(Organization::getId)
                .mapToInt(Integer::intValue)
                .max().orElse(0);
        nextId = maxId+1;
    }*/


    public void setId(Integer id) {
        this.id = id;
    }
    public Organization copyCollWithId(Integer id){
        return new Organization(id,this.name,this.creationDate,this.coordinates,this.annualTurnover,this.fullName,this.type,this.postalAddress);
    }

    public  int getINSTANCE_SIZE(){
        return INSTANCE_SIZE;
    }

    public int getId() {
        return id;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Integer annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }


    @Override
    public boolean validate(){
        if( this.name == null || name.isBlank()  ){return false;}
        if (this.coordinates == null){return  false;}
        if (this.annualTurnover < 0){return  false;}
        if (this.type == null){return  false;}
        return (this.fullName != null && fullName.length()<818);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that =(Organization) o;
        return Objects.equals(getId(),
                that.getId()) && Objects.equals(getName(),
                that.getName()) && Objects.equals(getCoordinates(),
                that.getCoordinates()) && Objects.equals(getCreationDate(),
                that.getCreationDate()) && Objects.equals(getAnnualTurnover(),
                that.getAnnualTurnover()) && Objects.equals(getFullName(),
                that.getFullName()) && getType() == that.getType() && Objects.equals(getPostalAddress(),
                that.getPostalAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCoordinates(), getCreationDate(), getAnnualTurnover(), getFullName(), getType(), getPostalAddress());
    }

    @Override
    public String toString() {
        return "Organization " + getId() + " :{" +
                "name= '" + name + '\'' +
                ", creationDate= '"+creationDate+'\''+
                ", coordinates: " + coordinates +
                ", annualTurnover= " + annualTurnover +
                ", fullName= '" + fullName + '\'' +
                ", type= " + type +
                ", postalAddress= " + postalAddress +
                '}';
    }


}
