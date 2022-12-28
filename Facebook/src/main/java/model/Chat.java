package model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class Chat extends Base{
    private int userID;
    private String word;
    private int friedID;
}
