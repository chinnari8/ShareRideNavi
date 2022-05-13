package Managers;

import BackEnd.DataBase;
import Models.User;

import java.io.*;
import java.util.*;

public class UsersManager
{
    static int userCount=1;
    
    public static User getUser(int userId)
    { 
      return DataBase.getUsersTable().get(userId);
    }
    
    public static User addUser(String name, String gender, int age)
    {
        User user = new User(userCount,name,gender,age);
        if(DataBase.getUsersTable().add(user.getUserId(),user))
        {
            userCount++;
            return user;
        }
        return null;
    }
}