package yu.likelion14th._th.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th._th.domain.Category;
import yu.likelion14th._th.domain.Priority;
import yu.likelion14th._th.domain.Todo;
import yu.likelion14th._th.domain.User;
import yu.likelion14th._th.dto.todo.TodoReqDto;
import yu.likelion14th._th.dto.todo.TodoResDto;
import yu.likelion14th._th.repository.CategoryRepository;
import yu.likelion14th._th.repository.PriorityRepository;
import yu.likelion14th._th.repository.TodoRepository;
import yu.likelion14th._th.repository.UserRepository;

import java.util.List;

// 할 일 서비스
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PriorityRepository priorityRepository;

    // 할 일 생성
    public TodoResDto create(TodoReqDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음!"));
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음!"));
        Priority priority = priorityRepository.findById(requestDto.getPriorityId())
                .orElseThrow(() -> new IllegalArgumentException("해당 우선순위 없음!"));

        Todo todo = Todo.builder()
                .content(requestDto.getContent())
                .user(user)
                .category(category)
                .priority(priority)
                .build();

        return TodoResDto.from(todoRepository.save(todo));
    }

    // 전체 할 일 조회
    public List<TodoResDto> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoResDto::from)
                .toList();
    }

    // 단일 할 일 조회
    public TodoResDto findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일 없음!"));
        return TodoResDto.from(todo);
    }

    // 특정 사용자의 할 일 조회
    public List<TodoResDto> findAllByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("해당 사용자 없음!");
        }
        return todoRepository.findAllByUser_UserId(userId).stream()
                .map(TodoResDto::from)
                .toList();
    }

    // 할 일 수정
    public TodoResDto update(Long todoId, TodoReqDto requestDto) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일 없음!"));

        Category newCategory = null;
        if (requestDto.getCategoryId() != null) {
            newCategory = categoryRepository.findById(requestDto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 카테고리 없음!"));
        }

        Priority newPriority = null;
        if (requestDto.getPriorityId() != null) {
            newPriority = priorityRepository.findById(requestDto.getPriorityId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 우선순위 없음!"));
        }

        todo.update(requestDto.getContent(), newCategory, newPriority);
        return TodoResDto.from(todoRepository.save(todo));
    }

    // 완료 상태 토글
    public TodoResDto toggleDone(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일 없음!"));
        todo.toggleDone();
        return TodoResDto.from(todoRepository.save(todo));
    }

    // 할 일 삭제
    public void delete(Long todoId) {
        if (!todoRepository.existsById(todoId)) {
            throw new IllegalArgumentException("해당 할 일 없음!");
        }
        todoRepository.deleteById(todoId);
    }
}
