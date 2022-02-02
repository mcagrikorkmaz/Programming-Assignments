import java.util.LinkedHashMap;

public class User extends Person{
    private static LinkedHashMap<String,User> userMap = new LinkedHashMap<>();
    private LinkedHashMap<String,Integer> ratedFilms = new LinkedHashMap<>();//key film adı value puan

    private User(String uniqueID,String name,String surname,String country){
        super(uniqueID,name,surname,country);
    }
    //This method creates a new User object and puts it in the userMap as a value.
    public static void addUser(String uniqueID,String name,String surname,String country){
        User.userMap.put(uniqueID,new User(uniqueID,name,surname,country));
    }

    public static LinkedHashMap<String, User> getUserMap() {
        return userMap;
    }

    public void addRatedFilms(String filmName,int point) {
        this.ratedFilms.put(filmName, point);
    }
    public void removeRatedFilms(String filmName){
        this.ratedFilms.remove(filmName);
    }

    public LinkedHashMap<String,Integer> getRatedFilms() {
        return ratedFilms;
    }
}