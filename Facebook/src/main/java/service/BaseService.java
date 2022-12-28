package service;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public interface BaseService {
    boolean add();
    boolean edit(int id);
    boolean delete(int id);
    Object getById(int id);
    Map<Integer, LinkedList<User>> ListOnlyForOnePersonWithFriend=new HashMap<>();
    Map<String, ArrayList<User>> mapForGroup = new HashMap<>();
    Map<String, ArrayList<String>> listForChatWithGroup = new HashMap<>();
    Map<Integer, Map<Integer, ArrayList<String>>> listForChatWithFriend = new HashMap<>();
    Map<String, ArrayList<String>> listForTimeWithGroup = new HashMap<>();
}
