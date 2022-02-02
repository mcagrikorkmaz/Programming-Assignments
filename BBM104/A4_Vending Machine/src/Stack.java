import java.util.ArrayList;

public class Stack<T>{
    private ArrayList<T> stackList;

    public Stack(){
        this.stackList = new ArrayList<T>();
    }

    public void push(T o){
        this.stackList.add(o);
    }

    public T pop(){
        if(this.stackList.size() == 0){
            return null;
        }
        T result = this.stackList.get(stackList.size()-1);
        this.stackList.remove(stackList.size()-1);
        return result;
    }

    public int size(){
        return stackList.size();
    }

    public T get(int i){
        return stackList.get(i);
    }
}