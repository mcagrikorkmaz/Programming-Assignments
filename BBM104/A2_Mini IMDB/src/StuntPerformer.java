import java.util.LinkedHashMap;

public class StuntPerformer extends Performer{
    private final String height;
    private final String realActorID;
    private static LinkedHashMap<String ,StuntPerformer> stuntPerformerMap = new LinkedHashMap<>();
    private StuntPerformer(String uniqueID,String name,String surname,String country,String height,String realActorID){
        super(uniqueID, name, surname, country);
        this.height = height;
        this.realActorID = realActorID;
    }
    //This method creates a new StuntPerformer object and puts it in the StuntPerformerMap as a value.
    public static void addStuntPerformer(String uniqueID,String name,String surname,String country,String height,String realActorID){
        StuntPerformer.stuntPerformerMap.put(uniqueID,new StuntPerformer(uniqueID,name,surname,country,height,realActorID));
    }

    public String getHeight() {
        return height;
    }

    public String getRealActorID() {
        return realActorID;
    }

    public static LinkedHashMap<String, StuntPerformer> getStuntPerformerMap() {
        return stuntPerformerMap;
    }
}