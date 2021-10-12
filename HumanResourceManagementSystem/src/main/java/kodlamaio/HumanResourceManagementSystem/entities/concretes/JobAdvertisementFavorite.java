package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_advertisement_favorites")
public class JobAdvertisementFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_advertisement_favorite_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne()
    @JoinColumn(name = "job_advertisement_id")
    private JobAdvertisement jobAdvertisement;



}
