package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobs"})
@Table(name = "job_advertisements")
public class JobAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne()
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "description")
    private String description;

    @Column(name = "advertisement_number")
    private String advertisementNumber;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;
    @Column(name = "min_salary")
    private int minSalary;

    @Column(name = "max_salary")
    private int maxSalary;

    @Column(name = "open_positions")
    private int openPositions;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @PastOrPresent
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent
    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "job_advertisement_status")
    private Boolean isJobAdvertisementStatusActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workplace")
    private WorkPlace workPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_type")
    private JobType jobType;

    @JsonIgnore
    @OneToOne(mappedBy = "jobAdvertisement",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private JobAdvertisementActivationByEmployee jobAdvertisementActivationByEmployee;











}
