package pl.devfinder.infrastructure.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.devfinder.api.controller.rest.CandidateDataRestController;
import pl.devfinder.api.controller.rest.CandidateUserRestController;
import pl.devfinder.api.dto.CandidateDetailsDTO;
import pl.devfinder.api.dto.CandidateUpdateRequestDTO;
import pl.devfinder.api.dto.CandidatesDTO;


public interface CandidateRestControllerTestSupport {
    RequestSpecification requestSpecification();

    default CandidatesDTO listCandidates() {
        return requestSpecification()
                .get(CandidateDataRestController.BASE_PATH + CandidateDataRestController.CANDIDATES_LIST)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CandidatesDTO.class);
    }

    default CandidateDetailsDTO getCandidate(final Long candidateId) {
        return requestSpecification()
                .get(CandidateDataRestController.BASE_PATH + CandidateDataRestController.CANDIDATE_DETAIL, candidateId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CandidateDetailsDTO.class);
    }

    default ExtractableResponse<Response> createCandidateProfile(final CandidateUpdateRequestDTO candidateUpdateRequestDTO) {
        return requestSpecification()
                .body(candidateUpdateRequestDTO)
                .post(CandidateUserRestController.BASE_PATH + CandidateUserRestController.CANDIDATE_NEW_PROFILE)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();

    }

    // update
    default ExtractableResponse<Response> updateCandidateProfile(final CandidateUpdateRequestDTO candidateUpdateRequestDTO) {
        String endpoint = CandidateUserRestController.BASE_PATH + CandidateUserRestController.CANDIDATE_NEW_PROFILE;
        return requestSpecification()
                .body(candidateUpdateRequestDTO)
                .put(endpoint)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract();
    }
}