package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.EmailVerificationToken;
import pl.devfinder.infrastructure.database.entity.EmailVerificationTokenEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmailVerificationTokenMapper {

    EmailVerificationTokenEntity mapToEntity(EmailVerificationToken emailVerificationToken);

    @Mapping(source = "user.roleId", target = "user.role")
    @Mapping(target = "user.role.userId", ignore = true)
    @Mapping(target = "user.emailVerificationToken.user", ignore = true)
    @Mapping(target = "user.resetPasswordTokenEntity.user", ignore = true)
    EmailVerificationToken mapFromEntity(EmailVerificationTokenEntity emailVerificationTokenEntity);
}
