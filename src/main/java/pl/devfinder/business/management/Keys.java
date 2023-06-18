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
        OPEN("open"),
        CLOSED("closed");

        private final String state;

        OfferState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
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
        JUNIOR("Junior"),
        MID("Mid"),
        SENIOR("Senior");

        private final String level;

        Experience(String level) {
            this.level = level;
        }

        public String getLevel() {
            return level;
        }
    }
    enum Role {
        DEVELOPER("DEVELOPER"),
        EMPLOYER("EMPLOYER");

        private final String name;

        Role(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
