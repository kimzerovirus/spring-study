> - 다대다 관계는 안쓰는게 좋음
> - 양방향 관계는 최대한 피하는게 좋음

상품 -> 카테고리 아이템 -> 카테고리

fk 돈과 같은 중요한 요소면 정확성을 위해 설정하는게 좋지만 유연함이 필요하다면 굳이 안걸어도 될듯하다.

연관 관계는 즉시로딩(EAGER) 보다는 지연로딩(LAZY)를 지향
- EAGER는 SQL 추적이 어렵다.
- JPQL 사용시 n+1 문제를 야기한다.
- XtoOne은 default가 EAGER이지만 OneToX는 default가 LAZY이다. 

컬렉션은 생성자 초기화 보다는 필드 초기화를 지향
- null safety
- 하이버네이트는 엔티티를 영속화 할 때, 컬렉션을 감싸서 하이버네이트가 제공하는 내장 컬렉션으로 변환한다.

테이블, 컬럼명 생성 전략
- 논리명 `spring.jpa.hibernate.naming.implicit-strategy`
- 물리명 `spring.jpa.hibernate.naming.physical-strategy` 

변경 감지와 병합
- 준영속 엔티티 : 더이상 영속성 컨텍스트가 관리하지 않는 엔티티(DB에 저장되고 불러온 객체 = 임으로 다시 만들어냈어도 식별자가 존재하는 엔티티)
- 준영속 엔티티를 수정하는 방법
    - 변경 감지(dirty checking) 기능
    - 병합(merge) 사용
      - 병합은 변경 감지와 달리 모든 속성을 변경하므로 속성값을 안넣어주면 Null로 업데이트 될 위험이 있다.
