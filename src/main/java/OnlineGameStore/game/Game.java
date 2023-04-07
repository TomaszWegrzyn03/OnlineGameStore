package OnlineGameStore.game;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Table @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(20)")
    @Enumerated(EnumType.STRING)
    private GameGenres genre;

    @Column( columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String developer;

    @Column(nullable = false, columnDefinition = "date")
    private LocalDate releaseDate;

    @Column(nullable = false, columnDefinition = "double")
    private double dollarPrice;
}
