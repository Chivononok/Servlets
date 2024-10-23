package enterprise.mapper;

import enterprise.dto.UserDto;
import enterprise.entity.User;

public class UserMapper {

    public User toEntity(UserDto userDto){
        User user=new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setId(userDto.getId());
        return user;
    }

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setId(user.getId());
        return userDto;
    }
}
