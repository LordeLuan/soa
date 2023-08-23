package br.com.soa.dto;

import br.com.soa.domain.enums.Profile;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExerciseDTO {

    private Integer id;
    private String name;
    private Integer serie;
    private Integer repetitionSerie;
    private Integer time;
    private Double weight;

}
