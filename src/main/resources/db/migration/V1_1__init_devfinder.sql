DROP TABLE IF EXISTS follow_employer CASCADE;
DROP TABLE IF EXISTS follow_candidate CASCADE;
DROP TABLE IF EXISTS follow_offer CASCADE;
DROP TABLE IF EXISTS message_employer CASCADE;
DROP TABLE IF EXISTS message_candidate CASCADE;
DROP TABLE IF EXISTS alert CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS application CASCADE;
DROP TABLE IF EXISTS invitation CASCADE;
DROP TABLE IF EXISTS offer_skill CASCADE;
DROP TABLE IF EXISTS candidate_skill CASCADE;
DROP TABLE IF EXISTS candidate CASCADE;
DROP TABLE IF EXISTS offer CASCADE;
DROP TABLE IF EXISTS employer CASCADE;
DROP TABLE IF EXISTS skill CASCADE;
DROP TABLE IF EXISTS city CASCADE;
DROP TABLE IF EXISTS admin CASCADE;

DROP TABLE IF EXISTS car_dealership_user_role CASCADE;
DROP TABLE IF EXISTS car_dealership_user CASCADE;
DROP TABLE IF EXISTS car_dealership_role CASCADE;
DROP TABLE IF EXISTS flyway_schema_history CASCADE;

DROP TYPE IF EXISTS my_enum_type CASCADE;
DROP TYPE IF EXISTS alert_type CASCADE;
DROP TYPE IF EXISTS experience CASCADE;
DROP TYPE IF EXISTS response_state CASCADE;
DROP TYPE IF EXISTS offer_state CASCADE;
DROP TYPE IF EXISTS candidate_state CASCADE;

-- CREATE TYPE candidate_state AS ENUM ('active', 'inactive', 'employed', 'employed_but_seeking_next_job', 'banned');
-- CREATE TYPE offer_state AS ENUM ('open', 'closed');
-- CREATE TYPE response_state AS ENUM ('accepted', 'rejected', 'pending');
-- CREATE TYPE experience AS ENUM ('Junior', 'Mid', 'Senior');
-- CREATE TYPE alert_type AS ENUM ('application_sent', 'candidate_status_changed', 'invitation_accepted', 'invitation_rejected',
--     'new_message', 'new_job_posted', 'offer_expired', 'job_invitation_received', 'hired',
--     'application_rejected', 'application_accepted');

-- CREATE TABLE admin
-- (
--     admin_id   SERIAL       NOT NULL,
--     first_name VARCHAR(255) NOT NULL,
--     last_name  VARCHAR(255) NOT NULL,
--     email      VARCHAR(255) NOT NULL,
--     password   VARCHAR(255) NOT NULL,
--     UNIQUE (email),
--     PRIMARY KEY (admin_id)
-- );


CREATE TABLE city
(
    city_id   SERIAL       NOT NULL,
    city_name VARCHAR(255) NOT NULL,
    UNIQUE (city_name),
    PRIMARY KEY (city_id)
);

CREATE TABLE skill
(
    skill_id   SERIAL       NOT NULL,
    skill_name VARCHAR(255) NOT NULL,
    UNIQUE (skill_name),
    PRIMARY KEY (skill_id)
);

CREATE TABLE employer
(
    employer_id         SERIAL       NOT NULL,
    company_name        VARCHAR(255) NOT NULL,
--     email               VARCHAR(255) NOT NULL,
--     password            VARCHAR(100) NOT NULL,
    phone_number        VARCHAR(255),
    description         VARCHAR(255),
    logo_file           VARCHAR(500),
    website             VARCHAR(255),
--     rating              NUMERIC(2, 1),
    number_of_employees INTEGER,
    city_id             INTEGER      NOT NULL,
    created_at          TIMESTAMP    NOT NULL,
--     UNIQUE (email),
    UNIQUE (company_name),
    PRIMARY KEY (employer_id),
    CONSTRAINT fk_employer_city
        FOREIGN KEY (city_id) REFERENCES city (city_id)
);

CREATE TABLE offer
(
    offer_id            SERIAL       NOT NULL,
    title               VARCHAR(255) NOT NULL,
    employer_id         INTEGER      NOT NULL,
    description         TEXT,
    project_description TEXT,
    requirements        TEXT,
    city_id             INTEGER      NOT NULL,
    remote_work         INTEGER CHECK (remote_work BETWEEN 0 AND 100),
    experience          VARCHAR(254) NOT NULL,
    years_of_experience INTEGER,
    salary_min          NUMERIC(10, 2),
    salary_max          NUMERIC(10, 2),
    created_at          TIMESTAMP    NOT NULL,
    expiration_date     TIMESTAMP,
    benefits            VARCHAR(512),
    promoted            BOOLEAN DEFAULT FALSE,
    status              VARCHAR(254) NOT NULL,
    PRIMARY KEY (offer_id),
    CONSTRAINT fk_offer_city
        FOREIGN KEY (city_id) REFERENCES city (city_id),
    CONSTRAINT fk_offer_employer
        FOREIGN KEY (employer_id) REFERENCES employer (employer_id)
);

CREATE TABLE candidate
(
    candidate_id        SERIAL       NOT NULL,
    first_name          VARCHAR(128) NOT NULL,
    last_name           VARCHAR(128) NOT NULL,
--     email               VARCHAR(255)    NOT NULL,
--     password            VARCHAR(255)    NOT NULL,
    phone_number        VARCHAR(32),
    created_at          TIMESTAMP    NOT NULL,
    status              VARCHAR(254) NOT NULL,
    education           VARCHAR(500),
    hobby               VARCHAR(500),
    foreign_language    VARCHAR(500),
    cv_file             VARCHAR(500),
    github_link         VARCHAR(500),
    linkedin_link       VARCHAR(500),
    picture_file        VARCHAR(500),
    experience          VARCHAR(254),
    years_of_experience INTEGER,
    salary_min          NUMERIC(10, 2),
    open_to_relocation  BOOLEAN,
    open_to_remote_job  BOOLEAN,
    employer_id         INTEGER,
    desired_job_city_id INTEGER,
    residence_city_id   INTEGER      NOT NULL,

--     UNIQUE (email),
    PRIMARY KEY (candidate_id),
    CONSTRAINT fk_candidate_residence_city
        FOREIGN KEY (residence_city_id) REFERENCES city (city_id),
    CONSTRAINT fk_candidate_desired_job_city
        FOREIGN KEY (desired_job_city_id) REFERENCES city (city_id),
    CONSTRAINT fk_candidate_employer
        FOREIGN KEY (employer_id) REFERENCES employer (employer_id)
);

