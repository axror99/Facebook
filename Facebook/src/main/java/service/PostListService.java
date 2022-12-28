package service;

import model.PostList;

import java.util.Stack;

public class PostListService implements BaseService {
    Stack<PostList> postLists = new Stack<>();

    public PostList getById(int id) {
        for (PostList postList : postLists) {
            if (postList != null) {
                if (postList.getId() == id) {
                    return postList;
                }
            }
        }
        return null;
    }

    public PostList getByNumeric(int num) {
        for (PostList postList : postLists) {
            if (postList != null) {
                if (postList.getPostNumeric() == num) {
                    return postList;
                }
            }
        }
        return null;
    }

    public boolean add(Object o) {
        if (o != null) {
            postLists.add((PostList) o);
            return true;
        }
        return false;
    }


    public PostList showGetNUm(int num, int id) {
        for (PostList postList : postLists) {
            if (postList != null) {
                if (num == postList.getPostNumeric()) {
                    if (id == postList.getUserId())
                        return postList;
                }
            }
        }
        return null;
    }

    public PostList showGeneralPost(int num,int userId, boolean isfreand) {
        for (PostList postList : postLists) {
            if (postList != null) {
                if (num == postList.getPostNumeric()) {
                    if (isfreand || postList.getUserId()==userId) {
                        return postList;
                    }
                }
            }
        }
        return null;
    }

    public boolean edit(int id, PostList postList) {
        if (getById(id) != null) {
            postLists.remove(getById(id));
            postLists.add(postList);
            return true;
        }
        return false;
    }

    @Override
    public boolean add() {
        return false;
    }

    @Override
    public boolean edit(int id) {
        return false;
    }

    public boolean delete(int id) {
        if (getById(id) != null) {
            postLists.remove(getById(id));
            return true;
        }
        return false;
    }

}
