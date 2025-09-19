classDiagram
    direction LR
    
    class FinancialSimulation {
        +Long id
        +double initialValue
        +double monthlyInvestment
        +double interestRate
        +int months
        +double finalValue
    }

    class Activity {
        +Long id
        +Long userId
        +ActivityType type
        +LocalDateTime timestamp
    }

    class UserScore {
        +Long id
        +Long userId
        +int score
    }

    class ActivityType<<enum>> {
        SIMULATE_FINANCIAL_PLAN
        READ_ARTICLE
        COMPLETE_QUIZ
    }

    class FinancialSimulationRepository {
        <<interface>>
        +JpaRepository<FinancialSimulation, Long>
    }
    
    class ActivityRepository {
        <<interface>>
        +JpaRepository<Activity, Long>
    }
    
    class UserScoreRepository {
        <<interface>>
        +JpaRepository<UserScore, Long>
    }

    class FinancialSimulationService {
        +createSimulation()
        +getAllSimulations()
    }
    
    class GamificationService {
        +updateScore()
        +getAllUserScores()
    }

    class FinancialSimulationController {
        +createSimulation()
        +getAllSimulations()
    }

    class GamificationController {
        +addActivity()
        +getAllUserScores()
    }
    
    class FinancialSimulationRequestDTO {
        +double initialValue
        +double monthlyInvestment
        +double interestRate
        +int months
    }

    class FinancialSimulationResponseDTO {
        +double finalValue
    }
    
    class UserScoreDTO {
        +String userId
        +int score
    }

    FinancialSimulation ..> FinancialSimulationRepository : usa
    Activity ..> ActivityRepository : usa
    UserScore ..> UserScoreRepository : usa

    FinancialSimulationService ..> FinancialSimulationRepository : usa
    GamificationService ..> UserScoreRepository : usa
    GamificationService ..> ActivityRepository : usa

    FinancialSimulationController ..> FinancialSimulationService : usa
    FinancialSimulationController ..> GamificationService : usa
    GamificationController ..> GamificationService : usa
    
    FinancialSimulationService ..> FinancialSimulationRequestDTO : usa
    FinancialSimulationService ..> FinancialSimulationResponseDTO : usa
    GamificationService ..> UserScoreDTO : usa
    Activity ..> ActivityType : usa
    
    FinancialSimulationController ..> FinancialSimulationRequestDTO : usa
    FinancialSimulationController ..> FinancialSimulationResponseDTO : usa
    GamificationController ..> UserScoreDTO : usa
    GamificationController ..> ActivityType : usa