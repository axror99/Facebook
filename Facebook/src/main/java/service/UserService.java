package service;

import dto.UserMenuDto;
import model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UserService implements BaseService {
    static ArrayList<User> users = new ArrayList<>();
    static ChatService chatService = new ChatService();

    public User login(String password, String number) {
        for (User user : users) {
            if (user != null && user.getPhoneNumber().equals(number)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public boolean add() {
        User user = new User();
        User user1 = UserMenuDto.sing_up(user);
        if (getById(user1.getId()) == null) {
            users.add(user1);
            // fileWrite(user1);
            chatService.listForChatWithFriend.put(user.getId(), new HashMap<>());
            chatService.ListOnlyForOnePersonWithFriend.put(user.getId(), new LinkedList<>());
            return true;
        }
        return false;
    }

    public void fileWrite(User user) {
        File file = new File("C:\\Users\\HP\\Documents\\Ertangi_Project2\\ForRun\\src\\main\\java\\base\\namesFile.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public boolean edit(int id) {
        User user = (User) getById(id);
        if (user != null) {
            if (user.getId() == id) {
                UserMenuDto.sing_up(user);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        User user = (User) getById(id);
        if (user != null) {
            users.remove(user);
            return true;
        }
        return false;
    }

    @Override
    public User getById(int id) {
        for (User user1 : users) {
            if (user1 != null) {
                if (user1.getId() == id) {
                    return user1;
                }
            }
        }
        return null;
    }

    public void show() {
        for (User user : users) {
            if (user != null) {
                System.out.println(user);
            }
        }
    }

    public void show(int id) {
        int s = 0;
        for (User user : users) {
            if (user != null) {
                if (user.getId() != id) {
                    if (!FollowersService.isFriend(id, user.getId())) {
                        System.out.println(user);
                        s++;
                    }
                }
            }
        }
        if (s==0) System.out.println("not persons !!! ");
    }

    public User showUser() {
        for (User user : users) {
            if (user != null) {
                return user;
            }
        }
        return null;
    }


    //search all member of facebook
    public User searchAllMemberInFaceBook(String memberName) {
        for (User currUser : users) {
            if (currUser != null) {
                if (currUser.getUserName().equals(memberName)) {
                    return currUser;
                }
            }
        }
        return null;
    }

    public User chekingUser(String username, String password) {
        for (User user : users) {
            if (user != null) {
                if (user.getUserName().equals(username)) {
                    if (user.getPassword().equals(password))
                        return user;
                }
            }
        }
        return null;
    }

    public void showMemberOfGroup(String groupName) {
        ArrayList<User> members = ChatService.mapForGroup.get(groupName);
        System.err.println("           " + groupName);
        int count = 1;
        for (User user : members) {
            if (user != null) {
                System.out.println(count + ". " + user.getName());
            }
        }
    }

    public void addPersonForChat(User curruser, User friend) {
        LinkedList<User> friendsList = ListOnlyForOnePersonWithFriend.get(curruser.getId());
        if (friendsList != null) {
            friendsList.add(friend);
            ListOnlyForOnePersonWithFriend.put(curruser.getId(), friendsList);
//            LinkedList<User> friendsList1 = ListOnlyForOnePersonWithFriend.get(curruser.getId());
//            int count = 1;
//            for (User user : friendsList1) {
//                if (user != null) {
//                    System.out.println(count + ". " + user.getName());
//                    count++;
//                }
//            }
//            count = 1;
//            for (User user : friendsList) {
//                if (user != null) {
//                    System.out.println(count + ". " + user.getName());
//                    count++;
//                }
//            }
        }
    }

    public void showAllChatFriends(User currUser) {
        //    mergePeople(currUser);
        int count = 1;
        LinkedList<User> friendsList = ListOnlyForOnePersonWithFriend.get(currUser.getId());
        for (User user : friendsList) {
            if (user != null) {
                System.out.println(count + ". " + user.getName());
                count++;
            }
        }
    }

    public User showAllChatFriends(int select, User currUser) {
        int count = 1;
        LinkedList<User> friendsList = ListOnlyForOnePersonWithFriend.get(currUser.getId());
        for (User user : friendsList) {
            if (user != null) {
                if (select == count) {
                    return user;
                }
            }
        }
        return null;
    }

    public void mergePeople(User currUser) {
        Map<Integer, Boolean> mergePeople = currUser.getFollower();

        LinkedList<User> peopleInBucket = ListOnlyForOnePersonWithFriend.get(currUser.getId());
        LinkedList<User> tempListForPeople = new LinkedList<>();
        for (User user : peopleInBucket) {
            tempListForPeople.add(user);
        }
        Set<User> tempSetForPeople1 = new HashSet<>();

        for (int id : mergePeople.keySet()) {
            tempSetForPeople1.add(users.get(id));
        }
        for (User user : tempSetForPeople1) {
            tempListForPeople.add(user);
        }
        ListOnlyForOnePersonWithFriend.put(currUser.getId(), tempListForPeople);
        return;
    }
}
