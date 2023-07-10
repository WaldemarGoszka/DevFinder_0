# DevFinder_0

### Google Docs
https://docs.google.com/document/d/1ObqP6bslrtjoMebmcN0_7sKRWantNsZWjdJRCQXGcu8/edit#

### Git:
https://github.com/WaldemarGoszka/DevFinder_0.git

### Tomcat:
http://localhost:8190/devfinder

### Bootstrap templates:
preffer:
https://startbootstrap.com/theme/sb-admin-2

dark:
https://themewagon.com/themes/corona-free-responsive-bootstrap-4-admin-dashboard-template/

https://themewagon.com/themes/free-bootstrap-5-admin-dashboard-template-darkpan/

https://startbootstrap.com/template/sb-admin

Database Troubleshoting :
https://www.jetbrains.com/help/datagrip/2023.1/connectivity-problems.html?utm_source=from_product&utm_medium=help_link&utm_campaign=DB&utm_content=2023.1#configure-driver-from-the-existing-procedure

favicon.ico:
https://freefavicon.com/freefavicons/business/iconinfo/business-man-avatar-vector-152-185058.html


Job boards:

To enable security - change value in application.yaml: 
spring:
    security:
        enabled: false

To enable email validation after registration - change value in application.yaml
devfinder-conf:
    enable-email-verification: true

Select2:
https://select2.github.io/select2-bootstrap-theme/4.0.3.html
https://select2.org/
https://select2.github.io/select2/
https://github.com/select2/select2

https://preview.keenthemes.com/html/craft/docs/forms/select2

Działanie filtrów, paginacji i sortoania:
OfferCriteriaRepository - przetrzymuje odczyt czyli metody sortowania, paginacji i filtrowania. Definiujemy w niej
predykaty które są użyte przez Criteria API. Predykat są opcjonalne więc musimy sprawdzić czy nie są nullem
OfferRepository - przetrzymuje zapis, usuwanie i aktualizacje
OfferPage - przetrzymuje domyślne wartości paginacji i sortowania aby zbudować obiekt Pageable i Sort
OfferSearchCriteria - przerzymuje możliwości sortowania i filtrowania po  danych polach

http://localhost:8190/devfinder/candidate/offers/1?IsExperienceLevelIsJunior=true&IsExperienceLevelIsSenior=false&remoteWork=5