CREATE TABLE candidate_skill
(
    candidate_id INTEGER NOT NULL,
    skill_id     INTEGER NOT NULL,
    PRIMARY KEY (candidate_id, skill_id),
    CONSTRAINT fk_candidate_skill_candidate
        FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
    CONSTRAINT fk_candidate_skill_skill
        FOREIGN KEY (skill_id) REFERENCES skill (skill_id)
);

CREATE TABLE offer_skill
(
    offer_id INTEGER NOT NULL,
    skill_id INTEGER NOT NULL,
    PRIMARY KEY (offer_id, skill_id),
    CONSTRAINT fk_offer_skill_offer
        FOREIGN KEY (offer_id) REFERENCES offer (offer_id),
    CONSTRAINT fk_offer_skill_skill
        FOREIGN KEY (skill_id) REFERENCES skill (skill_id)
);

CREATE TABLE invitation
(
    invitation_id SERIAL       NOT NULL,
    employer_id   INTEGER      NOT NULL,
    candidate_id  INTEGER      NOT NULL,
    offer_id      INTEGER      NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    status        VARCHAR(254) NOT NULL,
    PRIMARY KEY (invitation_id),
    UNIQUE (candidate_id, offer_id),
    CONSTRAINT fk_invitation_employer
        FOREIGN KEY (employer_id) REFERENCES employer (employer_id),
    CONSTRAINT fk_invitation_candidate
        FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
    CONSTRAINT _fk_invitation_offer
        FOREIGN KEY (offer_id) REFERENCES offer (offer_id)
);

CREATE TABLE application
(
    application_id SERIAL       NOT NULL,
    created_at     TIMESTAMP    NOT NULL,
    status         VARCHAR(255) NOT NULL,
    candidate_id   INTEGER      NOT NULL,
    offer_id       INTEGER      NOT NULL,
    UNIQUE (candidate_id, offer_id),
    PRIMARY KEY (application_id),
    CONSTRAINT fk_application_candidate
        FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
    CONSTRAINT fk_application_offer
        FOREIGN KEY (offer_id) REFERENCES offer (offer_id)
);

CREATE TABLE employee
(
    employee_id    SERIAL    NOT NULL,
    candidate_id   INTEGER   NOT NULL,
    offer_id       INTEGER   NOT NULL,
    application_id INTEGER,
    employer_id    INTEGER   NOT NULL,
    begin_date     TIMESTAMP NOT NULL,
    finish_date    TIMESTAMP NOT NULL,
    approved       BOOLEAN,

    PRIMARY KEY (employee_id),
    CONSTRAINT fk_employee_candidate
        FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
    CONSTRAINT fk_employee_offer
        FOREIGN KEY (offer_id) REFERENCES offer (offer_id),
    CONSTRAINT fk_employee_application
        FOREIGN KEY (application_id) REFERENCES application (application_id),
    CONSTRAINT fk_employee_employer
        FOREIGN KEY (employer_id) REFERENCES employer (employer_id)
);


CREATE TABLE devfinder_user
(
    user_id   SERIAL       NOT NULL,
    user_name VARCHAR(32)  NOT NULL,
    email     VARCHAR(32)  NOT NULL,
    password  VARCHAR(128) NOT NULL,
    active    BOOLEAN      NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE devfinder_role
(
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE devfinder_user_role
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_devfinder_user_role_user
        FOREIGN KEY (user_id)
            REFERENCES devfinder_user (user_id),
    CONSTRAINT fk_devfinder_user_role_role
        FOREIGN KEY (role_id)
            REFERENCES devfinder_role (role_id)
);

-- CREATE TABLE alert
-- (
--     alert_id       SERIAL     NOT NULL,
--     alert_type     alert_type NOT NULL,
--     message        TEXT       NOT NULL,
--     created_at     TIMESTAMP  NOT NULL,
--     is_read        BOOLEAN    NOT NULL DEFAULT FALSE,
--     employer_id    INTEGER    NOT NULL,
--     candidate_id   INTEGER    NOT NULL,
--     application_id INTEGER,
--     invitation_id  INTEGER,
--     offer_id       INTEGER,
--     PRIMARY KEY (alert_id),
--     CONSTRAINT fk_alert_offer
--         FOREIGN KEY (offer_id) REFERENCES offer (offer_id),
--     CONSTRAINT fk_alert_invitation
--         FOREIGN KEY (invitation_id) REFERENCES invitation (invitation_id),
--     CONSTRAINT fk_alert_employer
--         FOREIGN KEY (employer_id) REFERENCES employer (employer_id),
--     CONSTRAINT fk_alert_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
--     CONSTRAINT fk_alert_application
--         FOREIGN KEY (application_id) REFERENCES application (application_id)
-- );
--
-- CREATE TABLE message_candidate
-- (
--     message_id           SERIAL    NOT NULL,
--     candidate_id         INTEGER   NOT NULL,
--     employer_id          INTEGER   NOT NULL,
--     content              TEXT      NOT NULL,
--     send_at              TIMESTAMP NOT NULL,
--     is_read_candidate    BOOLEAN   NOT NULL,
--     is_read_employer     BOOLEAN   NOT NULL,
--     is_deleted_candidate BOOLEAN   NOT NULL,
--     id_deleted_employer  BOOLEAN   NOT NULL,
--     PRIMARY KEY (message_id),
--     CONSTRAINT fk_message_candidate_employer
--         FOREIGN KEY (employer_id) REFERENCES employer (employer_id),
--     CONSTRAINT fk_message_candidate_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id)
-- );
--
-- CREATE TABLE message_employer
-- (
--     message_id           SERIAL    NOT NULL,
--     employer_id          INTEGER   NOT NULL,
--     candidate_id         INTEGER   NOT NULL,
--     content              TEXT      NOT NULL,
--     send_at              TIMESTAMP NOT NULL,
--     is_read_candidate    BOOLEAN   NOT NULL,
--     is_read_employer     BOOLEAN   NOT NULL,
--     is_deleted_candidate BOOLEAN   NOT NULL,
--     id_deleted_employer  BOOLEAN   NOT NULL,
--     PRIMARY KEY (message_id),
--     CONSTRAINT fk_message_employer_employer
--         FOREIGN KEY (employer_id) REFERENCES employer (employer_id),
--     CONSTRAINT fk_message_employer_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id)
-- );
--
-- CREATE TABLE follow_offer
-- (
--     follow_id       SERIAL    NOT NULL,
--     candidate_id    INTEGER   NOT NULL,
--     offer_id        INTEGER   NOT NULL,
--     date_time_added TIMESTAMP NOT NULL,
--     UNIQUE (candidate_id, offer_id),
--     PRIMARY KEY (follow_id),
--     CONSTRAINT fk_follow_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
--     CONSTRAINT fk_follow_offer
--         FOREIGN KEY (offer_id) REFERENCES offer (offer_id)
-- );
--
-- CREATE TABLE follow_candidate
-- (
--     follow_candidate_id SERIAL          NOT NULL,
--     candidate_id        INTEGER         NOT NULL,
--     last_status         candidate_state NOT NULL,
--     date_time_added     TIMESTAMP       NOT NULL,
--     UNIQUE (candidate_id),
--     PRIMARY KEY (follow_candidate_id),
--     CONSTRAINT fk_follow_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id)
-- );
--
-- CREATE TABLE follow_employer
-- (
--     follow_employer_id SERIAL    NOT NULL,
--     candidate_id       INTEGER   NOT NULL,
--     employer_id        INTEGER   NOT NULL,
--     date_time_added    TIMESTAMP NOT NULL,
--     UNIQUE (candidate_id, employer_id),
--     PRIMARY KEY (follow_employer_id),
--     CONSTRAINT fk_follow_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
--     CONSTRAINT fk_follow_employer_employer
--         FOREIGN KEY (employer_id) REFERENCES employer (employer_id)
-- );


