package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work_places")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdvertisement"})
public class WorkPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "work_place")
    private String workPlace;

    @JsonIgnore
    @OneToMany(mappedBy = "workPlace")
    private List<JobAdvertisement> jobAdvertisements;
}
