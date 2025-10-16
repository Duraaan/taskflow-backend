package cl.sdc.taskflow.controller;

import cl.sdc.taskflow.dto.TaskRequest;
import cl.sdc.taskflow.model.entity.Task;
import cl.sdc.taskflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@Valid @RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }


    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable  Long id) {
        return taskService.getTaskById(id);
    }
}
