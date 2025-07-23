package raff.stein.orderservice.service;

import raff.stein.proposalservice.dataasync.model.ProposalUpdatedEvent;

public class OrderService {
    
    public void createOrder() {
        // Logic to create an order
        // read from ProposalTopic
        ProposalUpdatedEvent proposalUpdatedEvent = readProposalUpdatedEvent();
    }

    private ProposalUpdatedEvent readProposalUpdatedEvent() {
        return new ProposalUpdatedEvent().proposalId(1L);
    }
}
