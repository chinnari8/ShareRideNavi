package BackEnd;

import QueryHandlers.QueryHandler;

import java.util.HashMap;
import java.io.*;
import java.util.*;

public class Table<T>
{
    private HashMap<Integer,T> table = new HashMap<Integer,T>();
    
    public boolean add(int id,T entry)
    {
        this.table.put(id,entry);
        return true;
    }
    
    public boolean update(int id, T entry)
    {
        if(!this.table.containsKey(id)) return false;
        else {
            this.table.put(id,entry);
            return true;
        }
    }
    
    public T get(int id)
    {
        if(!this.table.containsKey(id)) return null;
        else 
        {
            return this.table.get(id);
        }
    }
    
    public ArrayList<T> getAll()
    {
        ArrayList<T> values = new ArrayList<T>();
        
        for (Map.Entry<Integer,T> entry : this.table.entrySet())
        {
            values.add(entry.getValue());
        }
        
        return values;
    }
    
    public ArrayList<T> getByQuery(QueryHandler<T> query)
    {
        ArrayList<T> values = new ArrayList<T>();
        for(Map.Entry<Integer,T> entry : this.table.entrySet())
        {
            T t = entry.getValue();
            if(query.IsQueryMatch(t)) values.add(t);
        }
        return values;
    }
    
    public boolean remove(int id)
    {
        if(!this.table.containsKey(id)) return false;
        else 
        {
            this.table.remove(id);
            return true;
        }
    }
    
    
}