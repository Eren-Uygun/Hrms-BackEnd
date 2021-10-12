package kodlamaio.HumanResourceManagementSystem.entities.abstracts;




import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="email_address",unique = true)
    private String email;

    @Column(name="password")
    private String password;


    @Transient
    private String passwordRepeat;

    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private Collection<Role> roles;

    {
        roles = new ArrayList<>();
    }


    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;
}

