package br.com.soa.dto;

import br.com.soa.domain.Exercise;
import br.com.soa.domain.enums.Profile;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer age;
    private Double weight;
    private Double height;
    private LocalDateTime updateDate;
    private Profile profile;

    private List<Exercise> exercises;

}
