package pl.devfinder.business.management;


import java.util.Arrays;
import java.util.List;

public interface Keys {

    enum CandidateState {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE"),
        EMPLOYED("EMPLOYED");
//        EMPLOYED_BUT_SEEKING_NEXT_JOB("employed_but_seeking_next_job"),
//        BANNED("banned");

        private final String name;

        CandidateState(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
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
        offerId("offerId"),
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

    enum OfferSortBy {
        title("title"),
        employerId_companyName("employerId.companyName"),
        remoteWork("remoteWork"),
        salaryMin("salaryMin"),
        createdAt("createdAt"),
        status("status"),
        cityId_cityName("cityId.cityName"),
        experienceLevel("experienceLevel");

        private final String name;

        OfferSortBy(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    enum CandidateFilterBy {
        //        title("title"),
//        employerId("employerId"),
//        companyName("companyName"),
//        remoteWork("remoteWork"),
        salaryMin("salaryMin"),
//        salaryMax("salaryMax"),
//        salary("salary"),
        openToRemoteJob("openToRemoteJob"),
        yearsOfExperience("yearsOfExperience"),
        residenceCityId("residenceCityId"),
        cityName("cityName"),
        candidateId("candidateId"),

        experienceLevel("experienceLevel"),
        //        candidateSkills("candidateSkills"),
        skillId("skillId"),
        skillName("skillName"),
        status("status"),
        YES("YES"),
        NO("NO");


        private final String name;

        CandidateFilterBy(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum LIST_OF_SKILLS {
        LIST_OF_SKILLS(
                "Python",
                "JavaScript",
                "C++",
                "SQL",
                "HTML",
                "CSS",
                "PHP",
                "Ruby",
                "Swift",
                "Go",
                "Kotlin",
                "React",
                "Angular",
                "Vue.js",
                "Node.js",
                "Docker",
                "Git",
                "AWS",
                "Machine Learning",
                "Data Science",
                "Ruby on Rails",
                "ASP.NET",
                "Laravel",
                "Spring Framework",
                "Express.js",
                "MongoDB",
                "PostgreSQL",
                "Firebase",
                "Elasticsearch",
                "GraphQL",
                "AWS Lambda",
                "Azure",
                "Google Cloud Platform",
                "DevOps",
                "Scrum",
                "Kubernetes",
                "Microservices",
                "RESTful API",
                "Test Driven Development",
                "Agile Project Management",
                "Spring Boot",
                "Hibernate",
                "JavaFX",
                "JPA",
                "JUnit",
                "Maven",
                "Gradle"
        );

        private final List<String> fields;

        private LIST_OF_SKILLS(String... fields) {
            this.fields = Arrays.asList(fields);
        }

        public List<String> getFields() {
            return fields;
        }
    }

    enum EmployerSortBy {
        companyName("companyName"),
        city("city"),
        amountOfAvailableOffers("amountOfAvailableOffers"),
        numberOfEmployees("numberOfEmployees");

        private final String name;

        EmployerSortBy(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    enum EmployerFilterBy {
        skillsInOffers("skillsInOffers"),
        hasJobOffers("hasJobOffers"),
        hasNoJobOffers("hasNoJobOffers"),
        amountOfAvailableOffers("amountOfAvailableOffers"),
        city("city"),
        cityId("cityId"),
        cityName("cityName");

        private final String name;

        EmployerFilterBy(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
