import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        String line;

        // creating parts object according to the content of the parts.txt file.
        BufferedReader partsReader = new BufferedReader(new FileReader(args[0]));
        while((line = partsReader.readLine()) != null){
            String type = line.trim();
            Parts.createPart(new Parts(type));
        }
        partsReader.close();

        // pushing items to the stacks of the parts according to their types by reading items.txt.
        BufferedReader itemsReader = new BufferedReader(new FileReader(args[1]));
        while((line = itemsReader.readLine()) != null){
            ArrayList<String> itemArray = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
            for(Parts part: Parts.allParts){
                if(part.getPartName().equals(itemArray.get(1))){
                    part.pushItem(new Items(itemArray.get(0),itemArray.get(1)));
                }
            }
        }
        itemsReader.close();

        // creating tokens object and enqueueing tokens according to their value by reading tokens.txt.
        BufferedReader tokensReader = new BufferedReader(new FileReader(args[2]));
        while((line = tokensReader.readLine()) != null){
            ArrayList<String> tokenArray = new ArrayList<>(Arrays.asList(line.trim().split(" ")));
            Tokens.allTokens.enqueue(new Tokens(tokenArray.get(0),tokenArray.get(1),Integer.parseInt(tokenArray.get(2))));
        }
        tokensReader.close();

        // buying and putting process according to the tasks.txt file.
        BufferedReader tasksReader = new BufferedReader(new FileReader(args[3]));
        while((line = tasksReader.readLine()) != null){
            ArrayList<String> tasksArray = new ArrayList<>(Arrays.asList(line.trim().split("\t")));
            // PUT PROCESS
            if(tasksArray.get(0).equals("PUT")){
                tasksArray.remove(0);
                for(String itemInfo: tasksArray){
                    ArrayList<String> itemInfoArray = new ArrayList<>(Arrays.asList(itemInfo.split(",")));
                    for(Parts part : Parts.allParts){
                        if(part.getPartName().equals(itemInfoArray.get(0))){
                            for(int i = 1; i<= itemInfoArray.size()-1; i++){
                                part.pushItem(new Items(itemInfoArray.get(i),itemInfoArray.get(0)));
                            }
                        }
                    }
                }
            }
            // BUY PROCESS
            else{
                tasksArray.remove(0);
                for(String buyInfo: tasksArray){
                    ArrayList<String> buyInfoArray = new ArrayList<>(Arrays.asList(buyInfo.split(",")));
                    int buyCount = Integer.parseInt(buyInfoArray.get(1));
                    for(Parts part: Parts.allParts){
                        if(part.getPartName().equals(buyInfoArray.get(0))){
                            while(buyCount > 0){
                                Tokens targetToken = new Tokens();
                                ArrayList<Tokens> allTokensList = new ArrayList<>();
                                Tokens token ;
                                int count = 0;
                                int size = Tokens.allTokens.size()-1;
                                for(int i = 0; i <= size ; i++){
                                    token = Tokens.allTokens.dequeue();
                                    allTokensList.add(token);
                                    if(token.getType().equals(part.getPartName()) && count ==0){
                                        allTokensList.remove(token);
                                        count ++;
                                        targetToken = token;
                                    }
                                }
                                for(Tokens tokens : allTokensList){
                                    Tokens.allTokens.enqueue(tokens);
                                }
                                if(targetToken.getValue() > buyCount){
                                    targetToken.setValue(targetToken.getValue()-buyCount);
                                    Tokens.allTokens.enqueue(targetToken);
                                    for(int i=1 ; i <=buyCount; i++){
                                        part.popItem();
                                    }
                                    buyCount = 0;
                                }
                                else{
                                    buyCount = buyCount - targetToken.getValue();
                                    for(int i=1 ; i <=targetToken.getValue(); i++){
                                        part.popItem();
                                    }
                                    targetToken.setValue(0);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        tasksReader.close();

        // writing output.txt
        PrintWriter outWriter = new PrintWriter(new FileWriter(args[4]));
        for(Parts part : Parts.allParts){
            outWriter.println(part.getPartName()+":");
            if(part.getBoxOfPart().size() == 0){
                outWriter.println();
            }else{
            for(int i = part.getBoxOfPart().size()-1 ; i>=0 ; i--){
                outWriter.println(part.getBoxOfPart().get(i).getUniqueID());
                }
            }
            outWriter.println("---------------");
        }
        outWriter.println("Tokens Box:");
        for(int i = Tokens.allTokens.size()-1 ; i>=0 ; i--){
            outWriter.println(Tokens.allTokens.get(i).getUniqueID()+" "+Tokens.allTokens.get(i).getType()+" "+Tokens.allTokens.get(i).getValue());
        }
        outWriter.close();
    }
}