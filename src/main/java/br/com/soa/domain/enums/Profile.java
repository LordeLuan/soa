package br.com.soa.domain.enums;

public enum Profile {

    ADMIN(0, "ADMIN"), STUDENT(1, "STUDENT"), INSTRUCTOR(2, "INSTRUCTOR");

    private Integer code;
    private String description;

    private Profile(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Profile toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
//		Percerro os valores do enum para localizar a qual valor o código passado se refere
        for (Profile x : Profile.values()) {
            if (cod.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Profile inválido");
    }
}
