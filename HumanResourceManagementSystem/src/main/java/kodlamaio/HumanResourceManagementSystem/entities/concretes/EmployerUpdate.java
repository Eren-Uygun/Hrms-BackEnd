package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employer_updates")
public class EmployerUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "website")
    private String website;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "employer_id")
    private Long employerId;


}
