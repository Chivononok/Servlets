package enterprise.repository;

import enterprise.entity.ROLE;
import enterprise.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static final String GET_USER_BY_ID="Select * from users where id=?";
    public static final String GET_USER_BY_LOGIN_PASSWORD="Select * from users where login=? and password=?";
    public static final String GET_USER_BY_LOGIN="Select * from users where login=?";
    public static final String GET_ALL_USERS="Select * from users";
    public static final String ADD_NEW_USER="INSERT Into users (id, login, password, role) VALUES (DEFAULT, ?, ?, ?)";
    private PostgreConnect postgreConnect;

    public UserRepository(PostgreConnect postgreConnect){
        this.postgreConnect = postgreConnect;
    }

    public User getUserById(int id) {
        User user = new User();
        Connection connection = postgreConnect.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(ROLE.getRoleByStringName(resultSet.getString("role")));
                user.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User findUserByLogin(String login){
        User user = new User();
        Connection connection = postgreConnect.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)){
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(ROLE.getRoleByStringName(resultSet.getString("role")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public User getUserByLoginPassword(String login, String password) {
        User user = new User();
        Connection connection = postgreConnect.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN_PASSWORD)){
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setPassword(resultSet.getString("password"));
                user.setLogin(resultSet.getString("login"));
                String codeRole = resultSet.getString("role");
                user.setRole(ROLE.getRoleById(Integer.parseInt(codeRole)));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public void add(User user) {
        Connection connection = postgreConnect.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_USER);){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getUserRoleCode());
            preparedStatement.execute();    //добавили запись в базу
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = postgreConnect.getConnection();

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()){
                //создаем пользователя на основе данных из таблицы
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                int id = resultSet.getInt("id");
                int roleId = resultSet.getInt("role");
                ROLE role = ROLE.getRoleById(roleId);
                User user = new User(id, login, password, role);
                users.add(user);    //добавили созданного пользователя в коллекцию ко всем пользователям
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }
}
