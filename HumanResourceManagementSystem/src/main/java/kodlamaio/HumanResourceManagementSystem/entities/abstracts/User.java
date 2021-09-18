package kodlamaio.HumanResourceManagementSystem.entities.abstracts;




import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="email_address",unique = true)
    private String email;

    @Column(name="password")
    private String password;


    @Column(name = "password_repeat")
    private String passwordRepeat;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
}

