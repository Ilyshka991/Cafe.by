package bsuir.pechuro.entity;


public class User {
    private Integer id;
    private String login;
    private String role;
    private String name;
    private String surname;
    private String status;
    private Boolean account;
    private Double point;
    private Integer isMain;

    public User(Integer id, String login, String role, Integer isMain) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.isMain = isMain;
    }

    public User(Integer id, String login, String role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public User(Integer id, String login, String role, String name, String surname, String status, Boolean account) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.account = account;
    }

    public User(Integer id, String login, String role, String name, String surname, String status, Boolean account, Double point) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.account = account;
        this.point = point;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAccount() {
        return account;
    }

    public void setAccount(Boolean account) {
        this.account = account;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (status != null ? !status.equals(user.status) : user.status != null) return false;
        if (account != null ? !account.equals(user.account) : user.account != null) return false;
        if (point != null ? !point.equals(user.point) : user.point != null) return false;
        return isMain != null ? isMain.equals(user.isMain) : user.isMain == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (point != null ? point.hashCode() : 0);
        result = 31 * result + (isMain != null ? isMain.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", status='" + status + '\'' +
                ", account=" + account +
                ", point=" + point +
                ", isMain=" + isMain +
                '}';
    }
}