INSERT INTO city (city_name)
VALUES ('Warszawa');
INSERT INTO city (city_name)
VALUES ('Kraków');
INSERT INTO city (city_name)
VALUES ('Łódź');
INSERT INTO city (city_name)
VALUES ('Wrocław');
INSERT INTO city (city_name)
VALUES ('Poznań');
INSERT INTO city (city_name)
VALUES ('Gdańsk');
INSERT INTO city (city_name)
VALUES ('Szczecin');
INSERT INTO city (city_name)
VALUES ('Bydgoszcz');
INSERT INTO city (city_name)
VALUES ('Lublin');
INSERT INTO city (city_name)
VALUES ('Białystok');
INSERT INTO city (city_name)
VALUES ('Katowice');
INSERT INTO city (city_name)
VALUES ('Gdynia');
INSERT INTO city (city_name)
VALUES ('Częstochowa');
INSERT INTO city (city_name)
VALUES ('Radom');
INSERT INTO city (city_name)
VALUES ('Sosnowiec');
INSERT INTO city (city_name)
VALUES ('Toruń');
INSERT INTO city (city_name)
VALUES ('Kielce');
INSERT INTO city (city_name)
VALUES ('Gliwice');
INSERT INTO city (city_name)
VALUES ('Zabrze');
INSERT INTO city (city_name)
VALUES ('Bytom');
INSERT INTO city (city_name)
VALUES ('Olsztyn');
INSERT INTO city (city_name)
VALUES ('Rzeszów');
INSERT INTO city (city_name)
VALUES ('Bielsko-Biała');
INSERT INTO city (city_name)
VALUES ('Ruda Śląska');
INSERT INTO city (city_name)
VALUES ('Rybnik');
INSERT INTO city (city_name)
VALUES ('Tychy');
INSERT INTO city (city_name)
VALUES ('Gorzów Wielkopolski');
INSERT INTO city (city_name)
VALUES ('Dąbrowa Górnicza');
INSERT INTO city (city_name)
VALUES ('Płock');
INSERT INTO city (city_name)
VALUES ('Elbląg');
INSERT INTO city (city_name)
VALUES ('Opole');
INSERT INTO city (city_name)
VALUES ('Wałbrzych');
INSERT INTO city (city_name)
VALUES ('Zielona Góra');
INSERT INTO city (city_name)
VALUES ('Tarnów');
INSERT INTO city (city_name)
VALUES ('Chorzów');
INSERT INTO city (city_name)
VALUES ('Koszalin');
INSERT INTO city (city_name)
VALUES ('Legnica');
INSERT INTO city (city_name)
VALUES ('Kalisz');
INSERT INTO city (city_name)
VALUES ('Grudziądz');
INSERT INTO city (city_name)
VALUES ('Jaworzno');
INSERT INTO city (city_name)
VALUES ('Słupsk');
INSERT INTO city (city_name)
VALUES ('Jastrzębie-Zdrój');
INSERT INTO city (city_name)
VALUES ('Nowy Sącz');
INSERT INTO city (city_name)
VALUES ('Jelenia Góra');
INSERT INTO city (city_name)
VALUES ('Siedlce');
INSERT INTO city (city_name)
VALUES ('Mysłowice');
INSERT INTO city (city_name)
VALUES ('Konin');
INSERT INTO city (city_name)
VALUES ('Piła');
INSERT INTO city (city_name)
VALUES ('Radomsko');
INSERT INTO city (city_name)
VALUES ('Inowrocław');
INSERT INTO city (city_name)
VALUES ('Lubin');
INSERT INTO city (city_name)
VALUES ('Ostrów Wielkopolski');
INSERT INTO city (city_name)
VALUES ('Ostrowiec Świętokrzyski');
INSERT INTO city (city_name)
VALUES ('Gniezno');
INSERT INTO city (city_name)
VALUES ('Stargard');
INSERT INTO city (city_name)
VALUES ('Siemianowice Śląskie');
INSERT INTO city (city_name)
VALUES ('Piotrków Trybunalski');
INSERT INTO city (city_name)
VALUES ('Suwałki');
INSERT INTO city (city_name)
VALUES ('Głogów');
INSERT INTO city (city_name)
VALUES ('Chełm');
INSERT INTO city (city_name)
VALUES ('Racibórz');
INSERT INTO city (city_name)
VALUES ('Ełk');
INSERT INTO city (city_name)
VALUES ('Przemyśl');
INSERT INTO city (city_name)
VALUES ('Zamość');
INSERT INTO city (city_name)
VALUES ('Tarnowskie Góry');
INSERT INTO city (city_name)
VALUES ('Tomaszów Mazowiecki');
INSERT INTO city (city_name)
VALUES ('Kędzierzyn-Koźle');
INSERT INTO city (city_name)
VALUES ('Leszno');
INSERT INTO city (city_name)
VALUES ('Łomża');
INSERT INTO city (city_name)
VALUES ('Żory');
INSERT INTO city (city_name)
VALUES ('Bytów');
INSERT INTO city (city_name)
VALUES ('Wałcz');
INSERT INTO city (city_name)
VALUES ('Starachowice');
INSERT INTO city (city_name)
VALUES ('Żyrardów');
INSERT INTO city (city_name)
VALUES ('Chrzanów');
INSERT INTO city (city_name)
VALUES ('Otwock');
INSERT INTO city (city_name)
VALUES ('Sanok');
INSERT INTO city (city_name)
VALUES ('Świdnica');
INSERT INTO city (city_name)
VALUES ('Szczytno');
INSERT INTO city (city_name)
VALUES ('Zgierz');
INSERT INTO city (city_name)
VALUES ('Trzebinia');
INSERT INTO city (city_name)
VALUES ('Piekary Śląskie');
INSERT INTO city (city_name)
VALUES ('Libiąż');
INSERT INTO city (city_name)
VALUES ('Knurow');


