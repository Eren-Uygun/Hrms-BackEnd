package kodlamaio.HumanResourceManagementSystem.core.enums.activationEnums;

public enum UserActivationStatus {
    Inactivate{
        @Override
        public String toString() {
            return "Inactivate";
        }
    },
    EmailActivation{
        @Override
        public String toString() {
            return "EmailActivation";
        }
    },
    ManualActivation{
        @Override
        public String toString() {
            return "ManualActivation";
        }
    }
}
