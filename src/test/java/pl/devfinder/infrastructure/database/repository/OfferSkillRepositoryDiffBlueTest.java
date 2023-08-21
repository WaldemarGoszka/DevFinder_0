package pl.devfinder.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.domain.Offer;
import pl.devfinder.infrastructure.database.entity.CityEntity;
import pl.devfinder.infrastructure.database.entity.EmployerEntity;
import pl.devfinder.infrastructure.database.entity.OfferEntity;
import pl.devfinder.infrastructure.database.repository.jpa.OfferSkillJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.OfferEntityMapper;
import pl.devfinder.infrastructure.database.repository.mapper.OfferSkillEntityMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OfferSkillRepository.class})
@ExtendWith(SpringExtension.class)
class OfferSkillRepositoryDiffBlueTest {
    @MockBean
    private OfferEntityMapper offerEntityMapper;

    @MockBean
    private OfferSkillEntityMapper offerSkillEntityMapper;

    @MockBean
    private OfferSkillJpaRepository offerSkillJpaRepository;

    @Autowired
    private OfferSkillRepository offerSkillRepository;

    /**
     * Method under test: {@link OfferSkillRepository#saveAll(Set)}
     */
    @Test
    void testSaveAll() {
        when(offerSkillJpaRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
        offerSkillRepository.saveAll(new HashSet<>());
        verify(offerSkillJpaRepository).saveAll(Mockito.any());
    }

    /**
     * Method under test: {@link OfferSkillRepository#deleteAllByOffer(Offer)}
     */
    @Test
    void testDeleteAllByOffer() {
        doNothing().when(offerSkillJpaRepository).deleteAllByOfferId(Mockito.any());

        CityEntity cityId = new CityEntity();
        cityId.setCandidateResidenceCities(new HashSet<>());
        cityId.setCityId(1L);
        cityId.setCityName("Oxford");
        cityId.setEmployerCities(new HashSet<>());
        cityId.setOfferCities(new HashSet<>());

        CityEntity cityId2 = new CityEntity();
        cityId2.setCandidateResidenceCities(new HashSet<>());
        cityId2.setCityId(1L);
        cityId2.setCityName("Oxford");
        cityId2.setEmployerCities(new HashSet<>());
        cityId2.setOfferCities(new HashSet<>());

        EmployerEntity employerId = new EmployerEntity();
        employerId.setAmountOfAvailableOffers(10);
        employerId.setCityId(cityId2);
        employerId.setCompanyName("Company Name");
        employerId.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        employerId.setDescription("The characteristics of someone or something");
        employerId.setEmailContact("jane.doe@example.org");
        employerId.setEmployerId(1L);
        employerId.setEmployerUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        employerId.setLogoFilename("foo.txt");
        employerId.setNumberOfEmployees(10);
        employerId.setPhoneNumber("6625550144");
        employerId.setWebsite("Website");

        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setBenefits("Benefits");
        offerEntity.setCityId(cityId);
        offerEntity.setCreatedAt(OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC));
        offerEntity.setDescription("The characteristics of someone or something");
        offerEntity.setEmployerId(employerId);
        offerEntity.setExperienceLevel("Experience Level");
        offerEntity.setOfferId(1L);
        offerEntity.setOfferSkills(new HashSet<>());
        offerEntity.setOfferUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        offerEntity.setOtherSkills("Other Skills");
        offerEntity.setRemoteWork(1);
        offerEntity.setSalaryMax(BigDecimal.valueOf(1L));
        offerEntity.setSalaryMin(BigDecimal.valueOf(1L));
        offerEntity.setStatus("Status");
        offerEntity.setTitle("Dr");
        offerEntity.setYearsOfExperience(1);
        when(offerEntityMapper.mapToEntity(Mockito.any())).thenReturn(offerEntity);
        offerSkillRepository.deleteAllByOffer(null);
        verify(offerSkillJpaRepository).deleteAllByOfferId(Mockito.any());
        verify(offerEntityMapper).mapToEntity(Mockito.any());
    }
}

