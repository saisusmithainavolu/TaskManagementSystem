package springboot.tms.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springboot.tms.backend.dto.TodoDto;
import springboot.tms.backend.service.TodoService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/todos")
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // Method level security - only admin can access this rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){

        TodoDto savedDto =  todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);

    }

    // Method level security -  admin and users can access this rest api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){

        TodoDto getDto =  todoService.getTodoById(todoId);
        return new ResponseEntity<>(getDto, HttpStatus.OK);

    }

    // Method level security -  admin and users can access this rest api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping()
    public ResponseEntity<List<TodoDto>> getAllTodos(){

        List<TodoDto> todos =  todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }

    // Method level security - only admin can access this rest api
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long todoId, @RequestBody TodoDto todoDto){

        TodoDto updateDto =  todoService.updateTodo(todoId,todoDto);
        return new ResponseEntity<>(updateDto, HttpStatus.OK);

    }

    // Method level security - only admin can access this rest api
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){

        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted Successfully!!");

    }

    // Method level security -  admin and users can access this rest api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){

        TodoDto completeDto =  todoService.completeTodo(todoId);
        return ResponseEntity.ok(completeDto);

    }

    // Method level security -  admin and users can access this rest api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){

        TodoDto inCompleteDto =  todoService.inCompleteTodo(todoId);
        return ResponseEntity.ok(inCompleteDto);

    }
}
