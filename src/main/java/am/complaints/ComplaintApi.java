package am.complaints;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class ComplaintApi {

    private final ComplaintQueryObjectRepository repository;
    private final CommandGateway commandGateway;

    @Autowired
    public ComplaintApi(ComplaintQueryObjectRepository repository, CommandGateway commandGateway) {
        this.repository = repository;
        this.commandGateway = commandGateway;
    }

    @GetMapping
    public List<ComplaintQueryObject> findAll() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ComplaintQueryObject findOne(@PathVariable String id) {
        return repository.findOne(id);
    }

    @PostMapping
    public CompletableFuture<String> fileComplaint(@RequestBody Map<String, String> request) {
        String id = UUID.randomUUID().toString();
        return commandGateway.send(new FileComplaintCommand(id, request.get("company"), request.get("description")));
    }

}
