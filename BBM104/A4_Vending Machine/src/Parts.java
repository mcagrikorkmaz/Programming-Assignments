import java.util.ArrayList;

public class Parts {
    public static ArrayList<Parts> allParts = new ArrayList<>();
    private final String partName;
    private Stack<Items> boxOfPart = new Stack<>();

    public Parts(String partName){
        this.partName = partName;
    }

    public void pushItem(Items item){
        this.boxOfPart.push(item);
    }

    public void popItem(){
        this.boxOfPart.pop();
    }

    public static void createPart(Parts part){
        allParts.add(part);
    }

    public String getPartName() {
        return partName;
    }

    public Stack<Items> getBoxOfPart() {
        return boxOfPart;
    }
}