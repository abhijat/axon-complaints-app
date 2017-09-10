package am.complaints;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComplaintQueryObjectUpdater {

    private final ComplaintQueryObjectRepository repository;

    @Autowired
    public ComplaintQueryObjectUpdater(ComplaintQueryObjectRepository repository) {
        this.repository = repository;
    }


    @EventHandler
    public void on(final ComplaintFiledEvent event) {
        repository.save(new ComplaintQueryObject(event.getId(), event.getCompany(), event.getDescription()));
    }

}
