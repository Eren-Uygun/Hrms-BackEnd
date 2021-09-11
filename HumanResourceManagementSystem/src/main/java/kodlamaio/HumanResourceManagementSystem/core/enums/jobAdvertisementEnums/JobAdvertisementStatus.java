package kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum JobAdvertisementStatus {
    Active{
        @Override
        public String toString(){
            return "Active";
        }

    },
    Passive{
        @Override
        public String toString(){
            return "Passive";
        }

    }
}
