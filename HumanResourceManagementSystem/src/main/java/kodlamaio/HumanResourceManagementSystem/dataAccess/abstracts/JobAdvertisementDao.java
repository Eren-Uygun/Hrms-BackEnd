package kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts;

import kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums.JobAdvertisementStatus;
import kodlamaio.HumanResourceManagementSystem.core.utils.results.DataResult;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.Job;
import kodlamaio.HumanResourceManagementSystem.entities.concretes.JobAdvertisement;
import org.apache.tomcat.jni.Local;
import org.hibernate.boot.model.relational.Database;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Integer> {

    JobAdvertisement getJobAdvertisementByJob_JobName(String jobName);

    JobAdvertisement  getJobAdvertisementByAdvertisementNumber(String advertisementNumber);

    DataResult<List<JobAdvertisement>> getJobAdvertisementByCity(String cityName);

    DataResult<List<JobAdvertisement>> getJobAdvertisementByJobType(String jobType);

    DataResult<List<JobAdvertisement>> getJobAdvertisementByWorkPlace(String workPlace);

    @Query("from JobAdvertisement where  jobAdvertisementStatus = 'Active'")
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByJobAdvertisementStatus();


    @Query("from JobAdvertisement where jobAdvertisementStatus = 'Active' order by releaseDate desc ")
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByJobAdvertisementStatusAndReleaseDateOrderByReleaseDateDesc();

    @Query("from JobAdvertisement where jobAdvertisementStatus='Active' and maxSalary between maxSalary and minSalary")
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByMaxSalaryBetweenAndMinSalary(int maxSalary, int minSalary);

    DataResult<List<JobAdvertisement>> getJobAdvertisementsByCity_Name(String cityName);


   /*@Query("from JobAdvertisement where JobAdvertisementActivationByEmployee =:employerId and jobAdvertisementStatus = 'Active'")*/
    DataResult<List<JobAdvertisement>> getJobAdvertisementsByEmployerAndJobAdvertisementStatus(int employerId, JobAdvertisementStatus jobAdvertisementStatus);















}
