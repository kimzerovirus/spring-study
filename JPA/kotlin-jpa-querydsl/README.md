## querydsl
> 설정 완료 후 `./gradlew build` 명령어를 치면 
> `build/generated/source/kapt/main` 위치에 q클래스가 생성된다


### Q클래스 인스턴스를 사용하는 2가지 방법
```java
QMember qMember = new QMember("m"); //별칭 직접 지정 
QMember qMember = QMember.member; //기본 인스턴스 사용
```

### 기본 인스턴스를 static import와 함께 사용
```java
import static study.querydsl.entity.QMember.*;
@Test
public void startQuerydsl3(){
//member1을 찾아라.
        Member findMember=queryFactory
        .select(member)
        .from(member)
        .where(member.username.eq("member1"))
        .fetchOne();
}
```
