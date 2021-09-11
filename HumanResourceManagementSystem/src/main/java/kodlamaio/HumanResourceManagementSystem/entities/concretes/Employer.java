package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@EqualsAndHashCode(callSuper = false)
@Table(name = "employers")
public class Employer extends User {


    @Column(name = "company_name",nullable = false,unique = true)
    private String companyName;

    @NotNull(message = "Web site boş geçilemez !")@NotBlank
    @Column(name = "website")
    private String website;

    @NotBlank@NotNull(message = "Telefon numarası alanı boş geçilemez.")
    @Column(name = "phone_number")
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
    private List<JobAdvertisement> jobAdvertisement;

    @JsonIgnore
    @OneToOne(mappedBy = "employer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private EmployerActivation employerActivation;


}
