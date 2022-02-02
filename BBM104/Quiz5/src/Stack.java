import java.util.ArrayList;

public class Stack<T>{
    private ArrayList<T> stackList;

    public Stack(){
        this.stackList = new ArrayList<T>();
    }

    public void push(T o){
        if(! this.isFull()) {
            this.stackList.add(o);
        }
    }

    public T pop(){
        if(this.isEmpty()){
            return null;
        }
        T result = this.top();
        this.stackList.remove(this.stackList.size()-1);
        return result;
    }

    public int size(){
        return stackList.size();
    }

    public boolean isFull(){
        return this.stackList.size() == 20;
    }

    public boolean isEmpty(){
        return this.stackList.size() == 0;
    }

    public T top(){
        return this.stackList.get(this.stackList.size()-1);
    }
}
