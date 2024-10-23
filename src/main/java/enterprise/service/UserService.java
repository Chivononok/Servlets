package enterprise.service;

import enterprise.dto.UserDto;
import enterprise.entity.User;
import enterprise.mapper.UserMapper;
import enterprise.repository.PostgreConnect;
import enterprise.repository.UserRepository;

public class UserService {
    private PostgreConnect postgreConnect;

    public UserService(PostgreConnect postgreConnect){
        this.postgreConnect = postgreConnect;
    }

    public void addUser(UserDto userDto) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.toEntity(userDto);
        UserRepository userRepository = new UserRepository(postgreConnect);
        var userFormDb = userRepository.findUserByLogin(user.getLogin());
        if (userFormDb.getId() != -1) {
            throw new RuntimeException("Пользователь с таким логином существует " + user.getLogin());
        }
        userRepository.add(user);
    }

    public boolean isUserExist(String login, String password) throws ClassNotFoundException {
        boolean res;
        UserRepository userRepository = new UserRepository(postgreConnect);
        User user = userRepository.getUserByLoginPassword(login, password);
        res = user.getId()!=-1;
        return  res;
    }
}
