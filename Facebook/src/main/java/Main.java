import dto.LentaDto;
import dto.NotificationDto;
import dto.SettingsDto;
import dto.UserMenuDto;
import model.PostList;
import model.User;
import service.*;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static User user1 = new User();
    static PostList postList111 = new PostList();
    static Scanner scanner = new Scanner(System.in);
    static PostListService postListService = new PostListService();
    static Scanner scannerInt = new Scanner(System.in);
    static int num1 = 1;
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserService();
    static NotificationService notificationService = new NotificationService();
    static FollowersService followersService = new FollowersService();

    static ChatService chatService = new ChatService();

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("1.SignIn   2.SignUp   0.Exit");
            int select = scanner.nextInt();
            if (select == 0) {
                //file ga yozish
                break;
            }
            if (select == 1) System.out.println(userService.add());
            if (select == 2) {
                User user = UserMenuDto.signIn();
                if (user != null) {
                    user1 = user;
                    userMenu(user);
                } else System.out.println();
            }
        }
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("1.Home 2.followers  3.Chat   4.Group   5.Notification(" + user.isNewCount + ")" +
                    "   5.Setting   0.back");
            int select = scanner.nextInt();
            if (select == 0) break;
            if (select == 1) {
                home();
            }
            if (select == 2) {
                addFollowers();
            }
            if (select == 3) {
                chatPersonally(user);
            }
            if (select == 4) {
                chatWithGroup(user);
            }
            if (select == 4) NotificationDto.menuDto(user.getId());
            if (select == 5) SettingsDto.menuSetting(user);
        }
    }

    private static void addFollowers() {
        int i = 9;
        while (i != 0) {
            userService.show(user1.getId());


            System.out.println("enter id for follower: (0)exit");
            i = scanner.nextInt();
            System.out.println(FollowersService.addFriend(user1.getId(), i));

        }
    }


    //========================bu yerni mubashshir yozdi========================
    private static void home() {
        int vv = 45;
        while (vv != 0) {
            System.out.println("1.myProfile, 2.showPosts");
            vv = scannerInt.nextInt();
            switch (vv) {
                case 1 -> {
                    myProFile();
                }
                case 2 -> {
                    showPostsGeneral();
                }
            }
        }
    }

    //=================================umumiy postlar uchun=============================
    private static void showPostsGeneral() {
        int i = 1;
        boolean b = false;
        int num = 1;
        while (i != 0) {
            b = FollowersService.isFriend(user1.getId(), userService.showUser().getId());

            postList111 = postListService.showGeneralPost(num, user1.getId(), b);

            if (null == postList111) {
                System.out.println("post mavjud emas");
                System.out.println("(4)next, (5)back, 0.exit");
            } else System.out.println(postList111);
            i = scannerInt.nextInt();


            switch (i) {
                case 1 -> {
                    assert postList111 != null;
                    if (!postList111.getLikeCount().contains(user1.getId())) {
                        postList111.getLikeCount().add(user1.getId());
                    } else postList111.getLikeCount().remove(user1.getId());
                }

                case 2 -> {
                    System.out.println("hali mavjud emas ");
                }
                case 3 -> {
                    assert postList111 != null;
                    User user = userService.getById(postList111.getUserId());
                    PostList postList = new PostList();
                    postList.setShareUserName(user.getName());
                    postList.setUserId(user1.getId());
                    postList.setLikeCount(new HashSet<>());
                    postList.setPostNumeric(num1++);
                    postList.setName(user1.getName());
                    postList.setPostName(postList111.getPostName());
                    postList.setWord(postList111.getWord());
                    postList.setTime(postList111.getTime());

                    System.out.println(postListService.add(postList));
                }
                case 4 -> {
                    ++num;
                }
                case 5 -> {
                    --num;
                }
                case 6 -> {
                    settingGeneralPost(num, user1.getFollower().get(postList111.getUserId()));
                }
            }
        }
    }

    private static void settingGeneralPost(int num, boolean isFrieand) {
        int v1 = 4;
        while (true) {
            PostList postList = postListService.showGeneralPost(num, user1.getId(), isFrieand);
            System.out.println(postList);

            if (postList != null)
                System.out.println("  2.delete from friendship 3.backForShow, 0.exit");
            else return;
            v1 = scannerInt.nextInt();
            switch (v1) {

                case 2 -> {
                    FollowersService.isDelete(user1.getId(), postList.getUserId());
                }
                case 3 -> {
                    showMyPost();
                }
            }
        }
    }


    //===============MY PROFILES================
    private static void myProFile() {
        int v1 = 4;

        while (v1 != 0) {
            System.out.println("1.addPost,  2.showPosts,  0.exit");
            v1 = scannerInt.nextInt();
            switch (v1) {
                case 1 -> {
                    PostList postList = new PostList();
                    PostList postList1 = LentaDto.addList(user1, postList);
                    postList1.setPostNumeric(num1++);
                    System.out.println(postListService.add(postList1));
                }
                case 2 -> {
                    showMyPost();
                }
            }
        }
    }

    private static void showMyPost() {
        int i = 1;
        int num = 1;
        while (i != 0) {

            postList111 = postListService.showGetNUm(num, user1.getId());

            if (postList111 != null) {
                System.out.println(postListService.showGetNUm(num, user1.getId()));

            } else System.out.println("post mavjud emas |(4)next, (5).back, 0.exit ");
            i = scannerInt.nextInt();


            switch (i) {
                case 1 -> {
                    assert postList111 != null;
                    if (!postList111.getLikeCount().contains(user1.getId())) {
                        postList111.getLikeCount().add(user1.getId());
                    } else postList111.getLikeCount().remove(user1.getId());
                }
                case 2 -> {
                    System.out.println("hali mavjud emas ");
                }
                case 3 -> {
                    assert postList111 != null;
                    User user = userService.getById(postList111.getUserId());
                    PostList postList = new PostList();
                    postList.setShareUserName(user.getName());
                    postList.setUserId(user1.getId());
                    postList.setLikeCount(new HashSet<>());
                    postList.setPostNumeric(num1++);
                    postList.setName(user1.getName());
                    postList.setPostName(postList111.getPostName());
                    postList.setWord(postList111.getWord());
                    postList.setTime(postList111.getTime());

                    System.out.println(postListService.add(postList));
                }
                case 4 -> {
                    ++num;
                }
                case 5 -> {
                    --num;
                }
                case 6 -> {
                    showPostGeretaion(num, user1.getId());
                }
            }
        }
    }

    private static void showPostGeretaion(int num, int id) {
        int v1 = 4;
        while (true) {
            PostList postList = postListService.showGetNUm(num, id);
            System.out.println(postList);

            if (postList != null) System.out.println(" 1.delete, 2.edit, 3.backForShow, 0.exit");
            else return;
            v1 = scannerInt.nextInt();
            switch (v1) {
                case 1 -> {
                    System.out.println(postListService.delete(postList.getId()));
                }
                case 2 -> {
                    PostList postList1 = new PostList();
                    System.out.println(postListService.edit(id, LentaDto.addList(user1, postList1)));
                }
                case 3 -> {
                    showMyPost();
                }
                case 0 -> {
                    return;
                }
            }
        }
    }

    //============================================================
    public static void chatWithGroup(User currUser) {
        int var = 10;
        while (var != 0) {
            System.out.println("1.creatGroup   2.joinPeople   3.showAllGroups");
            var = scanner.nextInt();
            switch (var) {
                case 1 -> creatGroup(currUser);
                case 2 -> joinPeople();
                case 3 -> showALlGroup(currUser);
            }
        }
    }

    public static void creatGroup(User currUser) {
        System.out.println("enter  group name");
        String groupName = scannerStr.nextLine();
        System.out.println(chatService.creatGroup(groupName, currUser));

    }

    public static void joinPeople() {
        System.out.println("enter  group name");
        String groupName = scannerStr.nextLine();
        userService.show();
        System.out.println("Enter username of friend  who you want to join:");
        String name = scannerStr.nextLine();
        User friend = userService.searchAllMemberInFaceBook(name);
        if (friend != null) {

            chatService.joinMemberToGroup(groupName, friend);
        } else {
            System.out.println("There isn't  like  person  in Facebook !!");
        }

    }

    public static void showALlGroup(User currUser) {
//        System.out.println("kim yozyapdi ?");
        chatService.showAllGroups();
        System.out.print("Select group id:");
        int son = scanner.nextInt();
        String currenGroup = chatService.showAllGroups(son);
        int var = 10;
        while (var != 0) {
            System.out.println("1.Start_chat   2.Member_OF_Group    0.Exit");
            var = scanner.nextInt();
            switch (var) {
                case 1 -> chatService.addChat_With_Friend_Or_Group(currUser, currenGroup, scannerStr);
                case 2 -> userService.showMemberOfGroup(currenGroup);
            }
        }
    }

    public static void chatPersonally(User currUser) {
        int var = 10;
        while (var != 0) {
            System.out.println("1. friendsIn_FaceBook   2.searchIn_FaceBook    0.Exit");
            var = scanner.nextInt();
            switch (var) {
                case 1 -> chatFriendsInFaceBook(currUser);
                case 2 -> searchPersonInFaceBook(currUser);
            }
        }
    }

    public static void searchPersonInFaceBook(User currUser) {
        System.out.println("Enter friends  username :");
        String username = scannerStr.nextLine();
        User user = userService.searchAllMemberInFaceBook(username);
        if (user != null)
            userService.addPersonForChat(currUser, user);
    }

    public static void chatFriendsInFaceBook(User currUser) {
        int var = 10;
        while (var != 0) {
            userService.mergePeople(currUser);
            userService.showAllChatFriends(currUser);
            //FollowersService.showFollower(currUser.getId());


            int select = scanner.nextInt();
            if (select != 0) {
                User user = userService.showAllChatFriends(select, currUser);
                if (user != null) {
                    String son = chatService.addChat_With_Friend_Or_Group(currUser, user, scannerStr);
                    var = Integer.parseInt(son);
                }
            }
        }
    }
}
