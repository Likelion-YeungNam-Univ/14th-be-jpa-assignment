package yu.likelion14th._th.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th._th.domain.Reminder;
import yu.likelion14th._th.domain.Todo;
import yu.likelion14th._th.dto.reminder.ReminderReqDto;
import yu.likelion14th._th.dto.reminder.ReminderResDto;
import yu.likelion14th._th.repository.ReminderRepository;
import yu.likelion14th._th.repository.TodoRepository;

import java.util.List;

// 알림 서비스
@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final TodoRepository todoRepository;

    // 알림 생성
    public ReminderResDto create(ReminderReqDto requestDto) {
        Todo todo = todoRepository.findById(requestDto.getTodoId())
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일 없음!"));

        if (reminderRepository.existsByTodo_TodoId(requestDto.getTodoId())) {
            throw new IllegalArgumentException("이미 알림이 등록된 할 일입니다!");
        }

        Reminder reminder = Reminder.builder()
                .isSent(requestDto.getIsSent())
                .todo(todo)
                .build();

        return ReminderResDto.from(reminderRepository.save(reminder));
    }

    // 전체 알림 조회
    public List<ReminderResDto> findAll() {
        return reminderRepository.findAll().stream()
                .map(ReminderResDto::from)
                .toList();
    }

    // 단일 알림 조회
    public ReminderResDto findById(Long reminderId) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림 없음!"));
        return ReminderResDto.from(reminder);
    }

    // 특정 할 일의 알림 조회
    public ReminderResDto findByTodoId(Long todoId) {
        Reminder reminder = reminderRepository.findByTodo_TodoId(todoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일의 알림 없음!"));
        return ReminderResDto.from(reminder);
    }

    // 알림 발송 상태 변경
    public ReminderResDto update(Long reminderId, ReminderReqDto requestDto) {
        Reminder reminder = reminderRepository.findById(reminderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림 없음!"));
        reminder.updateSentStatus(requestDto.getIsSent());
        return ReminderResDto.from(reminder);
    }

    // 알림 삭제
    public void delete(Long reminderId) {
        if (!reminderRepository.existsById(reminderId)) {
            throw new IllegalArgumentException("해당 알림 없음!");
        }
        reminderRepository.deleteById(reminderId);
    }
}
