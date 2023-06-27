package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.ResetPasswordToken;
import pl.devfinder.infrastructure.database.entity.ResetPasswordTokenEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResetPasswordTokenMapper {

    ResetPasswordTokenEntity mapToEntity(ResetPasswordToken resetPasswordToken);
    @Mapping(target = "user.roleId", ignore = true)
    @Mapping(target = "user.emailVerificationToken", ignore = true)
    @Mapping(target = "user.resetPasswordTokenEntity", ignore = true)
    ResetPasswordToken mapFromEntity(ResetPasswordTokenEntity resetPasswordTokenEntity);
}
