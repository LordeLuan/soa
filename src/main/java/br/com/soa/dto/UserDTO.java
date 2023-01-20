package br.com.soa.dto;

import br.com.soa.domain.enums.Profile;
import lombok.*;

import java.time.LocalDateTime;

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
    private Integer profile;

//    public Profile getProfile() {
//        return Profile.toEnum(profile);
//    }
//
//    public void setProfile(Profile profile) {
//        this.profile = profile.getCode();
//    }
}
