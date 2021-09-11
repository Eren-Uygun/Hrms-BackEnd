package kodlamaio.HumanResourceManagementSystem.core.enums.userEnums;

public enum UserStatus {
    Active{
        @Override
        public String toString() {
            return "Active";
        }
    },
    Passive{
        @Override
        public String toString() {
            return "Passive";
        }
    }
}
