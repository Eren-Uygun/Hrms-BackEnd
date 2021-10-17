package kodlamaio.HumanResourceManagementSystem.business.concretes;

import io.jsonwebtoken.Claims;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.AuthService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.securities.filter.CustomAuthorizationFilter;
import kodlamaio.HumanResourceManagementSystem.core.securities.util.JwtUtil;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorDataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessDataResult;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.UserDao;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.security.auth.login.CredentialException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

    private final UserDao _userDao;
    private final UserService _userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder _passwordEncoder;


    @Override
    public String login(LoginDto loginDto) throws Exception {

        var user = _userDao.findByEmail(loginDto.getEmail());
        if (user.getEmail() == null){
            throw  new UsernameNotFoundException("Kullanıcı bulunamadı.");
        }else if(user.getUserStatus() != UserStatus.Active){
            throw new AuthenticationException("Onaylanmamış kullanıcı");
        }

        boolean isPasswordMatch = _passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if(!isPasswordMatch) throw new CredentialException("Geçersiz kullanıcı adı yada şifre");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(),loginDto.getPassword(),authorities));
        } catch(Exception e) {
            throw new IllegalStateException("Authentication niye burası böyle failed "+e+user.getPassword()+"||"+loginDto.getPassword());
        }

        UserDetails userDetails = _userService.loadUserByUsername(loginDto.getEmail());

        return jwtUtil.generateToken(userDetails);

    }
}
