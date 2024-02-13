package springboot.tms.backend.service;

import springboot.tms.backend.dto.JWTAuthResponse;
import springboot.tms.backend.dto.LoginDto;
import springboot.tms.backend.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    JWTAuthResponse login(LoginDto loginDto);
}
