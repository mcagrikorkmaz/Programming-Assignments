import java.util.LinkedHashMap;

public class ChildActor extends Performer{
    private final String age;
    private static LinkedHashMap<String, ChildActor> childActorMap = new LinkedHashMap<>();

    private ChildActor(String uniqueID,String name,String surname,String country,String age)
    {
        super(uniqueID, name, surname, country);
        this.age = age;
    }
    //This method creates a new ChildActor object and puts it in the childActorMap as a value.
    public static void addChildActor(String uniqueID,String name,String surname,String country,String age) {
        ChildActor.childActorMap.put(uniqueID,new ChildActor(uniqueID,name,surname,country,age));
    }

    public String getAge() {
        return age;
    }

    public static LinkedHashMap<String, ChildActor> getChildActorMap() {
        return childActorMap;
    }
}