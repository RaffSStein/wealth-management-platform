package raff.stein.proposalservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raff.stein.proposalservice.model.entity.ProposalEntity;
import raff.stein.proposalservice.repository.ProposalRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;

    public List<ProposalEntity> findAll() {
        return proposalRepository.findAll();
    }

    public Optional<ProposalEntity> findById(Long id) {
        return proposalRepository.findById(id);
    }

    @Transactional
    public ProposalEntity save(ProposalEntity proposalEntity) {
        return proposalRepository.save(proposalEntity);
    }

    @Transactional
    public void deleteById(Long id) {
        proposalRepository.deleteById(id);
    }
}

