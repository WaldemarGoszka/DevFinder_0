package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.infrastructure.database.entity.EmailVerificationTokenEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmailVerificationTokenMapper {

    EmailVerificationTokenEntity mapToEntity(EmailVerificationToken emailVerificationToken);
}
