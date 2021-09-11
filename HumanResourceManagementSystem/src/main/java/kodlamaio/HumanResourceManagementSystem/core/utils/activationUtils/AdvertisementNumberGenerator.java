package kodlamaio.HumanResourceManagementSystem.core.utils.activationUtils;

public class AdvertisementNumberGenerator {
    public static String generateAdvertisementNumber(){

        Double sayi =  (Math.floor(1000000 + Math.random() * 900000));
        int yeniSayi = sayi.intValue();
        String activationNumber = String.valueOf(yeniSayi);
        return activationNumber;

    }

}
