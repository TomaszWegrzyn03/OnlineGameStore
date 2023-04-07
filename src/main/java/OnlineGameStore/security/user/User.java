package OnlineGameStore.security.user;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String username;

    @Column( nullable = false,columnDefinition = "varchar(300)")
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
