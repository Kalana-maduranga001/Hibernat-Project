package lk.ijse.gdse.project.hibernate_project.bo.custom.impl;

import lk.ijse.gdse.project.hibernate_project.Dao.DaoFactory;
import lk.ijse.gdse.project.hibernate_project.Dao.custome.UserDao;
import lk.ijse.gdse.project.hibernate_project.Dto.UserDto;
import lk.ijse.gdse.project.hibernate_project.Entity.User;
import lk.ijse.gdse.project.hibernate_project.bo.custom.UserBo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

public class UserBoImpl implements UserBo {



    UserDao userDao = (UserDao) DaoFactory.getInstance().getDao(DaoFactory.daoType.USER);

    @Override
    public boolean save(UserDto userDto) {
        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        return userDao.save(new User(
                userDto.getId(),
                userDto.getUsername(),
                hashedPassword,
                userDto.getRole()
        ));
    }

    @Override
    public Optional<String> getNextId() throws SQLException, IOException {
        return userDao.getNextId();
    }

    @Override
    public String getNextUserID() {
        Optional<String> lastTherapistId = userDao.getLastId();

        if (lastTherapistId.isPresent()) {
            String lastID = lastTherapistId.get();
            int numericPart = Integer.parseInt(lastID.substring(1));
            numericPart++;
            return String.format("U%03d", numericPart);
        } else {
            return "U001";
        }
    }

    @Override
    public boolean updateUser(UserDto dto) {
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
        return userDao.save(new User(
                dto.getId(),
                dto.getUsername(),
                hashedPassword,
                dto.getRole()
        ));
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDao.deleteByPK(id);
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userDao.getAll();
        List<UserDto> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDto userDTO = new UserDto();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setRole(user.getRole());
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    @Override
    public UserDto getUser(String id) {
        Optional<User> byId = userDao.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            UserDto userDTO = new UserDto();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setRole(user.getRole());
            return userDTO;
        }
        return null;
    }

    @Override
    public boolean validateLogin(String username, String rawPassword) {
        Optional<User> userOpt = userDao.findByUsername(username);
        if (userOpt.isEmpty()) return false;

//        return rawPassword.equals(userOpt.get().getPassword());
        return BCrypt.checkpw(rawPassword, userOpt.get().getPassword());

    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> userOpt = userDao.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
        }
        return null;
    }
}
