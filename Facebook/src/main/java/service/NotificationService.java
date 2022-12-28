package service;

import model.Chat;
import model.Notification;
import model.PostList;
import model.User;

import java.util.*;

import static service.ChatService.mapForGroup;

public class NotificationService {
    public static Map<String, LinkedList<User>> mapForGroupWithRequist = new HashMap<>();
    static List<Notification> notifications = new ArrayList<>();
    static List<String> groupsForUserNot = new ArrayList<>();
    UserService userService = new UserService();

    public boolean forLike(int hostUserId, PostList post, int userId) {
        Notification notification = new Notification();
        User user = userService.getById(hostUserId);
        notification.setName("like");
        notification.setUserId(hostUserId);
        String note = "Like by : " + userService.getById(userId).getName();
        user.setNew(true);
        ++user.isNewCount;
        notification.getNotification().add("{" + post);
        notification.getNotification().add(note + "}");
        notifications.add(notification);
        return true;
    }

    public boolean forComment(int hostUserId, PostList post, String cooment, int userId) {
        Notification notification = new Notification();
        User user = userService.getById(hostUserId);
        notification.setName("comment");
        notification.setUserId(hostUserId);
        user.setNew(true);
        ++user.isNewCount;
        String note = cooment + "\n by : " + userService.getById(userId).getName();
        notification.getNotification().add("{" + post);
        notification.getNotification().add(note + "}");
        notifications.add(notification);
        return true;
    }

    public boolean forPost(int hostUserId, PostList post) {
        User user = (User) userService.getById(hostUserId);
        if (user != null) {
            for (int i = 0; i < 100; i++) {
                if (user.getFollower().get(i) != null) {
                    if (user.getFollower().get(i)) {
                        User friend = (User) userService.getById(i);
                        Notification notification = new Notification();
                        notification.setName("post");
                        notification.setUserId(friend.getId());
                        friend.setNew(true);
                        ++friend.isNewCount;
                        notification.getNotification().add(post + " by: " + user.getName());
                        notifications.add(notification);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean forJoinToGroup(String groupName, User user) {
        groupsForUserNot.add(groupName);
        Notification notification = new Notification();
        notification.setName("join");
        notification.setUserId(user.getId());
        notification.setGroupName(groupName);
        user.setNew(true);
        ++user.isNewCount;
        notification.getNotification().add("Join the " + groupName + " group?");
        notifications.add(notification);
        System.out.println(notifications.size());
        return true;
    }

    public String getGroupName(int select, int userId) {
        String groupName = groupsForUserNot.get(select);
        if (groupName != null) ;
        return groupName;
    }

    public void response(Map<String, LinkedList<User>> requistMap, String groupName, Scanner scannerInt, User currUser) {

        System.err.println("       " + groupName);
        int var = 10;
        while (var != 0) {
            System.out.println("1.Yes, I agree       2.No , thanks");
            var = scannerInt.nextInt();
            switch (var) {
                case 1 -> {
                    ArrayList<User> currArray = mapForGroup.get(groupName);
                    currArray.add(currUser);
                    mapForGroup.put(groupName, currArray);
                    LinkedList<User> currList = requistMap.get(groupName);
                    currList.remove(currUser);
                    return;
                }
                case 2 -> {
                    LinkedList<User> currList = requistMap.get(groupName);
                    currList.remove(currUser);
                    return;
                }
            }
        }
    }

    public void showType(String type, int userId) {
        int count = 1;
        for (Notification not : notifications) {
            if (not != null) {
                if (not.getUserId() == userId) {
                    if (not.getName().equals(type)) {
                        for (Object o : not.getNotification()) {
                            System.out.println(count + ". " + o);
                            count++;
                            --userService.getById(userId).isNewCount;
                        }
                    }
                }
            }
        }
    }
}
