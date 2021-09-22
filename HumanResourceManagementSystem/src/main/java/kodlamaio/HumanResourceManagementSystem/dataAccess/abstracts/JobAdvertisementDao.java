package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import kodlamaio.HumanResourceManagementSystem.entities.dtos.JobAdvertisementFilter;
import org.apache.tomcat.jni.Local;
import org.hibernate.boot.model.relational.Database;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer> {

    JobAdvertisement getJobAdvertisementByJob_JobName(String jobName);

    JobAdvertisement  getJobAdvertisementByAdvertisementNumber(String advertisementNumber);

    DataResult<List<JobAdvertisement>> getJobAdvertisementsByCity_CityName(String cityName);

    DataResult<List<JobAdvertisement>> getJobAdvertisementsByJobType(String jobType);

    DataResult<List<JobAdvertisement>> getJobAdvertisementsByWorkPlace(String workPlace);

   // DataResult<List<JobAdvertisement>> getJobAdvertisementsByMaxSalaryBetweenAndMinSalary(int maxSalary, int minSalary);






    List<JobAdvertisement> getAllByIsJobAdvertisementStatusActive(boolean status);



   List<JobAdvertisement> findJobAdvertisementsByIsJobAdvertisementStatusActiveOrderByReleaseDate(boolean status);


   @Query("select j from JobAdvertisement j where j.isJobAdvertisementStatusActive = ?1 and j.employer.id = ?2")
   List<JobAdvertisement> findJobAdvertisementsByIsJobAdvertisementStatusActiveAndEmployer_Id(boolean status,int employerId);

    @Query("Select j from JobAdvertisement j where ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"
            +" and ((:#{#filter.jobId}) IS NULL OR j.job.id IN (:#{#filter.jobId}))"
            +" and ((:#{#filter.workPlaceId}) IS NULL OR j.workPlace.id IN (:#{#filter.workPlaceId}))"
            +" and ((:#{#filter.jobTypeId}) IS NULL OR j.jobType.id IN (:#{#filter.jobTypeId}))"
            +" and j.isJobAdvertisementStatusActive=true")
    Page<JobAdvertisement> getByFilter(@Param("filter") JobAdvertisementFilter jobAdvertisementFilter, Pageable pageable);















}
