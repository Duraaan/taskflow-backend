package cl.sdc.taskflow.controller;

import cl.sdc.taskflow.dto.TaskRequest;
import cl.sdc.taskflow.dto.TaskResponse;
import cl.sdc.taskflow.dto.TaskUpdateRequest;
import cl.sdc.taskflow.model.entity.Task;
import cl.sdc.taskflow.model.enums.TaskStatus;
import cl.sdc.taskflow.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping
    public List<TaskResponse> getTasks(@RequestParam(required = false) TaskStatus status) {
        return taskService.getByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


}
