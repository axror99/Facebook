package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Comment extends Base{
    private int postId;
    private int userid;
    private String comment;
}
