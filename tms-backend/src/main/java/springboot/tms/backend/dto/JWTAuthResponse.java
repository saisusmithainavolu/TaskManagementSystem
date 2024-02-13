package springboot.tms.backend.dto;

public class JWTAuthResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String role;

    public JWTAuthResponse() {
    }

    public JWTAuthResponse(String accessToken, String tokenType, String role) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.role = role;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
