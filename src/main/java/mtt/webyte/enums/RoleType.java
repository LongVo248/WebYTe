package mtt.webyte.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleType {
    ROLE_USER(1),
    ROLE_DOCTOR(2),
    ROLE_ADMIN(3);

    private final int type;

    RoleType(int type) {
        this.type = type;
    }

    @JsonCreator
    public static RoleType create(int value) {
        for (RoleType v : values()) {
            if (value == v.type) {
                return v;
            }
        }
        return null;
    }

    public String toString() {
        return String.valueOf(this.type);
    }

    public int getType() {
        return type;
    }
}
