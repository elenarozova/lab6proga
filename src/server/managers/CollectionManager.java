package server.managers;

import data.LabWork;

import java.util.Date;
import java.util.TreeMap;

public class CollectionManager {
    TreeMap<Integer, LabWork> labWork;
    Date time;

    public CollectionManager() {
        labWork = new TreeMap<>();
        time = new Date();
    }

    public void insert(Integer id, LabWork lab) {
    labWork.put(id, lab);
    }

    public TreeMap<Integer, LabWork> getLabWork() {
    return labWork;
    }

    public Date getTime() {
    return time;
    }

    public Integer maxKey(){
    int maxKey = 0;
    for (LabWork lab:labWork.values()){
        if(lab.getId()>maxKey){
            maxKey=lab.getId();
        }
    }
    return maxKey;
    }

    public void clear(){
        labWork.clear();
    }

    public boolean contains(Integer key){
        return labWork.containsKey(key);
    }
    public void remove(Integer key){
        labWork.remove(key);
    }
    public int size(){
        return labWork.size();
    }

    public void setLabWork(TreeMap<Integer, LabWork> labWork) {
        this.labWork = labWork;
    }
}
