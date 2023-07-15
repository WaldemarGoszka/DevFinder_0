package pl.devfinder.business.management;


public interface Keys {

    enum CandidateState {
        ACTIVE("active"),
        INACTIVE("inactive"),
        EMPLOYED("employed"),
        EMPLOYED_BUT_SEEKING_NEXT_JOB("employed_but_seeking_next_job"),
        BANNED("banned");

        private final String state;

        CandidateState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    enum OfferState {
        ACTIVE("ACTIVE"),
        EXPIRED("EXPIRED");

        private final String name;

        OfferState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    enum ResponseState {
        ACCEPTED("accepted"),
        REJECTED("rejected"),
        PENDING("pending");

        private final String state;

        ResponseState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    enum Experience {
        JUNIOR("JUNIOR"),
        MID("MID"),
        SENIOR("SENIOR");

        private final String name;

        Experience(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    enum Role {
        CANDIDATE("CANDIDATE"),
        EMPLOYER("EMPLOYER");

        private final String name;

        Role(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    enum TokenStatus {
        INVALID("INVALID"),
        EXPIRED("EXPIRED"),
        VALID("VALID");

        private final String name;

        TokenStatus(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    enum RemoteWork {
        OFFICE("OFFICE"),
        PARTLY("PARTLY"),
        FULL("FULL");

        private final String name;

        RemoteWork(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    enum Salary {
        WITH("WITH"),
        UNDISCLOSED("UNDISCLOSED");

        private final String name;

        Salary(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    enum OfferFilterBy {
        title("title"),
        employerId("employerId"),
        companyName("companyName"),
        remoteWork("remoteWork"),
        salaryMin("salaryMin"),
        salaryMax("salaryMax"),
        salary("salary"),
        createdAt("createdAt"),
        status("status"),
        cityId("cityId"),
        cityName("cityName"),
        experienceLevel("experienceLevel"),
        offerSkills("offerSkills"),
        skillId("skillId"),
        skillName("skillName");

        private final String name;

        OfferFilterBy(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    enum CandidateFilterBy {
        title("title"),
        employerId("employerId"),
        companyName("companyName"),
        remoteWork("remoteWork"),
        salaryMin("salaryMin"),
        salaryMax("salaryMax"),
        salary("salary"),
        createdAt("createdAt"),
        status("status"),
        cityId("cityId"),
        cityName("cityName"),
        experienceLevel("experienceLevel"),
        candidateSkills("candidateSkills"),
        skillId("skillId"),
        skillName("skillName");

        private final String name;

        CandidateFilterBy(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
