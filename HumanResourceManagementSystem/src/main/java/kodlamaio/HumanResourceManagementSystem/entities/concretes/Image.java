package kodlamaio.HumanResourceManagementSystem.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","curriculumVitae"})
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_id")
    private String imageId;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="curriculumVitae_id")
    private CurriculumVitae curriculumVitae;


}
