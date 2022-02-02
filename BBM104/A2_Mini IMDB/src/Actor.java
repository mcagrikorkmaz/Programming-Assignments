import java.util.LinkedHashMap;

public class Actor extends Performer{
    private final String height;
    private static LinkedHashMap<String, Actor> actorMap = new LinkedHashMap<>();

    private Actor(String uniqueID,String name,String surname,String country,String height){
        super(uniqueID, name, surname, country);
        this.height = height;
    }
    //This method creates a new Actor object and puts it in the actorMap as a value.
    public static void addActor(String uniqueID,String name,String surname,String country,String height){
        Actor.actorMap.put(uniqueID,new Actor(uniqueID,name,surname,country,height));
    }

    public String getHeight() {
        return height;
    }

    public static LinkedHashMap<String, Actor> getActorMap() {
        return actorMap;
    }
}