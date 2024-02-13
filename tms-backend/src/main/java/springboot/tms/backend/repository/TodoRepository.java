package springboot.tms.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.tms.backend.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
