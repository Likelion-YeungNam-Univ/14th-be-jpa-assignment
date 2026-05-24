package yu.likelion14th._th.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// 카테고리 엔티티
@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "icon_url", nullable = false, length = 255)
    private String iconUrl;

    // Category : Todo = 1 : N
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();

    @Builder
    public Category(String name, String iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
    }

    // 카테고리 수정
    public void update(String name, String iconUrl) {
        if (name != null) this.name = name;
        if (iconUrl != null) this.iconUrl = iconUrl;
    }
}
