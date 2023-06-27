package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.infrastructure.database.entity.EmailVerificationTokenEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmailVerificationTokenMapper {

    EmailVerificationTokenEntity mapToEntity(EmailVerificationToken emailVerificationToken);
    @Mapping(target = "user.roleId", ignore = true)
    @Mapping(target = "user.emailVerificationToken", ignore = true)
    @Mapping(target = "user.resetPasswordTokenEntity", ignore = true)
    EmailVerificationToken mapFromEntity(EmailVerificationTokenEntity emailVerificationTokenEntity);
}