INSERT INTO skill (skill_name)
VALUES ('Python');
INSERT INTO skill (skill_name)
VALUES ('JavaScript');
INSERT INTO skill (skill_name)
VALUES ('C++');
INSERT INTO skill (skill_name)
VALUES ('SQL');
INSERT INTO skill (skill_name)
VALUES ('HTML');
INSERT INTO skill (skill_name)
VALUES ('CSS');
INSERT INTO skill (skill_name)
VALUES ('PHP');
INSERT INTO skill (skill_name)
VALUES ('Ruby');
INSERT INTO skill (skill_name)
VALUES ('Swift');
INSERT INTO skill (skill_name)
VALUES ('Go');
INSERT INTO skill (skill_name)
VALUES ('Kotlin');
INSERT INTO skill (skill_name)
VALUES ('React');
INSERT INTO skill (skill_name)
VALUES ('Angular');
INSERT INTO skill (skill_name)
VALUES ('Vue.js');
INSERT INTO skill (skill_name)
VALUES ('Node.js');
INSERT INTO skill (skill_name)
VALUES ('Docker');
INSERT INTO skill (skill_name)
VALUES ('Git');
INSERT INTO skill (skill_name)
VALUES ('AWS');
INSERT INTO skill (skill_name)
VALUES ('Machine Learning');
INSERT INTO skill (skill_name)
VALUES ('Data Science');
INSERT INTO skill (skill_name)
VALUES ('Ruby on Rails');
INSERT INTO skill (skill_name)
VALUES ('ASP.NET');
INSERT INTO skill (skill_name)
VALUES ('Laravel');
INSERT INTO skill (skill_name)
VALUES ('Spring Framework');
INSERT INTO skill (skill_name)
VALUES ('Express.js');
INSERT INTO skill (skill_name)
VALUES ('MongoDB');
INSERT INTO skill (skill_name)
VALUES ('PostgreSQL');
INSERT INTO skill (skill_name)
VALUES ('Firebase');
INSERT INTO skill (skill_name)
VALUES ('Elasticsearch');
INSERT INTO skill (skill_name)
VALUES ('GraphQL');
INSERT INTO skill (skill_name)
VALUES ('AWS Lambda');
INSERT INTO skill (skill_name)
VALUES ('Azure');
INSERT INTO skill (skill_name)
VALUES ('Google Cloud Platform');
INSERT INTO skill (skill_name)
VALUES ('DevOps');
INSERT INTO skill (skill_name)
VALUES ('Scrum');
INSERT INTO skill (skill_name)
VALUES ('Kubernetes');
INSERT INTO skill (skill_name)
VALUES ('Microservices');
INSERT INTO skill (skill_name)
VALUES ('RESTful API');
INSERT INTO skill (skill_name)
VALUES ('Test Driven Development');
INSERT INTO skill (skill_name)
VALUES ('Agile Project Management');
INSERT INTO skill (skill_name)
VALUES ('Spring Boot');
INSERT INTO skill (skill_name)
VALUES ('Hibernate');
INSERT INTO skill (skill_name)
VALUES ('JavaFX');
INSERT INTO skill (skill_name)
VALUES ('JPA');
INSERT INTO skill (skill_name)
VALUES ('JUnit');
INSERT INTO skill (skill_name)
VALUES ('Maven');
INSERT INTO skill (skill_name)
VALUES ('Gradle');


-- Kandydat 1
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Jan', 'Kowalski', 'jan.kowalski@example.com', 'haslo123', '123456789', 1, '2023-05-01', 'Mgr inż. Informatyki',
        'Gra na gitarze', 'Angielski', 'jan_kowalski_cv.pdf', 'https://github.com/jankowalski',
        'https://linkedin.com/in/jankowalski', 'jan_kowalski_zdjecie.jpg', 'Junior', 3, 5000.00, true, false, 3);

-- Kandydat 2
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Anna', 'Nowak', 'anna.nowak@example.com', 'haslo456', '987654321', 2, '2023-05-02', 'Inż. informatyki',
        'Fotografia', 'Francuski', 'anna_nowak_cv.pdf', 'https://github.com/annanowak',
        'https://linkedin.com/in/annanowak', 'anna_nowak_zdjecie.jpg', 'Junior', 2, 4000.00, false, true, 1);

-- Kandydat 3
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Michał', 'Wiśniewski', 'michal.wisniewski@example.com', 'haslo789', '555555555', 3, '2023-05-03',
        'Inż. informatyki', 'Podróże', 'Niemiecki', 'michal_wisniewski_cv.pdf', 'https://github.com/michalwisniewski',
        'https://linkedin.com/in/michalwisniewski', 'michal_wisniewski_zdjecie.jpg', 'Junior', 4, 6000.00, true, true,
        2);

-- Kandydat 5
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Daniel', 'Lewandowski', 'daniel.lewandowski@example.com', 'hasloabc', '222222222', 4, '2023-05-04',
        'Informatyka', 'Gotowanie', 'Włoski', 'daniel_lewandowski_cv.pdf', 'https://github.com/daniellewandowski',
        'https://linkedin.com/in/daniellewandowski', 'daniel_lewandowski_zdjecie.jpg', 'Junior', 3, 5000.00, false,
        false, 3);

