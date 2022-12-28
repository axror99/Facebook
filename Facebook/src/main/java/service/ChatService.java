package service;

import model.Notification;
import model.User;

import java.text.SimpleDateFormat;
import java.util.*;

public class ChatService implements BaseService {
    UserService userService = new UserService();
    NotificationService notificationService = new NotificationService();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

    public boolean creatGroup(String groupName,User currUser) {
        // hozirgi holda ichida  odam bolmaydi
        ArrayList<User> groupsMember = new ArrayList<>();
        if (!mapForGroup.containsKey(groupName)) {
            groupsMember.add(currUser);
            mapForGroup.put(groupName, groupsMember);
            listForChatWithGroup.put(groupName, new ArrayList<>());
            listForTimeWithGroup.put(groupName, new ArrayList<>());
            NotificationService.mapForGroupWithRequist.put(groupName,new LinkedList<>());

            return true;
        }
        return false;
    }

    // grouppaga  azo  qo'shish
    public void joinMemberToGroup(String groupName, User memberName) {
        ArrayList<User> groupsMember = new ArrayList<>();
        if (groupsMember != null) {
            if (mapForGroup.containsKey(groupName)) {
                groupsMember = mapForGroup.get(groupName);
                if (isHaveThisPerson(memberName.getName(), groupsMember)) {
                    notificationService.forJoinToGroup(groupName, memberName);
                    LinkedList<User> requistFriend = NotificationService.mapForGroupWithRequist.get(groupName);
                    requistFriend.add(memberName);
                    NotificationService.mapForGroupWithRequist.put(groupName,requistFriend);

//                    groupsMember.add(memberName);
//                    mapForGroup.put(groupName, groupsMember);
                }
            }
        } else {
            System.out.println("Siz  qo'shmoqchi  bo'lgan azo topilmagan !!!");
        }
    }

    public boolean isHaveThisPerson(String personName, ArrayList<User> arrayList) {
        for (User user : arrayList) {
            if (user != null) {
                if (user.getName().equals(personName)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showAllGroups() {
        int count = 1;
        for (String groupName : mapForGroup.keySet()) {
            if (groupName != null) {
                System.out.println(count + ". " + groupName);
                count++;
            }
        }
    }

    public String showAllGroups(int select) {
        int count = 1;
        for (String groupName : mapForGroup.keySet()) {
            if (groupName != null) {
                if (count == select) {
                    return groupName;
                }
                count++;
            }
        }
        return "";
    }

    public void addChat_With_Friend_Or_Group(User currUser, String groupName, Scanner scannerStr) {
        if (listForChatWithGroup.containsKey(groupName)) {
            ArrayList<String> arrayList = listForChatWithGroup.get(groupName);
            System.err.println("          " + groupName);
            for (String s : arrayList) {
                if (s != null) {
                    String name = s.substring(2, currUser.getName().length() + 2);
                    String time = s.substring(s.length() - 8);
                    if (currUser.getName().equals(name)) {
                        String exchangeString = s.substring((5 + currUser.getName().length()), s.length() - (currUser.getName().length() + 7));
                        exchangeString = exchangeString + "[ " + currUser.getName() + " ]" + time;
                        System.out.printf("%60s %n", exchangeString);
                    } else {
                        System.out.printf("%-60s %n", s);
                    }

                }
            }
            String sentance = "";
            while (true) {
                sentance = scannerStr.nextLine();
                if (sentance.equals("0")) {
                    break;
                }
                arrayList.add("[ " + currUser.getName() + " ]" + sentance + " (" + simpleDateFormat.format(new Date()) + ")");
            }
            listForChatWithGroup.put(groupName, arrayList);
        }
    }


    public String addChat_With_Friend_Or_Group(User currUser, User currUser2, Scanner scannerStr) {
        if (listForChatWithFriend.containsKey(currUser.getId()) && listForChatWithFriend.containsKey(currUser2.getId())) {
            Map<Integer, ArrayList<String>> currMap = listForChatWithFriend.get(currUser.getId());
            Map<Integer, ArrayList<String>> currMap2 = listForChatWithFriend.get(currUser2.getId());
            if (!currMap.containsKey(currUser2.getId())) {
                currMap.put(currUser2.getId(), new ArrayList<>());
            }
            if (!currMap2.containsKey(currUser.getId())) {
                currMap2.put(currUser.getId(), new ArrayList<>());
            }
            ArrayList<String> currArr = currMap.get(currUser2.getId());
            ArrayList<String> currArr2 = currMap2.get(currUser.getId());

            System.err.println("          " + currUser2.getName());

            for (String s : currArr) {
                if (s != null) {
                    String name = s.substring(2, currUser2.getName().length() + 2);
                    if (name.equals(currUser2.getName())) {
                        System.out.printf("%-55s %n", s);
                    } else {
                        System.out.printf("%55s %n", s);
                    }
                }
            }
            String sentance = "";
            while (true) {
                sentance = scannerStr.nextLine();
                if (sentance.equals("0")) {
                    return "0";
                }
                currArr.add((sentance + ":[ " + currUser.getName() + " ]" + " (" + simpleDateFormat.format(new Date()) + ")"));
                currArr2.add("[ " + currUser.getName() + " ]:" + sentance + " (" + simpleDateFormat.format(new Date()) + ")");
               LinkedList<User> linkedList= ListOnlyForOnePersonWithFriend.get(currUser2.getId());
              boolean isHave=true;
               for (User user: linkedList){
                   if (user!= null)
                   {
                       if (user==currUser) {
                           isHave=false;
                       }
                   }
               }
               if (isHave){
                   linkedList.add(currUser);
                   ListOnlyForOnePersonWithFriend.put(currUser2.getId(),linkedList);
               }
            }
        }
        return "0";
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean edit(int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Object getById(int id) {
        return null;
    }
}