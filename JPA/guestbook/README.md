# Guestbook 방명록

### JPA Auditing이란?

Java에서 ORM 기술인 JPA를 사용하여 도메인을 관계형 데이터베이스 테이블에 매핑할 때 공통적으로 도메인들이 가지고 있는 필드나 컬럼들이 존재합니다.
대표적으로 생성일자, 수정일자, 식별자 같은 필드 및 컬럼이 있습니다.

도메인마다 공통으로 존재한다는 의미는 결국 코드가 중복된다는 말과 일맥상통합니다.
데이터베이스에서 누가, 언제하였는지 기록을 잘 남겨놓아야 합니다. 그렇기 때문에 생성일, 수정일 컬럼은 대단히 중요한 데이터 입니다.

그래서 JPA에서는 Audit이라는 기능을 제공하고 있습니다. Audit은 감시하다, 감사하다라는 뜻으로 Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능입니다.
도메인을 영속성 컨텍스트에 저장하거나 조회를 수행한 후에 update를 하는 경우 매번 시간 데이터를 입력하여 주어야 하는데,
audit을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어주게 됩니다.

AuditingEntityListener를 활성하시키려면 프로젝트 루트에 @EnableJpaAuditing어노테이션을 추가해주어야한다.

### Querydsl 적용하기
```
buildscript {
	ext {
		queryDslVersion = "5.0.0"
	}
}


plugins {
    id 'com.ewerk.gradle.plugins.querydsl' version '1.0.10'
}

dependencies {
    implementation "com.querydsl:querydsl-jpa:${queryDslVersion}"
	implementation "com.querydsl:querydsl-apt:${queryDslVersion}"
}

def querydslDir = "$buildDir/generated/querydsl"

querydsl {
	jpa = true
	querydslSourcesDir = querydslDir
}

sourceSets {
	main.java.srcDir querydslDir
}

configurations {
	querydsl.extendsFrom compileClasspath
}

compileQuerydsl {
	options.annotationProcessorPath = configurations.querydsl
}
```

queryDsl 버전 명시 안해줬을때 `Unable to load class 'com.mysema.codegen.model.Type'.` 라는 에러가 발생했다.

설정 후 어플리케이션을 실행하면 build 디렉토리에 generated라는 디렉토리가 생성된다.

### @NoArgsConstructor
매개변수가 없는 생성자
```
public TodoEntity(){

} 
```
### @ AllArgsConstructor
클래스의 모든 멤버 변수를 매개변수로 받는 생성자
```
public TodoEntity()(String id, String userId, String title, boolean done){
    super();
    this.id = id;
    this.userId = userId;
    this.title = title;
    this.done = done;
}
```