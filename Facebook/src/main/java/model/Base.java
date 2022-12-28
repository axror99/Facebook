package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString

public abstract class Base {
    private int id;
    private String name;
    private static int genetarion=0;
    public Base() {
        this.id = genetarion++;
    }
}
