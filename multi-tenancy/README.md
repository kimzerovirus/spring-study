# 멀티 테넌시
> 멀티 테넌시는 소프트웨어 어플리케이션의 단일 인스턴스가 여러 고객에게 서비스를 제공하는 아키텍처를 말한다.
> <br/>
> 멀티 테넌시는 소프트웨어 개발과 유지보수 비용을 공유하기 때문에 경제적이다.

Multi tenant - Same Application, Separate Database<br/>
예를 들면, 카카오와 같은 회사는 여러 개의 계열사가 존재한다.
따라서 각각의 계열사 별로 DB를 만들어 주고 어플리케이션은 하나로 관리하는 경우 적합한 아키텍처이다. 

## Multi-Tenancy
- tenancy-manager 모듈은 접속 할 DB의 정보를 관리하는 모듈이다.
  - mysql DB를 사용한다.
- tenancy-application 은 tenancy-manager 로 부터 얻은 DB 커넥션 정보를 사용해 어플리케이션을 작동한다.
  - mysql 과 postgresql 을 사용한다.