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

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer> {

    @Query("select j from JobAdvertisement j where j.job.jobName = ?1")
    JobAdvertisement getJobAdvertisementByJob_JobName(String jobName);

    JobAdvertisement  getJobAdvertisementByAdvertisementNumber(String advertisementNumber);

    @Query("select j from JobAdvertisement j where j.city.cityName = ?1")
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByCity_CityName(String cityName);

    @Query("select j from JobAdvertisement j where j.jobType = ?1")
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByJobType(String jobType);

    @Query("select j from JobAdvertisement j where j.workPlace = ?1")
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByWorkPlace(String workPlace);

   @Query("select j from JobAdvertisement j where j.maxSalary between ?1 and ?2 order by j.maxSalary desc")
   DataResult<List<JobAdvertisement>> getJobAdvertisementsByMaxSalaryBetweenAndMinSalary(int maxSalary, int minSalary);



    @Query("select j from JobAdvertisement j where j.isJobAdvertisementStatusActive = true ")
    List<JobAdvertisement> getJobAdvertisementsByIsJobAdvertisementStatusActive();



   List<JobAdvertisement> findJobAdvertisementsByIsJobAdvertisementStatusActiveOrderByReleaseDate(boolean status);


   @Query("select j from JobAdvertisement j where j.isJobAdvertisementStatusActive = ?1 and j.employer.id = ?2")
   List<JobAdvertisement> findJobAdvertisementsByIsJobAdvertisementStatusActiveAndEmployer_Id(boolean status,int employerId);

    @Query("select j from JobAdvertisement j where j.isJobAdvertisementStatusActive=true " +
            "and ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"+
            "or ((:#{#filter.jobTypeId}) IS NULL OR j.jobType.id IN (:#{#filter.jobTypeId})) ")
    Page<JobAdvertisement> getByFilter(@Param("filter") JobAdvertisementFilter jobAdvertisementFilter, Pageable pageable);

/*
    @Query("select j from JobAdvertisement j where j.city.id = ?1 or  j.workPlace.id = ?2 or j.job.id = ?3 or j.jobType.id = ?4 or j.isJobAdvertisementStatusActive = true ")
    Page<JobAdvertisement> getJobAdvertisementByCity_IdOrWorkPlace_IdOrJob_IdOrJobType_IdOrIsJobAdvertisementStatusActive (int city_id, int workPlace_id, int job_id, int jobType_id, Pageable pageable);


    @Query("select j from JobAdvertisement j where j.city.id = ?1 and j.isJobAdvertisementStatusActive = true ")
    Page<List<JobAdvertisement>> getJobAdvertisementsByCity_IdAndIsJobAdvertisementStatusActive(int city_Id,Pageable pageable);
*/












}