-- Kandydat 6
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Aleksandra', 'Kaczmarek', 'aleksandra.kaczmarek@example.com', 'haslodef', '444444444', 5, '2023-05-05',
        'Informatyka', 'Gry komputerowe', 'Chiński', 'aleksandra_kaczmarek_cv.pdf',
        'https://github.com/aleksandrakaczmarek', 'https://linkedin.com/in/aleksandrakaczmarek',
        'aleksandra_kaczmarek_zdjecie.jpg', 'Junior', 2, 4000.00, true, true, 1);

-- Kandydat 7
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Piotr', 'Jankowski', 'piotr.jankowski@example.com', 'hasloghi', '666666666', 6, '2023-05-06',
        'Inż. informatyki', 'Sport', 'Hiszpański', 'piotr_jankowski_cv.pdf', 'https://github.com/piotrjankowski',
        'https://linkedin.com/in/piotrjankowski', 'piotr_jankowski_zdjecie.jpg', 'Junior', 4, 6000.00, false, true, 2);

-- Kandydat 8
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Katarzyna', 'Wójcik', 'katarzyna.wojcik@example.com', 'haslouvw', '777777777', 7, '2023-05-07',
        'Inż. informatyki', 'Podróże', 'Angielski', 'katarzyna_wojcik_cv.pdf', 'https://github.com/katarzynawojcik',
        'https://linkedin.com/in/katarzynawojcik', 'katarzyna_wojcik_zdjecie.jpg', 'Junior', 3, 5000.00, true, false,
        3);

-- Kandydat 9
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Marcin', 'Kowalczyk', 'marcin.kowalczyk@example.com', 'hasloxzy', '888888888', 8, '2023-05-08',
        'Mgr inż. Informatyki', 'Gry planszowe', 'Niemiecki', 'marcin_kowalczyk_cv.pdf',
        'https://github.com/marcinkowalczyk', 'https://linkedin.com/in/marcinkowalczyk', 'marcin_kowalczyk_zdjecie.jpg',
        'Junior', 2, 4000.00, false, true, 1);

-- Kandydat 10
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Karolina', 'Lis', 'karolina.lis@example.com', 'hasloqwe', '999999999', 9, '2023-05-09', 'Inż. informatyki',
        'Jazda na rowerze', 'Francuski', 'karolina_lis_cv.pdf', 'https://github.com/karolinalis',
        'https://linkedin.com/in/karolinalis', 'karolina_lis_zdjecie.jpg', 'Junior', 4, 6000.00, true, true, 2);

-- Kandydat 11
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Tomasz', 'Wojciechowski', 'tomasz.wojciechowski@example.com', 'hasloasd', '101010101', 10, '2023-05-10',
        'Inż. informatyki', 'Fotografia', 'Włoski', 'tomasz_wojciechowski_cv.pdf',
        'https://github.com/tomaszwojciechowski', 'https://linkedin.com/in/tomaszwojciechowski',
        'tomasz_wojciechowski_zdjecie.jpg', 'Junior', 3, 5000.00, false, false, 3);

-- Kandydat 12
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Monika', 'Wróblewska', 'monika.wroblewska@example.com', 'haslomnb', '111111111', 11, '2023-05-11',
        'Inż. informatyki', 'Podróże', 'Hiszpański', 'monika_wroblewska_cv.pdf', 'https://github.com/monikawroblewska',
        'https://linkedin.com/in/monikawroblewska', 'monika_wroblewska_zdjecie.jpg', 'Mid', 2, 4000.00, true, true, 1);

-- Kandydat 13
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Marek', 'Laskowski', 'marek.laskowski@example.com', 'haslopou', '121212121', 12, '2023-05-12',
        'Mgr inż. Informatyki', 'Gra na gitarze', 'Angielski', 'marek_laskowski_cv.pdf',
        'https://github.com/mareklaskowski', 'https://linkedin.com/in/mareklaskowski', 'marek_laskowski_zdjecie.jpg',
        'Mid',
        4, 6000.00, false, true, 2);

-- Kandydat 14
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Małgorzata', 'Kowal', 'malgorzata.kowal@example.com', 'haslozxc', '131313131', 13, '2023-05-13',
        'Inż. informatyki', 'Sport', 'Niemiecki', 'malgorzata_kowal_cv.pdf', 'https://github.com/malgorzatakowal',
        'https://linkedin.com/in/malgorzatakowal', 'malgorzata_kowal_zdjecie.jpg', 'Mid', 3, 5000.00, true, false, 3);

-- Kandydat 15
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Grzegorz', 'Dudek', 'grzegorz.dudek@example.com', 'hasloqaz', '141414141', 14, '2023-05-14',
        'Inż. informatyki', 'Podróże', 'Francuski', 'grzegorz_dudek_cv.pdf', 'https://github.com/grzegorzdudek',
        'https://linkedin.com/in/grzegorzdudek', 'grzegorz_dudek_zdjecie.jpg', 'Mid', 2, 4000.00, false, true, 1);

-- Kandydat 16
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Joanna', 'Jasińska', 'joanna.jasinska@example.com', 'hasloqwe', '151515151', 15, '2023-05-15', 'Informatyka',
        'Fotografia', 'Włoski', 'joanna_jasinska_cv.pdf', 'https://github.com/joannajasinska',
        'https://linkedin.com/in/joannajasinska', 'joanna_jasinska_zdjecie.jpg', 'Mid', 4, 6000.00, true, true, 2);

-- Kandydat 17
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Adam', 'Kaczorowski', 'adam.kaczorowski@example.com', 'haslodfg', '161616161', 16, '2023-05-16',
        'Inż. informatyki', 'Podróże', 'Hiszpański', 'adam_kaczorowski_cv.pdf', 'https://github.com/adamkaczorowski',
        'https://linkedin.com/in/adamkaczorowski', 'adam_kaczorowski_zdjecie.jpg', 'Mid', 3, 5000.00, false, false, 3);

-- Kandydat 18
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Magdalena', 'Pawlak', 'magdalena.pawlak@example.com', 'haslojkl', '171717171', 17, '2023-05-17',
        'Inż. informatyki', 'Gry planszowe', 'Angielski', 'magdalena_pawlak_cv.pdf',
        'https://github.com/magdalenapawlak', 'https://linkedin.com/in/magdalenapawlak', 'magdalena_pawlak_zdjecie.jpg',
        'Mid', 2, 4000.00, true, true, 1);

-- Kandydat 19
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Krzysztof', 'Zawadzki', 'krzysztof.zawadzki@example.com', 'haslolkj', '181818181', 18, '2023-05-18',
        'Informatyka', 'Sport', 'Niemiecki', 'krzysztof_zawadzki_cv.pdf', 'https://github.com/krzysztofzawadzki',
        'https://linkedin.com/in/krzysztofzawadzki', 'krzysztof_zawadzki_zdjecie.jpg', 'Mid', 4, 6000.00, false, true,
        2);

-- Kandydat 20
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Iwona', 'Jaworska', 'iwona.jaworska@example.com', 'haslopwe', '191919191', 19, '2023-05-19',
        'Inż. informatyki', 'Podróże', 'Francuski', 'iwona_jaworska_cv.pdf', 'https://github.com/iwonajaworska',
        'https://linkedin.com/in/iwonajaworska', 'iwona_jaworska_zdjecie.jpg', 'Mid', 3, 5000.00, true, false, 3);

-- Kandydat 21
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Michał', 'Witkowski', 'michal.witkowski@example.com', 'haslozxc', '202020202', 20, '2023-05-20',
        'Inż. informatyki', 'Gra na gitarze', 'Włoski', 'michal_witkowski_cv.pdf', 'https://github.com/michalwitkowski',
        'https://linkedin.com/in/michalwitkowski', 'michal_witkowski_zdjecie.jpg', 'Senior', 2, 4000.00, false, true,
        1);

-- Kandydat 22
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Anna', 'Nowak', 'anna.nowakk@example.com', 'hasloqwe', '212121212', 1, '2023-05-21', 'Informatyka',
        'Fotografia', 'Hiszpański', 'anna_nowak_cv.pdf', 'https://github.com/annanowak',
        'https://linkedin.com/in/annanowak', 'anna_nowak_zdjecie.jpg', 'Senior', 4, 6000.00, true, true, 2);

-- Kandydat 23
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Paweł', 'Dąbrowski', 'pawel.dabrowski@example.com', 'hasloasd', '222222222', 2, '2023-05-22',
        'Inż. informatyki', 'Podróże', 'Angielski', 'pawel_dabrowski_cv.pdf', 'https://github.com/paweldabrowski',
        'https://linkedin.com/in/paweldabrowski', 'pawel_dabrowski_zdjecie.jpg', 'Senior', 3, 5000.00, false, false, 3);

-- Kandydat 24
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Ewa', 'Kwiatkowska', 'ewa.kwiatkowska@example.com', 'hasloouy', '232323232', 3, '2023-05-23', 'Informatyka',
        'Sport', 'Niemiecki', 'ewa_kwiatkowska_cv.pdf', 'https://github.com/ewakwiatkowska',
        'https://linkedin.com/in/ewakwiatkowska', 'ewa_kwiatkowska_zdjecie.jpg', 'Senior', 2, 4000.00, true, true, 1);

-- Kandydat 25
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Jan', 'Piotrowski', 'jan.piotrowski@example.com', 'haslolki', '242424242', 4, '2023-05-24',
        'Mgr inż. Informatyki', 'Gry planszowe', 'Francuski', 'jan_piotrowski_cv.pdf',
        'https://github.com/janpiotrowski', 'https://linkedin.com/in/janpiotrowski', 'jan_piotrowski_zdjecie.jpg',
        'Senior', 4,
        6000.00, false, true, 2);

-- Kandydat 26
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Katarzyna', 'Zając', 'katarzyna.zajac@example.com', 'haslopoi', '252525252', 5, '2023-05-25',
        'Inż. informatyki', 'Podróże', 'Hiszpański', 'katarzyna_zajac_cv.pdf', 'https://github.com/katarzynazajac',
        'https://linkedin.com/in/katarzynazajac', 'katarzyna_zajac_zdjecie.jpg', 'Senior', 3, 5000.00, true, false, 3);

-- Kandydat 27
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Andrzej', 'Kowalczyk', 'andrzej.kowalczyk@example.com', 'haslomyt', '262626262', 6, '2023-05-26',
        'Inż. informatyki', 'Fotografia', 'Włoski', 'andrzej_kowalczyk_cv.pdf', 'https://github.com/andrzejkowalczyk',
        'https://linkedin.com/in/andrzejkowalczyk', 'andrzej_kowalczyk_zdjecie.jpg', 'Senior', 2, 4000.00, false, true,
        1);

-- Kandydat 28
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Karolina', 'Kaczmarek', 'karolina.kaczmarek@example.com', 'hasloqaz', '272727272', 7, '2023-05-27',
        'Inż. informatyki', 'Podróże', 'Angielski', 'karolina_kaczmarek_cv.pdf', 'https://github.com/karolinakaczmarek',
        'https://linkedin.com/in/karolinakaczmarek', 'karolina_kaczmarek_zdjecie.jpg', 'Senior', 4, 6000.00, true, true,
        2);

-- Kandydat 29
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Robert', 'Mazur', 'robert.mazur@example.com', 'hasloqwe', '282828282', 8, '2023-05-28', 'Informatyka',
        'Gra na gitarze', 'Niemiecki', 'robert_mazur_cv.pdf', 'https://github.com/robertmazur',
        'https://linkedin.com/in/robertmazur', 'robert_mazur_zdjecie.jpg', 'Senior', 3, 5000.00, false, false, 3);

-- Kandydat 30
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Marta', 'Górka', 'marta.gorka@example.com', 'haslozxc', '292929292', 9, '2023-05-29', 'Informatyka', 'Sport',
        'Francuski', 'marta_gorka_cv.pdf', 'https://github.com/martagorka', 'https://linkedin.com/in/martagorka',
        'marta_gorka_zdjecie.jpg', 'Senior', 2, 4000.00, true, true, 1);

-- Kandydat 31
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Tomasz', 'Wójcik', 'tomasz.wojcik@example.com', 'haslopoi', '303030303', 10, '2023-05-30', 'Inż. informatyki',
        'Podróże', 'Hiszpański', 'tomasz_wojcik_cv.pdf', 'https://github.com/tomaszwojcik',
        'https://linkedin.com/in/tomaszwojcik', 'tomasz_wojcik_zdjecie.jpg', 'Senior', 4, 6000.00, false, true, 2);

