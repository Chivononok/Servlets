package enterprise.entity;

public enum ROLE {
    NORMAL_USER, ADMIN, ANONYMOUS;

    public static ROLE getRoleByStringName(String role){
        ROLE res = ANONYMOUS;
        switch (role){
            case "ADMIN": res = ADMIN;
                break;
            case "NORMAL_USER": res = NORMAL_USER;
                break;
            default: res = ANONYMOUS;
        }
        return res;
    }

    public static ROLE getRoleById(int id){
        ROLE res = ANONYMOUS;
        switch (id){
            case 1: res = ADMIN;
            break;
            case 2: res = NORMAL_USER;
        }
        return res;
    }
}
