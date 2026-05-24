package yu.likelion14th._th.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yu.likelion14th._th.dto.priority.PriorityReqDto;
import yu.likelion14th._th.dto.priority.PriorityResDto;
import yu.likelion14th._th.service.PriorityService;

import java.util.List;

// 우선순위 컨트롤러
@RestController
@RequestMapping("/api/priorities")
@RequiredArgsConstructor
public class PriorityController {

    private final PriorityService priorityService;

    // 우선순위 생성
    @PostMapping
    public PriorityResDto create(@RequestBody PriorityReqDto requestDto) {
        return priorityService.create(requestDto);
    }

    // 전체 우선순위 조회
    @GetMapping
    public List<PriorityResDto> findAll() {
        return priorityService.findAll();
    }

    // 단일 우선순위 조회
    @GetMapping("/{priorityId}")
    public PriorityResDto findById(@PathVariable Long priorityId) {
        return priorityService.findById(priorityId);
    }

    // 우선순위 수정
    @PutMapping("/{priorityId}")
    public PriorityResDto update(@PathVariable Long priorityId,
                                 @RequestBody PriorityReqDto requestDto) {
        return priorityService.update(priorityId, requestDto);
    }

    // 우선순위 삭제
    @DeleteMapping("/{priorityId}")
    public void delete(@PathVariable Long priorityId) {
        priorityService.delete(priorityId);
    }
}
