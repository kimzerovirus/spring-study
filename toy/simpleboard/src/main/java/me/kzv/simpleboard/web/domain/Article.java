package me.kzv.simpleboard.web.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdDate"),
        @Index(columnList = "createdBy")
})
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private UserAccount userAccount; // 유저 정보 (ID)

    @Setter @Column(nullable = false) private String title; // 제목
    @Setter @Lob @Column(nullable = false) private String content; // 본문 검색은 인덱스를 걸기에 너무 커서 엘라스틱서치 등으로 따로 거는게 좋다

    @OrderBy("id")
    @ToString.Exclude // ArticleComment 에서도 ToString 이 있으므로 순환참조가 일어날 수 있으므로 제거
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @ToString.Exclude
    @JoinTable(
            name = "article_hashtag",
            joinColumns = @JoinColumn(name = "articleId"),
            inverseJoinColumns = @JoinColumn(name = "hashtagId")
    )
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Hashtag> hashtags = new LinkedHashSet<>();

    private Article(UserAccount userAccount, String title, String content) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
    }

    public static Article of(UserAccount userAccount, String title, String content) {
        return new Article(userAccount, title, content);
    }

    public void addHashtag(Hashtag hashtag) {
        this.getHashtags().add(hashtag);
    }

    public void addHashtags(Collection<Hashtag> hashtags) {
        this.getHashtags().addAll(hashtags);
    }

    public void clearHashtags() {
        this.getHashtags().clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        /**
         * java 14++ pattern variable 사용해서 한줄로 코드 간략하게 바꾸기
         * if (!(o instanceof Article)) return false;
         * Article article = (Article) o;
         */
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id); // 영속화가 되지 않으면 id 가 null
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
