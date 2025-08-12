package raff.stein.user.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import raff.stein.platformcore.model.audit.entity.BaseDateEntity;

@Entity
@Table(name = "user_settings")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSettingsEntity extends BaseDateEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationships

    @OneToOne(mappedBy = "userSettings")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private UserEntity user;

    // Fields

    @Column(length = 5)
    private String language; // ISO 639-1 code for language preference, eg: "it-IT", "en-US", etc.

    @Column(length = 50)
    private String theme; // "light" or "dark"

    // Additional settings can be added here as needed


}
