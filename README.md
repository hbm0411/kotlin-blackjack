# kotlin-blackjack

## Step2 - 블랙잭
### 기능 요구사항
블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

* 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
* 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.

### 실행 결과
```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

pobi, jason에게 2장의 나누었습니다.
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17
```

### 프로그래밍 요구 사항
* 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
* indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
* 모든 엔티티를 작게 유지한다.
* 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
* 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
* git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.

### 상세 구현 사항
[x] 이름 입력받기
    [x] 입력받은 값이 empty인 경우 IllegalArgumentException 발생
    [x] 쉼표로 입력받은 이름 나누기
    [x] 이름이 2개 미만인 경우 IllegalArgumentException 발생
[x] 랜덤한 카드 나누어주기
    [x] 나누어 주는 카드는 중복될 수 없다.
    [x] 처음에는 모두가 2장의 카드를 받는다.
[x] 추가로 카드 받기
    [x] 가지고 있는 카드의 합이 21 미만인 경우 카드를 추가로 더 받을 수 있다.
    [x] 가지고 있는 카드의 합이 21보다 크면 카드를 추가로 더 받을 수 없다.
    [x] 인원별로 각각 카드를 추가로 받지 않을때까지 받는다.
[x] 결과 출력
    [x] 플레이어별로 가지고 있는 카드 리스트와 카드의 합을 출력한다.

## Step3 - 블랙잭(딜러)
### 기능 요구사항
* 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
* 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
* 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

### 실행 결과
```text
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장의 나누었습니다.
딜러: 3다이아몬드, 9클로버
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```

### 프로그래밍 요구 사항
* 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
* 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.


### 상세 구현 사항
[] 딜러 구현
    [] 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 한다.
    [] 17점 이상이면 추가로 받을 수 없다.
[] 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리한다.
[] 게임을 완료한 후 각 플레이어별로 승패를 출력한다.