package Model;

import java.util.ArrayList;
import static javafx.scene.input.KeyCode.T;

public class LongBox<T> {
    private T t;
    private ArrayList<T> mList;
    
    public LongBox() {
        mList = new ArrayList<T>();
    }

    public void add(T t) {
       mList.add(t);
    }

    public int size() {
        return mList.size();
    }
    
    public ArrayList<T> getArray() {
        return mList;
    }
    
    public T getLastElement() {
        int lastIndex = (this.size() == 0?0 : this.size()-1);
        return mList.get(lastIndex);
    }
}
