# Devfinder - Recruitment Platform

Welcome to the Recruitment Platform! This is a web application designed to connect employers with skilled programmers
seeking new opportunities.

## Features

### For Employers

- Create an account to represent your company on the platform.
- Browse candidate profiles based on your criteria.
- Mark candidates as hired to indicate their availability status.
- Post job offers to attract potential candidates directly to your open positions.

### For Candidates

- Create a profile to showcase your skills and experience.
- Upload your photo to personalize your profile.
- Search for companies seeking candidates like you.
- Mark your profile as "inactive" if not actively looking for new opportunities.
- Explore job offers posted by employers.

## Description

### Content available without registration

Without registration, we get access to 3 sites that search for candidates, employers and employer offers.
On each page we are shown tables of results. By default, the results are sorted and paginated. We are given how many
results were returned. We can change the number of items per page (default is 5) from 5,10,50. We can change pages by
clicking on the page number or Next, Previous buttons. We can change the column by which the results are sorted by
clicking on the column head, the columns by which we can sort are red. We can change the direction of sorting by
clicking again on the same column, The direction of sorting can be seen by a white strap line which changes according to
the direction of sorting, I used font-awesome and JavaScript functions for this.

Filters take into account many parameters, They were written using Criteria Api. Here you can select multiple fields at
once and they will all be taken into account when searching. I think few job platforms have such advanced search.

All sorting as well as filtering parameters are included in param
url ```http://localhost:8080/devfinder/offers?city=Warszawa&employer=ABC+Sp.+z+o.o.&salaryMin=&pageSize=5&pageNumber=1&sortBy=title&sortDirection=ASC#```.

Offers as well as candidates can have INACTIVE, EMPLOYED or EXPIRED status, the line of such result is displayed in
darker font. We can also filter them to not be displayed.

We can go into the details of each offer, candidate or employee. When entering the details of an employer, we have links
leading to offers issued by that employer. Entering the details of the offer we have links to the details of the
employer and links to other offers issued by this employer.

Translated with www.DeepL.com/Translator (free version)

### Registration

Aplication allows you to register two types of user. You can register as a "Candidate" or as an "Employer".
After registration, the application can send an email from ```devfinder.service@gmail.com``` to the email provided at
registration. For testing purposes and to speed up testing of the application, the option to send verification email is
disabled. It can be enabled by changing the flag to true in the aplication.yaml file in the
```devfinder-conf.enable-email-verification``` option
When the option to send an email is enabled after registration, an email with an activation link is sent, the link
contains a verified
token ```http://localhost:8080/devfinder/register/verify_email?token=247b2acf-66af-408c-a2f2-2050addf4c80```. This token
is active for 10 minutes (this can be changed in the TokenExpirationTime class).
The token is stored in the database along with the user and registration time information in the
```email_varification_token``` column. Only when the user clicks on the activation link, it checks if the token is valid
for
the user and toggles the ```isEnabled``` flag to true in the User object which means that the user has been verified and
only
now can log in.
When the email option is disabled, the email with the verification link is not sent and the isEnabled flag is
immediately switched to true and the user can log in immediately

### Forgot Password

If you forget your password, the application allows you to set a new password. We only need to know our email address
that we used when registering for the account. We send the form with the email and we get an email from
```devfinder.service@gmail.com``` along with an email in which there is a link to reset the password which contains an
wtgenerated token which is stored in the database in the ```reset_password_token```
column (```http://localhost:8080/devfinder/register/password_reset_form?token=e3039f53-8297-4a64-8782-0689f7fce14e```)
the
link contains a token which leads us to the password change form where we enter the new password. Now we can log in with
the new password

### Candidate Portal

The candidate portal allows you to create your own profile for employers to view. We can enter all of our details from
personal information to hobbies .We have the ability to select our skills, years of experience, experience level,
education, whether we are open to working remotely, links to github and linkedin and minimum expected payout. We have
the option to add a .jpeg photo and a resume/cv .pdf file, and we can set ACTIVE or INACTIVE status. We cannot set
EMPLOYED status because only the employer can do that. When we have EMPLOYED status we also cannot change our status,
only the employer can do that by firing us.
We have the ability to edit our profile at any time to remove our photo, cv or entire profile. In order for our profile
not to be in the search results, we have to delete the entire profile.
When creating a profile is created

