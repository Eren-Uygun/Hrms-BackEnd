package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","user"})
@Table(name = "candidates")
public class Candidate extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_identity_number",unique = true)
    private String nationalIdentityNumber;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Doğum tarihi boş geçilemez.")
    @NotBlank
    private LocalDate birthDate;

    @JsonIgnore
    @OneToOne(mappedBy = "candidate",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private CandidateActivation candidateActivation;

    @JsonIgnore
    @OneToMany(mappedBy = "candidate")
    private List<CurriculumVitae> curriculumVitaes;

    @JsonIgnore
    @OneToMany(mappedBy = "candidate")
    private List<JobAdvertisementFavorite> jobAdvertisementFavorites;


}
