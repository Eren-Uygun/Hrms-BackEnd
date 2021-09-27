package kodlamaio.HumanResourceManagementSystem.core.utils.emailSender.concretes;

import kodlamaio.HumanResourceManagementSystem.core.utils.emailSender.abstracts.EmailSenderService;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderManager implements EmailSenderService {
    @Override
    public void sendMail(String email) {

        System.out.println("Aktivasyon kodunuz " + email+" adresinize gönderilmiştir.");

    }
}
