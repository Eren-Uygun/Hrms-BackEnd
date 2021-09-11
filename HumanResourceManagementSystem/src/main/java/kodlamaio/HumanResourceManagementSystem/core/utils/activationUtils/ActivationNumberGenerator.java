package kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils;

import javax.persistence.Convert;

public class ActivationNumberGenerator {

    public static String generateActivationNumber(){

        Double sayi =  (Math.floor(100000 + Math.random() * 900000));
        int yeniSayi = sayi.intValue();
        String activationNumber = String.valueOf(yeniSayi);
        return activationNumber;

    }
}
