package enterprise.dto;

import enterprise.entity.ROLE;

public class UserDto {
    private int id;
    private String login;
    private String password;
    private ROLE role;

    public UserDto(int id, String login, String password, ROLE role){
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }
    public UserDto(){this.id = -1;}

    public int getId() {return id; }

    public void setId(int id) {this.id = id;}

    public String getLogin() {return login; }
    public void setLogin(String login) {this.login = login; }
    public ROLE getRole() {return role; }
    public String getPassword() {return password; }
    public void setPassword(String password) {this.password = password; }
    public void setRole(ROLE role){this.role = role;}
}
