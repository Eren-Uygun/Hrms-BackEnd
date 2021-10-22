package kodlamaio.HumanResourceManagementSystem.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementFavoriteDto {
    private Long candidateId;
    private Long jobAdvertisementId;
}
