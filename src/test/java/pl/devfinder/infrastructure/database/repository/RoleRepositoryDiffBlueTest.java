package pl.devfinder.infrastructure.database.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.devfinder.infrastructure.database.entity.RoleEntity;
import pl.devfinder.infrastructure.database.repository.jpa.RoleJpaRepository;
import pl.devfinder.infrastructure.database.repository.mapper.RoleEntityMapper;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RoleRepository.class})
@ExtendWith(SpringExtension.class)
class RoleRepositoryDiffBlueTest {
    @MockBean
    private RoleEntityMapper roleEntityMapper;

    @MockBean
    private RoleJpaRepository roleJpaRepository;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Method under test: {@link RoleRepository#findByRole(String)}
     */
    @Test
    void testFindByRole() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setRole("Role");
        roleEntity.setUserId(new HashSet<>());
        Optional<RoleEntity> ofResult = Optional.of(roleEntity);
        when(roleJpaRepository.findByRole(Mockito.any())).thenReturn(ofResult);
        when(roleEntityMapper.mapFromEntity(Mockito.any())).thenReturn(null);
        assertFalse(roleRepository.findByRole("Role").isPresent());
        verify(roleJpaRepository).findByRole(Mockito.any());
        verify(roleEntityMapper).mapFromEntity(Mockito.any());
    }
}

