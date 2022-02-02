public class Person {
    private final String name;
    private final String surname;
    private final String country;
    private final String uniqueID;

    protected Person(String uniqueID,String name,String surname,String country){
        this.name = name;
        this.surname = surname;
        this.uniqueID = uniqueID;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCountry() {
        return country;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}