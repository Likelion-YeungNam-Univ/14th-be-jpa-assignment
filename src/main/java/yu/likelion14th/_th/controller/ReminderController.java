package yu.likelion14th._th.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th._th.dto.reminder.ReminderReqDto;
import yu.likelion14th._th.dto.reminder.ReminderResDto;
import yu.likelion14th._th.service.ReminderService;

import java.util.List;

// 알림 컨트롤러
@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    // 알림 생성
    @PostMapping
    public ReminderResDto create(@RequestBody ReminderReqDto requestDto) {
        return reminderService.create(requestDto);
    }

    // 전체 알림 조회
    @GetMapping
    public List<ReminderResDto> findAll() {
        return reminderService.findAll();
    }

    // 단일 알림 조회
    @GetMapping("/{reminderId}")
    public ReminderResDto findById(@PathVariable Long reminderId) {
        return reminderService.findById(reminderId);
    }

    // 특정 할 일의 알림 조회
    @GetMapping("/todo/{todoId}")
    public ReminderResDto findByTodoId(@PathVariable Long todoId) {
        return reminderService.findByTodoId(todoId);
    }

    // 알림 발송 상태 변경
    @PutMapping("/{reminderId}")
    public ReminderResDto update(@PathVariable Long reminderId,
                                 @RequestBody ReminderReqDto requestDto) {
        return reminderService.update(reminderId, requestDto);
    }

    // 알림 삭제
    @DeleteMapping("/{reminderId}")
    public void delete(@PathVariable Long reminderId) {
        reminderService.delete(reminderId);
    }
}
