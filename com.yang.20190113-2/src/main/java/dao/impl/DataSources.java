package dao.impl;

public class DataSources {
    private String username;
    private String password;
    private String url;
    private String driverClass;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public DataSources() {
    }

    public DataSources(String username, String password, String url, String driverClass) {
        this.username = username;
        this.password = password;

        this.url = url;
        this.driverClass = driverClass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DataSources{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", driverClass='" + driverClass + '\'' +
                '}';
    }
}
