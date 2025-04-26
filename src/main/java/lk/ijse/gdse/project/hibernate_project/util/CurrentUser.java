package lk.ijse.gdse.project.hibernate_project.util;

import lk.ijse.gdse.project.hibernate_project.Dto.UserDto;

import lombok.Getter;
import lombok.Setter;

public class CurrentUser {
    @Getter
    @Setter
    private static UserDto currentUser;

    public static void clearUser() {
        currentUser = null;
    }
}
