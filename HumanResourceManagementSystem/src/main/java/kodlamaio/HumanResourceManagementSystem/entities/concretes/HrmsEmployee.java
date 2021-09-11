package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobAdvertisementActivationByEmployeeDao;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.JobAdvertisementActivation;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "hrms_employees")
public class HrmsEmployee extends User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "department_name")
    private String department;

    @JsonIgnore
    @OneToOne(mappedBy = "hrmsEmployee",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private JobAdvertisementActivationByEmployee jobAdvertisementActivationByEmployee;


}