package kodlamaio.HumanResourceManagementSystem.business.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.Result;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Role;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.LoginDto;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.RoleAddDto;

import java.util.List;

public interface UserService {

    Result saveRole(Role role);
    void addRoleToUser(String email,String roleName);
    Result login(LoginDto loginDto);
    User getUser(String userName);
    List<User> getUsers();





}
