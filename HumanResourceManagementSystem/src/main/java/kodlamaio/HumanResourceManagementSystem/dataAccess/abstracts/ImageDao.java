package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.sound.sampled.DataLine;
import java.util.List;

public interface ImageDao extends JpaRepository<Image,Long> {

    List<Image> getImagesByCurriculumVitae_Id(Long id);
    Image findByCurriculumVitae_Id(Long id);

}
