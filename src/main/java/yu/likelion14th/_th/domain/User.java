package yu.likelion14th._th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// 회원 엔티티
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "useremail", nullable = false, length = 100)
    private String useremail;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    // User : Todo = 1 : N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();

    @Builder
    public User(String username, String useremail, String password) {
        this.username = username;
        this.useremail = useremail;
        this.password = password;
    }

    // 회원 정보 수정
    public void update(String username, String useremail, String password) {
        if (username != null) this.username = username;
        if (useremail != null) this.useremail = useremail;
        if (password != null) this.password = password;
    }
}
