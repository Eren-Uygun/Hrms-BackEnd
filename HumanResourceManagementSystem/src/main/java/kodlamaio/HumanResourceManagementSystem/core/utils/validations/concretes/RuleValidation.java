package kodlamaio.HumanResourceManagementSystem.core.utils.validations.concretes;

import kodlamaio.HumanResourceManagementSystem.core.utils.validations.abstracts.RuleValidationService;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RuleValidation implements RuleValidationService {

    final String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";
    final String passwordRegex = "";


    @Override
    public boolean isMailRuleOk(String email) {
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(email);
       // return matcher.matches();
        return true;
    }

    @Override
    public boolean isPasswordRuleOk(String password) {
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher matcher = passwordPattern.matcher(password);
      //  return matcher.matches();
        return true;
    }

    @Override
    public boolean isEmployerMailRuleOk(String email, String website) {
        email.toLowerCase(Locale.ROOT);
        website.toLowerCase(Locale.ROOT);

        String[] websiteArray = website.split("www.");
        String domain = websiteArray[1];
        System.out.println(domain);

        if (email.endsWith(domain)) return true;
        return false;
    }

    @Override
    public boolean isPasswordMatchConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
