CREATE TABLE city
(
    city_id   SERIAL      NOT NULL,
    city_name VARCHAR(64) NOT NULL,
    UNIQUE (city_name),
    PRIMARY KEY (city_id)
);

CREATE TABLE skill
(
    skill_id   SERIAL      NOT NULL,
    skill_name VARCHAR(64) NOT NULL,
    UNIQUE (skill_name),
    PRIMARY KEY (skill_id)
);

CREATE TABLE employer
(
    employer_id         SERIAL       NOT NULL,
    employer_uuid       VARCHAR(64)  NOT NULL,
    company_name        VARCHAR(255),
    email_contact       VARCHAR(128),
    phone_number        VARCHAR(255),
    description         VARCHAR(255),
    logo_file           VARCHAR(500),
    website             VARCHAR(255),
    number_of_employees INTEGER,
    created_at          TIMESTAMP    NOT NULL,
    city_id             INTEGER,
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
    experience_level    VARCHAR(16)  NOT NULL,
    years_of_experience INTEGER,
    salary_min          NUMERIC(10, 2),
    salary_max          NUMERIC(10, 2),
    created_at          TIMESTAMP    NOT NULL,
    benefits            VARCHAR(256),
    status              VARCHAR(256) NOT NULL,
    employer_id         INTEGER      NOT NULL,
    city_id             INTEGER,
    PRIMARY KEY (offer_id),
    CONSTRAINT fk_offer_city
        FOREIGN KEY (city_id) REFERENCES city (city_id),
    CONSTRAINT fk_offer_employer
        FOREIGN KEY (employer_id) REFERENCES employer (employer_id)
);

CREATE TABLE candidate
(
    candidate_id        SERIAL       NOT NULL,
    candidate_uuid      VARCHAR(64)  NOT NULL,
    first_name          VARCHAR(128),
    last_name           VARCHAR(128),
    email_contact       VARCHAR(128),
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
    candidate_skill_id SERIAL  NOT NULL,
    candidate_id       INTEGER NOT NULL,
    skill_id           INTEGER NOT NULL,
    PRIMARY KEY (candidate_skill_id),
    CONSTRAINT fk_candidate_skill_candidate
        FOREIGN KEY (candidate_id) REFERENCES candidate (candidate_id),
    CONSTRAINT fk_candidate_skill_skill
        FOREIGN KEY (skill_id) REFERENCES skill (skill_id)
);

CREATE TABLE offer_skill
(
    offer_skill_id SERIAL  NOT NULL,
    offer_id       INTEGER NOT NULL,
    skill_id       INTEGER NOT NULL,
    PRIMARY KEY (offer_skill_id),
    CONSTRAINT fk_offer_skill_offer
        FOREIGN KEY (offer_id) REFERENCES offer (offer_id),
    CONSTRAINT fk_offer_skill_skill
        FOREIGN KEY (skill_id) REFERENCES skill (skill_id)
);
CREATE TABLE devfinder_role
(
    role_id SERIAL      NOT NULL,
    role    VARCHAR(20) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE devfinder_user
(
    user_id   SERIAL       NOT NULL,
    user_name VARCHAR(32),
    user_uuid VARCHAR(64)  NOT NULL,
    email     VARCHAR(64)  NOT NULL,
    password  VARCHAR(128) NOT NULL,
    is_enabled    BOOLEAN  NOT NULL DEFAULT FALSE,
    role_id INT NOT NULL,
    UNIQUE (email),
    PRIMARY KEY (user_id),
    CONSTRAINT fk_devfinder_user_role_role
        FOREIGN KEY (role_id)
            REFERENCES devfinder_role (role_id)
);

CREATE TABLE reset_password_token (
    password_reset_token_id     SERIAL NOT NULL,
    token                       VARCHAR(255),
    expiration_time             TIMESTAMP,
    user_id                     INTEGER,
    PRIMARY KEY (password_reset_token_id),
    CONSTRAINT "fk_password_reset_token_user"
        FOREIGN KEY (user_id) REFERENCES devfinder_user (user_id) ON DELETE CASCADE
);

CREATE TABLE  email_verification_token (
    verification_token_id   SERIAL NOT NULL,
    token                   VARCHAR(255),
    expiration_time         TIMESTAMP,
    user_id                 INTEGER,
    PRIMARY KEY (verification_token_id),
    CONSTRAINT "verification_token_user_id"
        FOREIGN KEY (user_id) REFERENCES devfinder_user (user_id) ON DELETE CASCADE
);