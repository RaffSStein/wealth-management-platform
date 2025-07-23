package raff.stein.proposalservice.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProposalApi;
import org.openapitools.model.ProposalListResponse;
import org.openapitools.model.ProposalRequest;
import org.openapitools.model.ProposalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import raff.stein.proposalservice.service.ProposalService;

@Controller
@RequiredArgsConstructor
public class ProposalController implements ProposalApi {

    private final ProposalService proposalService;


    @Override
    public ResponseEntity<ProposalResponse> createProposal(ProposalRequest proposalRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProposal(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ProposalListResponse> getAllProposals() {
        return null;
    }

    @Override
    public ResponseEntity<ProposalResponse> getProposalById(Long id) {
        return null;
    }
}

