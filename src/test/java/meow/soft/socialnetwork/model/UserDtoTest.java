package meow.soft.socialnetwork.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

class UserDtoTest {
    //Field id of type UUID - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field birthDate of type LocalDate - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    UserDto userDto = new UserDto(null, "firstName", "lastName", "patronymic", "sex", LocalDate.of(2022, Month.NOVEMBER, 25), "city", "photoUrl", "about", "nickName", "skills", "mail", "phone");

    @Test
    void testEquals() {
        EqualsVerifier.simple().forClass(UserDto.class).verify();
    }


    @Test
    void testToString() {
        String result = userDto.toString();
        Assertions.assertEquals("UserDto(id=null, firstName=firstName, lastName=lastName, patronymic=patronymic, sex=sex, birthDate=2022-11-25, city=city, photoUrl=photoUrl, about=about, nickName=nickName, skills=skills, mail=mail, phone=phone)", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme