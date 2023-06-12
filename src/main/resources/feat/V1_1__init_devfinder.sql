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

DROP TABLE IF EXISTS devfinder_user_role CASCADE;
DROP TABLE IF EXISTS devfinder_user CASCADE;
DROP TABLE IF EXISTS devfinder_role CASCADE;
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
    city_name VARCHAR(64) NOT NULL,
    UNIQUE (city_name),
    PRIMARY KEY (city_id)
);

CREATE TABLE skill
(
    skill_id   SERIAL       NOT NULL,
    skill_name VARCHAR(64) NOT NULL,
    UNIQUE (skill_name),
    PRIMARY KEY (skill_id)
);

CREATE TABLE employer
(
    employer_id         SERIAL       NOT NULL,
    employer_uuid       VARCHAR(64)  NOT NULL,
    company_name        VARCHAR(255) NOT NULL,
    email_contact       VARCHAR(128) NOT NULL,
--     email               VARCHAR(255) NOT NULL,
--     password            VARCHAR(100) NOT NULL,
    phone_number        VARCHAR(255) NOT NULL,
    description         VARCHAR(255),
    logo_file           VARCHAR(500),
    website             VARCHAR(255),
--     rating              NUMERIC(2, 1),
    number_of_employees INTEGER,
    created_at          TIMESTAMP    NOT NULL,
    city_id             INTEGER,

--     UNIQUE (email),
    UNIQUE (company_name),
    PRIMARY KEY (employer_id),
    CONSTRAINT fk_employer_city
        FOREIGN KEY (city_id) REFERENCES city (city_id)
);

CREATE TABLE offer
(
    offer_id            SERIAL       NOT NULL,
    offer_uuid          VARCHAR(64)  NOT NULL,
    title               VARCHAR(256) NOT NULL,
    description         TEXT,
    other_skills        VARCHAR(256),
    remote_work         INTEGER CHECK (remote_work BETWEEN 0 AND 100),
    experience_level    VARCHAR(16) NOT NULL,
    years_of_experience INTEGER,
    salary_min          NUMERIC(10, 2),
    salary_max          NUMERIC(10, 2),
    created_at          TIMESTAMP    NOT NULL,
    expiration_date     TIMESTAMP,
    benefits            VARCHAR(256),
    status              VARCHAR(256) NOT NULL,
    employer_id         INTEGER      NOT NULL,
    city_id             INTEGER      ,


    PRIMARY KEY (offer_id),
    CONSTRAINT fk_offer_city
        FOREIGN KEY (city_id) REFERENCES city (city_id),
    CONSTRAINT fk_offer_employer
        FOREIGN KEY (employer_id) REFERENCES employer (employer_id)
);

CREATE TABLE candidate
(
    candidate_id        SERIAL       NOT NULL,
    candidate_uuid       VARCHAR(64)  NOT NULL,
    first_name          VARCHAR(128) NOT NULL,
    last_name           VARCHAR(128) NOT NULL,
    email_contact       VARCHAR(128) NOT NULL,
--     email               VARCHAR(255)    NOT NULL,
--     password            VARCHAR(255)    NOT NULL,
    phone_number        VARCHAR(32),
    created_at          TIMESTAMP    NOT NULL,
    status              VARCHAR(254) NOT NULL,
    education           VARCHAR(500),
    other_skills        VARCHAR(500),
    hobby               VARCHAR(500),
    foreign_language    VARCHAR(500),
    cv_file             VARCHAR(500),
    github_link         VARCHAR(500),
    linkedin_link       VARCHAR(500),
    picture_file        VARCHAR(500),
    experience_level    VARCHAR(16),
    years_of_experience INTEGER,
    salary_min          NUMERIC(10, 2),
    open_to_relocation  BOOLEAN,
    open_to_remote_job  BOOLEAN,
    employer_id         INTEGER,
    desired_job_city_id INTEGER,
    residence_city_id   INTEGER,

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
    candidate_skill_id SERIAL NOT NULL,
    candidate_id INTEGER NOT NULL,
    skill_id     INTEGER NOT NULL,
    PRIMARY KEY (candidate_skill_id),
    CONSTRAINT fk_candidate_skill_candidate
        FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
    CONSTRAINT fk_candidate_skill_skill
        FOREIGN KEY (skill_id) REFERENCES skill (skill_id)
);

CREATE TABLE offer_skill
(
    offer_skill_id SERIAL NOT NULL,
    offer_id INTEGER NOT NULL,
    skill_id INTEGER NOT NULL,
    PRIMARY KEY (offer_skill_id),
    CONSTRAINT fk_offer_skill_offer
        FOREIGN KEY (offer_id) REFERENCES offer (offer_id),
    CONSTRAINT fk_offer_skill_skill
        FOREIGN KEY (skill_id) REFERENCES skill (skill_id)
);

CREATE TABLE devfinder_user
(
    user_id   SERIAL       NOT NULL,
    user_name VARCHAR(32)  NOT NULL,
    email     VARCHAR(32)  NOT NULL,
    password  VARCHAR(128) NOT NULL,
    active    BOOLEAN      NOT NULL,
    UNIQUE (email),
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

-- CREATE TABLE invitation
-- (
--     invitation_id SERIAL       NOT NULL,
--     employer_id   INTEGER      NOT NULL,
--     candidate_id  INTEGER      NOT NULL,
--     offer_id      INTEGER      NOT NULL,
--     created_at    TIMESTAMP    NOT NULL,
--     status        VARCHAR(254) NOT NULL,
--     PRIMARY KEY (invitation_id),
--     UNIQUE (candidate_id, offer_id),
--     CONSTRAINT fk_invitation_employer
--         FOREIGN KEY (employer_id) REFERENCES employer (employer_id),
--     CONSTRAINT fk_invitation_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
--     CONSTRAINT _fk_invitation_offer
--         FOREIGN KEY (offer_id) REFERENCES offer (offer_id)
-- );

-- CREATE TABLE application
-- (
--     application_id SERIAL       NOT NULL,
--     created_at     TIMESTAMP    NOT NULL,
--     status         VARCHAR(255) NOT NULL,
--     candidate_id   INTEGER      NOT NULL,
--     offer_id       INTEGER      NOT NULL,
--     UNIQUE (candidate_id, offer_id),
--     PRIMARY KEY (application_id),
--     CONSTRAINT fk_application_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
--     CONSTRAINT fk_application_offer
--         FOREIGN KEY (offer_id) REFERENCES offer (offer_id)
-- );

-- CREATE TABLE employee
-- (
--     employee_id    SERIAL    NOT NULL,
--     candidate_id   INTEGER   NOT NULL,
--     offer_id       INTEGER   NOT NULL,
--     application_id INTEGER,
--     employer_id    INTEGER   NOT NULL,
--     begin_date     TIMESTAMP NOT NULL,
--     finish_date    TIMESTAMP NOT NULL,
--     approved       BOOLEAN,
--
--     PRIMARY KEY (employee_id),
--     CONSTRAINT fk_employee_candidate
--         FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
--     CONSTRAINT fk_employee_offer
--         FOREIGN KEY (offer_id) REFERENCES offer (offer_id),
--     CONSTRAINT fk_employee_application
--         FOREIGN KEY (application_id) REFERENCES application (application_id),
--     CONSTRAINT fk_employee_employer
--         FOREIGN KEY (employer_id) REFERENCES employer (employer_id)
-- );


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
---- );
--
--

--
--

--
--

--
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

--
---- Aplikacja 1
--INSERT INTO application (candidate_id, offer_id, application_date, status)
--VALUES (1, 1, '2023-05-24', 'pending');
--
---- Aplikacja 2
--INSERT INTO application (candidate_id, offer_id, application_date, status)
--VALUES (2, 2, '2023-05-24', 'pending');
--
---- Aplikacja 3
--INSERT INTO application (candidate_id, offer_id, application_date, status)
--VALUES (3, 3, '2023-05-24', 'pending');
--
---- Aplikacja 4
--INSERT INTO application (candidate_id, offer_id, application_date, status)
--VALUES (4, 4, '2023-05-24', 'pending');
--
--

