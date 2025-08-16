package raff.stein.customer.controller;

import lombok.RequiredArgsConstructor;
import org.openapitools.api.GoalApi;
import org.openapitools.model.GoalTypeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import raff.stein.customer.service.GoalsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GoalsController implements GoalApi {

    private final GoalsService goalsService;

    @Override
    public ResponseEntity<List<GoalTypeDTO>> getAllGoalTypes() {
        return null;
    }
}
