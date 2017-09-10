package am.complaints;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Complaint {

    @AggregateIdentifier
    private String complaintId;

    @CommandHandler
    public Complaint(final FileComplaintCommand command) {
        apply(new ComplaintFiledEvent(command.getId(), command.getCompany(), command.getDescription()));
    }

    @EventSourcingHandler
    public void on(final ComplaintFiledEvent event) {
        this.complaintId = event.getId();
    }

}
