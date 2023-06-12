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
    company_name        VARCHAR(255) NOT NULL,
    email_contact       VARCHAR(128) NOT NULL,
    phone_number        VARCHAR(255) NOT NULL,
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
    expiration_date     TIMESTAMP,
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
    first_name          VARCHAR(128) NOT NULL,
    last_name           VARCHAR(128) NOT NULL,
    email_contact       VARCHAR(128) NOT NULL,
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

CREATE TABLE devfinder_user
(
    user_id   SERIAL       NOT NULL,
    user_name VARCHAR(32)  NOT NULL,
    user_uuid VARCHAR(64)  NOT NULL,
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

-- Oferta 1
INSERT INTO offer (title, employer_id, description, project_description, city_id, experience_level, salary_min, salary_max,
                   created_at, expiration_date)
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



'jan.kowalski@example.com', 'haslo123',
'anna.nowak@example.com', 'haslo456',
'michal.wisniewski@example.com', 'haslo789',
'daniel.lewandowski@example.com', 'hasloabc',
'aleksandra.kaczmarek@example.com', 'haslodef',
'piotr.jankowski@example.com', 'hasloghi',
'katarzyna.wojcik@example.com', 'haslouvw',
'marcin.kowalczyk@example.com', 'hasloxzy',
'karolina.lis@example.com', 'hasloqwe',
'tomasz.wojciechowski@example.com', 'hasloasd',
'monika.wroblewska@example.com', 'haslomnb',
'marek.laskowski@example.com', 'haslopou',
'malgorzata.kowal@example.com', 'haslozxc',
'grzegorz.dudek@example.com', 'hasloqaz',
'joanna.jasinska@example.com', 'hasloqwe',
'adam.kaczorowski@example.com', 'haslodfg',
'magdalena.pawlak@example.com', 'haslojkl',
'krzysztof.zawadzki@example.com', 'haslolkj',
'iwona.jaworska@example.com', 'haslopwe',
'michal.witkowski@example.com', 'haslozxc',
'anna.nowakk@example.com', 'hasloqwe',
'pawel.dabrowski@example.com', 'hasloasd',
'ewa.kwiatkowska@example.com', 'hasloouy',
'jan.piotrowski@example.com', 'haslolki',
'katarzyna.zajac@example.com', 'haslopoi',
'andrzej.kowalczyk@example.com', 'haslomyt',
'karolina.kaczmarek@example.com', 'hasloqaz',
'robert.mazur@example.com', 'hasloqwe',
'marta.gorka@example.com', 'haslozxc',
'tomasz.wojcik@example.com', 'haslopoi',
'monika.krawczyk@example.com', 'haslolkj',

1.  5e8dd047-7b07-44fb-bbd6-1f15db0f6a8c
2.  9c6d5a77-7250-4d4e-b0c9-af65fdd1c3b3
3.  7f7b1e1b-8c5c-4c71-bc3a-1485dfcc7ac4
4.  2d6c9a0d-350e-4dbb-a3c0-ef334d8fcd34
5.  9a2e1a2d-0a45-44be-b214-d4a9d26e54b5
6.  6f2ea2fb-f1bf-4fe7-bf63-d431f5e68776
7.  e3b0c442-98fc-1c14-9af3-d0f07aa18bbf
8.  a3d9e614-5d57-4596-a6d8-c1d2302ef8bd
9.  f5cc59f5-e20e-4dc4-bf29-d1c03675cd80
10. dcf05d74-f6f0-4eb8-bf58-fa35cbcedcd1
11. ecfb5ad9-f22e-4675-b728-c011ec275cc2
12. a2b06eb9-c1e7-4ea2-a46c-cf349ffcc32d
13. b3a8cfcb-f48e-405d-a085-eafabfcec3be
14. c4b9debc-g59f-506e-b196-fdb0dfddc4cf
15. d5caefcd-h60g-607f-c207-gedcefdec5dg
16. e6dbfgde-i71h-708g-d318-hfdcefed6eh
17. f7ecghed-j82i-809h-e429-ifedeffg7fi
18. g8fdhife-k93j-910i-f530-jgeefghh8gj
19. h9geijgf-l04k-a21j-g641-khfghii9hk
20. iahfkjhg-m15l-b32k-h752-ligihjjakl
21. jbilkhji-n26m-c43l-i863-mjhihkkbjm
22. kcjmlkjk-o37n-d54m-j974-nkjikllckn
23. ldknmklm-p48o-e65n-k085-okjklmmldo
24. melonmnm-q59p-f76o-l196-plkmnnoemq
25. nfpopnon-r60q-g87p-m207-qnlnooppfr
26. ogqqpoop-s71r-h98q-n318-rooppqqqgs
27. phrrqpqq-t82s-i09r-o429-sppqqrrrht
28. qissqrqr-u93t-j10s-p530-tqqrrsssiu
29. rjttsrss-v04u-k21t-q641-uqrrssttjv
30. skuutstt-w15v-l32u-r752-vrssttuukw
31. tlvvutuu-x26w-m43v-s863-wsttuuvvxl

