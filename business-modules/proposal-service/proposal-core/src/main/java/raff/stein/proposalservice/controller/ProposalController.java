package raff.stein.proposalservice.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProposalApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import raff.stein.proposalservice.datasync.model.ProposalListResponse;
import raff.stein.proposalservice.datasync.model.ProposalRequest;
import raff.stein.proposalservice.datasync.model.ProposalResponse;
import raff.stein.proposalservice.service.ProposalService;

@Controller
@RequiredArgsConstructor
public class ProposalController implements ProposalApi {

    private final ProposalService proposalService;

    @Override
    public ResponseEntity<ProposalListResponse> getAllProposals() {
        return null;
    }

    @Override
    public ResponseEntity<ProposalResponse> getProposalById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ProposalResponse> createProposal(@RequestBody ProposalRequest proposalRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProposal(Long id) {
        proposalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

