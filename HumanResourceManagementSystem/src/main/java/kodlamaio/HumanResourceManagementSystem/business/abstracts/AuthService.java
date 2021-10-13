package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.LoginDto;

public interface AuthService {

    String login(LoginDto loginDto) throws Exception;

}
