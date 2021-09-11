package kodlamaio.HumanResourceManagementSystem.core.utils.validations;

import kodlamaio.HumanResourceManagementSystem.dataAccess.abstracts.JobDao;

public class JobValidation {
    private static JobDao _jobDao;

    public static boolean isJobExists(int id){
        var tempJob = _jobDao.getById(id);
        if (tempJob != null){
            return true;
        }
        else{
            return false;
        }
    }

     public static boolean isOccupationNameExists(String occupationName){
        var tempJob = _jobDao.findByJobName(occupationName);
        if (tempJob != null){
            return true;
        }
        else {
            return false;
        }
     }

}
