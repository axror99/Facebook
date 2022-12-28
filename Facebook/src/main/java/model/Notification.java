package model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter

public class Notification extends Base{
    private int userId;
    private String groupName;
    LinkedList<Object>notification=new LinkedList<>();

}
