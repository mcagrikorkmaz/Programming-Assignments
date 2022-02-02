//This Queue class is actually a PriorityQueue class that
// customized according to the value of the tokens.

import java.util.ArrayList;

public class Queue {
    private ArrayList<Tokens> tokensList;

    public Queue(){
        tokensList = new ArrayList<>();
    }

    // this method works according to the value of the tokens.
    public void enqueue(Tokens token){
        if(tokensList.size() == 0 || token.getValue() < tokensList.get(tokensList.size()-1).getValue()){
            tokensList.add(token);
        }
        else {
            for(int i = tokensList.size()-1; i >= 0; i--){
                if(i == 0 && (token.getValue() > tokensList.get(i).getValue())){
                    tokensList.add(0,token);
                    break;
                }
                if(token.getValue() <= tokensList.get(i).getValue()){
                    tokensList.add(i+1,token);
                    break;
                }
            }

        }
    }
    public Tokens dequeue(){
        Tokens firstToken = tokensList.get(0);
        tokensList.remove(0);
        return firstToken;
    }

    public int size(){
        return tokensList.size();
    }

    public Tokens get(int i){
        return tokensList.get(i);
    }
}