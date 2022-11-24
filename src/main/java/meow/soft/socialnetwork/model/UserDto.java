package meow.soft.socialnetwork.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link User} entity
 */
@Data
public class UserDto implements Serializable {
    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String sex;
    private final LocalDate birthDate;
    private final String city;
    private final String photoUrl;
    private final String about;
    private final String nickName;
    private final String skills;
    private final String mail;
    private final String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto dto = (UserDto) o;
        return Objects.equals(id, dto.id)
                && Objects.equals(firstName, dto.firstName)
                && Objects.equals(lastName, dto.lastName)
                && Objects.equals(patronymic, dto.patronymic)
                && Objects.equals(sex, dto.sex)
                && Objects.equals(birthDate, dto.birthDate)
                && Objects.equals(city, dto.city)
                && Objects.equals(photoUrl, dto.photoUrl)
                && Objects.equals(about, dto.about)
                && Objects.equals(nickName, dto.nickName)
                && Objects.equals(skills, dto.skills)
                && Objects.equals(mail, dto.mail)
                && Objects.equals(phone, dto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, patronymic, sex, birthDate, city, photoUrl, about, nickName, skills, mail, phone);
    }
}