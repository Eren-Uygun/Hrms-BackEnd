package kodlamaio.HumanResourceManagementSystem.business.concretes;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.AuthService;
import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.core.securities.util.JwtUtil;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.ErrorDataResult;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.SuccessDataResult;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.UserDao;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.security.auth.login.CredentialException;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

    private final UserDao _userDao;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public String login(LoginDto loginDto) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var user = _userDao.findByEmail(loginDto.getEmail());
        if (user.getEmail() == null){
            throw  new UsernameNotFoundException("Kullanıcı bulunamadı.");
        }else if(user.getUserStatus() != UserStatus.Active){
            throw new AuthenticationException("Onaylanmamış kullanıcı");
        }


        boolean isPasswordMatch = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        if(!isPasswordMatch) throw new CredentialException("Geçersiz kullanıcı adı yada şifre");

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), user.getPassword(),new ArrayList<>()));
        } catch(Exception e) {
            throw new IllegalStateException("Authentication niye burası böyle failed "+e);
        }


        return  jwtUtil.generateToken(loginDto.getEmail());

    }
}
