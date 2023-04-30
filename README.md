# 😎 Awesome amething server

## 주의사항
1. IDE에서 gradle7.6이 아닌 경우 IDE에서 syntex error가 발생할 수 있다.
2. JDK17 이상에서만 빌드 및 실행된다.

## Quick Start
```shell
./gradlew build
java -jar build/libs/amething-0.0.1.jar
```

## 협업 규칙
### PR & Merge
1. PR 제목은 개발한 작업(feature)이름을 간단하게 적고 코드 개선의 경우도 무엇을 개선했는지 간단하게 적는다.
2. 최대한 작업의 단위를 분리하고 만약 어쩔 수 없이 코드의 변경이 많으면 설명을 자세히 적는다.
3. main/develop에 merge시 squash and merge를 통해 변경 사항을 반영한다.
   > 깔끔하고 관리 가능한 형태로 커밋 메시지를 관리하기 위함이다
