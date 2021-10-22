package kodlamaio.HumanResourceManagementSystem.entities.abstracts;




import kodlamaio.HumanResourceManagementSystem.core.enums.userEnums.UserStatus;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Role;
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


    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Collection<Role> roles = new ArrayList<>();

}

