package before;

public class HttpRequest {
    private String ipAddress;
    private String username;
    private String password;
    private String role;

    public HttpRequest(String ipAddress, String username, String password, String role) {
        this.ipAddress = ipAddress;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
