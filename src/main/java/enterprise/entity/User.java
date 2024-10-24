package enterprise.entity;

public class User {

    private int id;
    private String login;
    private String password;
    private ROLE role;

    public User(int id, String login, String password, ROLE role){
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }
    public User(){
        this.id = -1;
    };
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ROLE getRole() {return role;}
    public void setRole(ROLE role) {
        this.role = role;
    }
    public int getUserRoleCode(){
        int res=0;
        switch (role){
            case ADMIN -> res = 1;
            case NORMAL_USER -> res = 2;
        }
        return res;
    }
}
