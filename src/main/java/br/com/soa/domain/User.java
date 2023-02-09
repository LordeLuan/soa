package br.com.soa.domain;

import br.com.soa.domain.enums.Profile;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private Double weight;
    private Double height;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    private Profile profile;

}
