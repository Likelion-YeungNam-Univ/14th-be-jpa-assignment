package yu.likelion14th._th.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yu.likelion14th._th.domain.User;
import yu.likelion14th._th.dto.user.UserReqDto;
import yu.likelion14th._th.dto.user.UserResDto;
import yu.likelion14th._th.repository.UserRepository;

import java.util.List;

// 회원 서비스
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 생성
    public UserResDto create(UserReqDto requestDto) {
        if (userRepository.existsByUseremail(requestDto.getUseremail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다!");
        }

        User user = User.builder()
                .username(requestDto.getUsername())
                .useremail(requestDto.getUseremail())
                .password(requestDto.getPassword())
                .build();

        return UserResDto.from(userRepository.save(user));
    }

    // 전체 회원 조회
    public List<UserResDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResDto::from)
                .toList();
    }

    // 단일 회원 조회
    public UserResDto findById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음!"));
        return UserResDto.from(user);
    }

    // 회원 정보 수정
    public UserResDto update(Long userId, UserReqDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 없음!"));
        user.update(requestDto.getUsername(), requestDto.getUseremail(), requestDto.getPassword());
        return UserResDto.from(user);
    }

    // 회원 삭제
    public void delete(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("해당 사용자 없음!");
        }
        userRepository.deleteById(userId);
    }
}