### Employer Portal

The employer has the ability to issue new offers and edit existing ones and delete them. He also has the ability to hire
candidates
In order to add a new job offer, the employer first needs to fill out his profile, in which he provides basic data, he
also has the option of adding a company logo. The company profile shows the number of active job offers. Issuing a new
job offer. We have the opportunity to provide basic information about the candidate we are looking for, as well as the
skills that the candidate should have. We can specify the percentage of remote work, where 0% means work in the office
and 100% means fully remote work, we can specify the salary range and benefits at work.
The employer has the ability to hire employees. In order to hire an employee he must have an ACTIVE status. Employed
candidates are in the "Employee" tab, you can dismiss an employee at any time by entering one datails

### User Settings

It is possible to change your password in the Settings tab. Both the candidate and the employer can delete his account
completely, which will block the possibility of logging in to it. All data will be deleted, to return to the portal
again must re-register .

### REST API

#### Swagger-UI / Open Api

http://localhost:8080/devfinder/swagger-ui/index.html


## Technologies Used

- Spring Boot
- Thymeleaf
- Spring Security
- Docker Compose
- PostgreSQL
- Flyway
- Spring Data JPA
- Spring Criteria API
- REST API
- Swagger UI
- MapStruct
- JavaScript
- HTML

File transfer
Files are saved on the server in the directory defined in application.yaml devfinder-conf.user-data-path : "src/main/resources/static/user_data/"
When When saving an image to a directory, the name of the directory is changed, the user uuid is added to prevent collision of the same file name uploaded by 2 users. In addition, the hyphens "-" are replaced with underscores "_" in the uuid. When a user deletes their profile or account, the files are also deleted.

Data validation
CandidateUpdateRequestDTO, EmployerUpdateRequestDTO, OfferUpdateRequestDTO classes are used to transport data from the frontend to the backend. In them is used validation of input data so that it goes to the backend in the correct form

Mapstruct
The application uses 3 layers: data access layer, service layer and web layer. Between these layers objects are mapped with the help of mappstruct

## Database

### ERD Database Diagram

![ERD Diagram](ERD_diagram_devfinder.png)

### solutions used in the data base
#### functions used in the database
The ```employer.amount_of_available_offers``` field is responsible for functions and database triggers such as
``increase_amount_of_available_offers_when_add_new()```
after insert on offer

```decrease_amount_of_available_offers_when_delete()```
after delete ON offer

```recalculate_amount_of_available_offers_after_inserts()```
when running data base

```decrease_amount_of_available_offers_when_change_active_to_expired()```
when offer status changes from ACTIVE to EXPIRED

```increase_amount_of_available_offers_when_change_expired_to_active()```
when offer status changes from EXPIRED to ACTIVE

A user in the database can have 2 roles EMPLOYER or CANDIDATE.
The user.user_uuid field is detected in the database. User is created when the user registers. Employer and candidate columns are created when the user fills out his profile, then user_uuuid is the same as employer_uuuid or candidate_uuid.

The city table is updated each time the parameter city_id is deleted, added or edited in the employer, candidate or offer tables. If a city does not exist it is added to the database. if you delete an offer, candidate or employer and no others have that city it is removed from the database.
This is implemented for better search and filtering of results, because when you select the filter you have a list of all cities used in the database, if the city is not used in any of the records, it is removed.

## Further plans
- Adding the possibility for candidates to send applications to specific offers.
- List of submitted applications for offers in the employer and hiring portal.
- Ability to observe employers and receive notifications when a particular employer adds a new offer
- Possibility to add offers to favorites section
- Mechanism of sending messages in the application
- Notification function


## Getting Started
Clone repositorium

```git clone https://github.com/WaldemarGoszka/DevFinder_0.git```

Run in Intellij
create PostgreSQL database name: "devfinder"
run in webbrowser:
```http://localhost:8080/devfinder```

### Bootstrap templates:

Favicon.ico:
https://freefavicon.com/freefavicons/business/iconinfo/business-man-avatar-vector-152-185058.html

Bootstrap template:
https://themewagon.com/themes/free-bootstrap-5-admin-dashboard-template-darkpan/

