package kodlamaio.HumanResourceManagementSystem.core.enums.jobAdvertisementEnums;

public enum JobAdvertisementActivationStatus {
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

    },
    WaitingActivation{
        @Override
        public String toString() {
            return "Waiting Activation";
        }
    }
}
