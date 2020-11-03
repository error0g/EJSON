package entity;

public class User {
    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "entity.User{" +
                "value=" + value +
                '}';
    }
}
