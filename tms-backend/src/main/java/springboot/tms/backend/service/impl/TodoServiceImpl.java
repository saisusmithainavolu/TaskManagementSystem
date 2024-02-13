package springboot.tms.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springboot.tms.backend.dto.TodoDto;
import springboot.tms.backend.entity.Todo;
import springboot.tms.backend.repository.TodoRepository;
import springboot.tms.backend.service.TodoService;
import springboot.tms.backend.Exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // convert TodoDto into Todo entity
//            Todo todo = new Todo();
//            todo.setTitle(todoDto.getTitle());
//            todo.setDescription(todoDto.getDescription());
//            todo.setCompleted(todoDto.getCompleted());
            Todo todo = modelMapper.map(todoDto, Todo.class);

         // save Todo entity to database
            Todo savedTodo = todoRepository.save(todo);

         // convert saved Todo entity to TodoDto
//        TodoDto savedDto = new TodoDto();
//        savedDto.setId(savedTodo.getId());
//        savedDto.setTitle(savedTodo.getTitle());
//        savedDto.setDescription(savedTodo.getDescription());
//        savedDto.setCompleted(savedTodo.isCompleted());
        TodoDto savedDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedDto;
    }

    @Override
    public TodoDto getTodoById(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo doesn't exist with given ID :"+id));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

       List<Todo> todos = todoRepository.findAll();
       return todos.stream().map(t-> modelMapper.map(t, TodoDto.class)).collect(Collectors.toList());

    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo doesn't exist with given ID :"+id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo doesn't exist with given ID :"+id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo doesn't exist with given ID :"+id));

        todo.setCompleted(Boolean.TRUE);
        Todo completeTodo = todoRepository.save(todo);

        return modelMapper.map(completeTodo, TodoDto.class);

    }

    @Override
    public TodoDto inCompleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo doesn't exist with given ID :"+id));

        todo.setCompleted(Boolean.FALSE);
        Todo inCompleteTodo = todoRepository.save(todo);

        return modelMapper.map(inCompleteTodo, TodoDto.class);
    }
}
