package model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@ToString(callSuper = true)
@Setter
@Getter
@NoArgsConstructor
public class PostList extends Base {

    private int postNumeric=0;
    private String word;
    private String postName;
    private String shareUserName = "";
    private Set<Integer> likeCount = new HashSet<>();
    private int userId;
    private boolean isLike;
    private int size=0;
    private String time;


    @Override
    public String toString() {
        return "=============================\nFACEBOOK\n" + "[" + getName() + "] " + time + "\n" +
                shareUserName +
                "\n" + postName + "\n" +
                word + "\n\n(1)Like " + likeCount.size() + " (2)comment (3)share \n(4)next  (5)back  (6)setting";
    }
}