-- Kandydat 32
INSERT INTO candidate (first_name, last_name, email, password, phone_number, residence_city_id, registration_date,
                       education, hobby, foreign_language, cv_file, github_link, linkedin_link, picture_file,
                       experience, years_of_experience, salary_min, open_to_relocation, open_to_remote_job,
                       desired_job_city_id)
VALUES ('Monika', 'Krawczyk', 'monika.krawczyk@example.com', 'haslolkj', '313131313', 11, '2023-05-31',
        'Inż. informatyki', 'Fotografia', 'Angielski', 'monika_krawczyk_cv.pdf', 'https://github.com/monikakrawczyk',
        'https://linkedin.com/in/monikakrawczyk', 'monika_krawczyk_zdjecie.jpg', 'Senior', 3, 5000.00, true, false, 3);


-- Pracodawca 1
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('Firma XYZ', 'xyz@example.com', 'password123', '111111111', 'Opis firmy XYZ', 1);

-- Pracodawca 2
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('ABC Company', 'abc@example.com', 'password456', '222222222', 'Opis firmy ABC', 2);

-- Pracodawca 3
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('Nowa Firma', 'nowafirma@example.com', 'haslonowa', '333333333', 'Opis nowej firmy', 3);

-- Pracodawca 4
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('Innowacyjne Rozwiązania', 'info@innovative.com', 'pass1234', '444444444', 'Opis firmy Innovative Solutions',
        4);

-- Pracodawca 5
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('Tech Corp', 'techcorp@example.com', 'pass5678', '555555555', 'Opis firmy Tech Corp', 5);

-- Pracodawca 6
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('Super Firma', 'super@firma.com', 'superpass', '666666666', 'Opis super firmy', 1);

-- Pracodawca 7
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('Nowy Biznes', 'biznes@nowy.pl', 'haslo123', '777777777', 'Opis nowego biznesu', 2);

-- Pracodawca 8
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('IT Solutions', 'info@itsolutions.com', 'pass5678', '888888888', 'Opis firmy IT Solutions', 3);

-- Pracodawca 9
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('Dynamiczne Przedsiębiorstwo', 'kontakt@dynamic.com', 'dynamicpass', '999999999', 'Opis dynamicznej firmy', 4);

-- Pracodawca 10
INSERT INTO employer (company_name, email, password, phone_number, description, city_id)
VALUES ('Quick Tech', 'info@quicktech.com', 'quickpass', '101010101', 'Opis firmy Quick Tech', 5);


-- Oferta 1
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Programista Java', 1, 'Opis stanowiska programisty Java', 'Projektowanie i rozwijanie aplikacji w Javie', 1,
        'Senior',
        5000.00, 8000.00, '2023-05-24', '2023-06-30');

-- Oferta 2
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Front-end Developer', 3, 'Opis stanowiska Front-end Developera', 'Tworzenie responsywnych stron internetowych',
        3, 'Senior', 4000.00, 6000.00, '2023-05-24', '2023-06-30');

-- Oferta 3
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Full-stack Developer', 2, 'Opis stanowiska Full-stack Developera',
        'Rozwój aplikacji webowych od front-endu do back-endu', 2, 'Senior', 6000.00, 9000.00, '2023-05-24',
        '2023-06-30');

-- Oferta 4
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Java Software Engineer', 4, 'Opis stanowiska Java Software Engineera', 'Tworzenie oprogramowania w Javie', 4,
        'Mid', 5500.00, 8500.00, '2023-05-24', '2023-06-30');

-- Oferta 5
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Backend Developer', 5, 'Opis stanowiska Backend Developera',
        'Tworzenie i utrzymanie serwerowej części aplikacji', 5, 'Mid', 5000.00, 8000.00, '2023-05-24', '2023-06-30');

-- Oferta 6
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Programista C#', 1, 'Opis stanowiska programisty C#', 'Tworzenie aplikacji w technologii C#', 1, 'Mid',
        4500.00,
        7000.00, '2023-05-24', '2023-06-30');

-- Oferta 7
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('QA Engineer', 3, 'Opis stanowiska QA Engineera', 'Testowanie i analiza jakości oprogramowania', 3, 'Junior',
        4000.00,
        6000.00, '2023-05-24', '2023-06-30');

-- Oferta 8
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Software Architect', 2, 'Opis stanowiska Software Architekta',
        'Projektowanie i nadzór nad architekturą oprogramowania', 2, 'Senior', 7000.00, 10000.00, '2023-05-24',
        '2023-06-30');

-- Oferta 9
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Junior Java Developer', 4, 'Opis stanowiska Junior Java Developera', 'Wsparcie w tworzeniu aplikacji w Javie',
        4, 'Junior', 3500.00, 5000.00, '2023-05-24', '2023-06-30');

-- Oferta 10
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Software Tester', 5, 'Opis stanowiska Software Testera', 'Testowanie oprogramowania i raportowanie błędów', 5,
        'Junior', 3500.00, 5500.00, '2023-05-24', '2023-06-30');

-- Oferta 11
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Python Developer', 1, 'Opis stanowiska Python Developera', 'Tworzenie aplikacji w języku Python', 1, 'Mid',
        4500.00, 7000.00, '2023-05-24', '2023-06-30');

-- Oferta 12
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Mobile App Developer', 3, 'Opis stanowiska Mobile App Developera',
        'Tworzenie mobilnych aplikacji dla systemów iOS i Android', 3, 'Mid', 5000.00, 8000.00, '2023-05-24',
        '2023-06-30');

-- Oferta 13
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Web Designer', 2, 'Opis stanowiska Web Designera',
        'Tworzenie atrakcyjnych i responsywnych interfejsów użytkownika', 2, 'Junior', 4000.00, 6000.00, '2023-05-24',
        '2023-06-30');

-- Oferta 14
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('DevOps Engineer', 4, 'Opis stanowiska DevOps Engineera',
        'Wdrażanie narzędzi i procesów wspierających wytwarzanie oprogramowania', 4, 'Senior', 6000.00, 9000.00,
        '2023-05-24',
        '2023-06-30');

-- Oferta 15
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Backend Python Developer', 5, 'Opis stanowiska Backend Python Developera',
        'Rozwój serwerowej części aplikacji w języku Python', 5, 'Mid', 5500.00, 8500.00, '2023-05-24', '2023-06-30');

-- Oferta 16
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('PHP Developer', 1, 'Opis stanowiska PHP Developera', 'Tworzenie aplikacji w języku PHP', 1, 'Mid', 4500.00,
        7000.00, '2023-05-24', '2023-06-30');

