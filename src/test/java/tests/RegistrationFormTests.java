package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;

import java.util.Locale;

import static utils.RandomUtils.*;

public class RegistrationFormTests extends TestBase {

    @Test
    void successfulRegistration() {

        Faker faker = new Faker(new Locale("en"));

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                userEmail = faker.internet().emailAddress(),
                userGender = getRandomItemFromArray(genders),
                userNumber = "9" + faker.number().numberBetween(100000000, 999999999),
                // проще поменять заполнение поля даты, чем писать зависимость дней от месяцев
                userBirthDay = String.valueOf(faker.number().numberBetween(10, 28)),
                userMonthOfBirth = getRandomItemFromArray(months),
                userYearOfBirth = String.valueOf(faker.number().numberBetween(1900, 2020)),
                userSubjects = getRandomItemFromArray(subjects),
                userHobbies = getRandomItemFromArray(hobbies),
                currentAddress = faker.address().fullAddress(),
                userState = getRandomItemFromArray(states),
                userCity = getRandomItemFromArray(cities);


        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(userGender)
                .setMobile(userNumber)
                .setBirthDate(userBirthDay, userMonthOfBirth, userYearOfBirth)
                .setSubjects(userSubjects)
                .setHobbies(userHobbies)
                .uploadPicture("avatar.png")
                .setAddress(currentAddress)
                .setState(userState)
                .setCity(userCity)
                .clickSubmit();

        //$("#uploadPicture").uploadFile(new File("src/test/resources/avatar.png"));

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", userGender)
                .verifyResult("Mobile", userNumber)
                .verifyResult("Date of Birth", userBirthDay + " " + userMonthOfBirth + "," + userYearOfBirth)
                .verifyResult("Subjects", userSubjects)
                .verifyResult("Hobbies", userHobbies)
                .verifyResult("Picture", "avatar.png")
                .verifyResult("Address", currentAddress)
                .verifyResult("State and City", userState + " " +  userCity);
    }
}
