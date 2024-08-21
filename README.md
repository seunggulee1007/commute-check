# 지각 체크 프로젝트

8시 30분까지 출근하기 위해 만든 프로젝트.
8시 30분 이후에 출근을 3번 이상 하게 되면 영화 티켓을 한장씩 구매한다.

````mermaid
classDiagram
class Account {
-Long id
-AccountId accountId
-String nickname
-String password
-String email
-String empNumber
-Set<AccountRole> roles
+void commute(DateTime time)
}

    class Commute {
        -String commuteId
        -String accountId
        -LocalDate commuteDate
        -LocalDateTime inTime
        -LocalDateTime outTime
        -boolean late
        
    }

    class Penalty {
        -String penaltyId
        -String accountId
        -Integer penaltyCount
        -Integer lateCount
        -Integer deductionCount
        -Integer totalLateCoun
        +void renewPenalty(Commute commute)
    }
    
    class Movie {
        - String movieId
        - String collectDate
        - String rank
        - String title
        - String bookingRate
        - String openDate
        - String detailLink
        - String posterImageLink
        - String releaseDate
        - String bookingLink
        - LocalDateTime createdAt
    }

    class Crwaler {
        - String url
        - String html
        - Document doc
        + void getHtml()
        + void getDocument()
        + void getMovie()
    }
    
    Account --> Commute : commute
    Commute --> Penalty : renewPenalty
    Account --> Movie : getMovie
    Crwaler --> Movie : insertMovie
```