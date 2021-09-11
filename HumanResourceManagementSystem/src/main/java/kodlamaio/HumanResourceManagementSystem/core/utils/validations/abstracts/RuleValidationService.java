package kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts;

public interface RuleValidationService {
    boolean isMailRuleOk(String email);
    boolean isPasswordRuleOk(String password);
    boolean isEmployerMailRuleOk(String email,String website);
    boolean isPasswordMatchConfirmPassword(String password,String confirmPassword);
}
