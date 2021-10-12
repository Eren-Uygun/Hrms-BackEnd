package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementFilter {

    private List<Long> cityId;
  //  private List<Integer> jobId;
 //   private List<Integer> workPlaceId;
    private List<Long> jobTypeId;


}
