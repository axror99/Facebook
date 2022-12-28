package service;

import model.User;

public class FollowersService {
    public static int lastUserId = 0;
    static UserService userService = new UserService();

    public static boolean addFriend(int hostUserId, int userId) {
        User user = userService.getById(hostUserId);
        if (user != null) {
            if (userId > lastUserId) lastUserId = userId;
            user.getFollower().put(userId, true);
            return true;
        }
        return false;
    }

    public static boolean isDelete(int hostUserId, int userId) {
        User user = userService.getById(hostUserId);
        if (user != null) {
            user.getFollower().put(userId, false);
            if (userId > lastUserId) lastUserId = userId;
            return true;
        }
        return false;
    }

    public static void showFollower(int hostUserId) {
        User user = userService.getById(hostUserId);
        if (user != null) {
            for (int i = 0; i < lastUserId; i++) {
                if (user.getFollower().get(i)) {
                    System.out.println(userService.getById(i).getName());
                }
            }
        }
    }
    public static boolean isFriend(int hostUserId,int userId){
        User user=userService.getById(hostUserId);
        if (user != null){
            return  (user.getFollower().containsKey(userId));
        }
        return false;
    }

}
