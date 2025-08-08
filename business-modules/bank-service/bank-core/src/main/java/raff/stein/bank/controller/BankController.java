package raff.stein.bank.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.BankApi;
import org.openapitools.model.BankBranchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.bank.service.BankService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BankController implements BankApi {

    private final BankService bankService;


    @Override
    public ResponseEntity<BankBranchDTO> createBankBranch(BankBranchDTO bankBranchDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteBankBranch(String branchCode) {
        return null;
    }

    @Override
    public ResponseEntity<List<BankBranchDTO>> getAllBankBranches() {
        return null;
    }

    @Override
    public ResponseEntity<BankBranchDTO> getBankBranchByCode(String branchCode) {
        return null;
    }

    @Override
    public ResponseEntity<BankBranchDTO> updateBankBranch(String branchCode, BankBranchDTO bankBranchDTO) {
        return null;
    }
}
