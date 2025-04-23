package lk.ijse.gdse.project.hibernate_project.bo.custom.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.DaoFactory;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.UserDao;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.impl.UserDaoImpl;
import lk.ijse.gdse.project.hibernate_project.Dto.UserDto;

import lk.ijse.gdse.project.hibernate_project.Entity.User;
import lk.ijse.gdse.project.hibernate_project.bo.custom.UserBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UserBoImpl implements UserBo {

    public UserBoImpl() {
        this.userDao = new UserDaoImpl();
    }

    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.USER);

    @Override
    public boolean save(UserDto userDto) {
        return userDao.save(new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getRole()

        ));
    }

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        return userDao.getNextId();
    }
}
