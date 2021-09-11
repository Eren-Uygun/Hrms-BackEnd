package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;

public interface UserService {
    Result login(String email,String password);

}
