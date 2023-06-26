package pl.devfinder.infrastructure.database.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.devfinder.domain.ResetPasswordToken;
import pl.devfinder.infrastructure.database.entity.ResetPasswordTokenEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResetPasswordTokenMapper {

    ResetPasswordTokenEntity mapToEntity(ResetPasswordToken resetPasswordToken);
}