-- Oferta 17
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Software Engineer', 3, 'Opis stanowiska Software Engineera', 'Tworzenie oprogramowania zgodnie z wymaganiami',
        3, 'Senior', 6000.00, 9000.00, '2023-05-24', '2023-06-30');

-- Oferta 18
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Android Developer', 2, 'Opis stanowiska Android Developera',
        'Tworzenie aplikacji mobilnych dla systemu Android', 2, 'Mid', 5000.00, 8000.00, '2023-05-24', '2023-06-30');

-- Oferta 19
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Frontend JavaScript Developer', 4, 'Opis stanowiska Frontend JavaScript Developera',
        'Rozwój interfejsów użytkownika w JavaScript', 4, 'Mid', 5000.00, 8000.00, '2023-05-24', '2023-06-30');

-- Oferta 20
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience, salary_min, salary_max,
                   publication_date, expiration_date)
VALUES ('Data Scientist', 5, 'Opis stanowiska Data Scientista', 'Analiza danych i tworzenie modeli predykcyjnych', 5,
        'Senior',
        7000.00, 10000.00, '2023-05-24', '2023-06-30');


-- Aplikacja 1
INSERT INTO application (candidate_id, offer_id, application_date, status)
VALUES (1, 1, '2023-05-24', 'pending');

-- Aplikacja 2
INSERT INTO application (candidate_id, offer_id, application_date, status)
VALUES (2, 2, '2023-05-24', 'pending');

-- Aplikacja 3
INSERT INTO application (candidate_id, offer_id, application_date, status)
VALUES (3, 3, '2023-05-24', 'pending');

-- Aplikacja 4
INSERT INTO application (candidate_id, offer_id, application_date, status)
VALUES (4, 4, '2023-05-24', 'pending');


-- Candidate 1
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (1, 2),
       (1, 6),
       (1, 11),
       (1, 16),
       (1, 20),
       (1, 25),
       (1, 30);

-- Candidate 2
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (2, 3),
       (2, 7),
       (2, 12),
       (2, 17),
       (2, 21);

-- Candidate 3
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (3, 4),
       (3, 8),
       (3, 13),
       (3, 18),
       (3, 22),
       (3, 26),
       (3, 31),
       (3, 36),
       (3, 40),
       (3, 45);

-- Candidate 4
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (4, 5),
       (4, 9),
       (4, 14),
       (4, 19),
       (4, 23),
       (4, 27),
       (4, 32),
       (4, 37);

-- Candidate 5
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (5, 6),
       (5, 10),
       (5, 15),
       (5, 20),
       (5, 24),
       (5, 28),
       (5, 33),
       (5, 38),
       (5, 41),
       (5, 46);

-- Candidate 6
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (6, 7),
       (6, 11),
       (6, 16),
       (6, 21),
       (6, 25),
       (6, 29),
       (6, 34),
       (6, 39),
       (6, 42);

-- Candidate 7
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (7, 8),
       (7, 12),
       (7, 17),
       (7, 22),
       (7, 26),
       (7, 30),
       (7, 35),
       (7, 40),
       (7, 43);

-- Candidate 8
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (8, 9),
       (8, 13),
       (8, 18),
       (8, 23),
       (8, 27),
       (8, 31),
       (8, 36),
       (8, 41),
       (8, 44);

-- Candidate 9
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (9, 10),
       (9, 14),
       (9, 19),
       (9, 24),
       (9, 28),
       (9, 32),
       (9, 37),
       (9, 42),
       (9, 45);
-- Candidate 10
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (10, 11),
       (10, 15),
       (10, 20),
       (10, 25),
       (10, 30),
       (10, 35),
       (10, 40);

-- Candidate 11
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (11, 12),
       (11, 16),
       (11, 21),
       (11, 26),
       (11, 31),
       (11, 36),
       (11, 41),
       (11, 46);

-- Candidate 12
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (12, 13),
       (12, 17),
       (12, 22),
       (12, 27),
       (12, 32),
       (12, 37),
       (12, 42),
       (12, 45);

-- Candidate 13
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (13, 14),
       (13, 18),
       (13, 23),
       (13, 28),
       (13, 33),
       (13, 38),
       (13, 43);

-- Candidate 14
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (14, 15),
       (14, 19),
       (14, 24),
       (14, 29),
       (14, 34),
       (14, 39),
       (14, 44);

-- Candidate 15
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (15, 16),
       (15, 20),
       (15, 25),
       (15, 30),
       (15, 35),
       (15, 40),
       (15, 45);

-- Candidate 16
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (16, 17),
       (16, 21),
       (16, 26),
       (16, 31),
       (16, 36),
       (16, 41),
       (16, 46);

-- Candidate 17
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (17, 18),
       (17, 22),
       (17, 27),
       (17, 32),
       (17, 37),
       (17, 42),
       (17, 45);

-- Candidate 18
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (18, 19),
       (18, 23),
       (18, 28),
       (18, 33),
       (18, 38),
       (18, 43);

-- Candidate 19
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (19, 20),
       (19, 24),
       (19, 29),
       (19, 34),
       (19, 39),
       (19, 44);

-- Candidate 20
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (20, 21),
       (20, 25),
       (20, 30),
       (20, 35),
       (20, 40),
       (20, 45);

-- Candidate 21
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (21, 22),
       (21, 26),
       (21, 31),
       (21, 36),
       (21, 41),
       (21, 46);

-- Candidate 22
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (22, 23),
       (22, 27),
       (22, 32),
       (22, 37),
       (22, 42),
       (22, 45);

-- Candidate 23
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (23, 24),
       (23, 28),
       (23, 33),
       (23, 38),
       (23, 43);

-- Candidate 24
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (24, 25),
       (24, 29),
       (24, 34),
       (24, 39),
       (24, 44);

-- Candidate 25
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (25, 26),
       (25, 30),
       (25, 35),
       (25, 40),
       (25, 45);

-- Candidate 26
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (26, 27),
       (26, 31),
       (26, 36),
       (26, 41),
       (26, 46);

-- Candidate 27
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (27, 28),
       (27, 32),
       (27, 37),
       (27, 42),
       (27, 45);

-- Candidate 28
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (28, 29),
       (28, 33),
       (28, 38),
       (28, 43);

-- Candidate 29
INSERT INTO candidate_skill (candidate_id, skill_id)
VALUES (29, 30),
       (29, 34),
       (29, 39),
       (29, 44);
