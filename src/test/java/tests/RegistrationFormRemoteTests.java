package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.Locale;

import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.*;

@Tag("remote")
public class RegistrationFormRemoteTests extends RemoteTestBase {

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
                currentAddress = faker.address().secondaryAddress(),
                userState = getRandomItemFromArray(states),
                userCity = getRandomItemFromArray(cities);

        step("Open form", () -> {
        registrationPage.openPage();
        });
        step("Fill form", () -> {
            registrationPage.setFirstName(firstName)
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
        });
        step("Verify results", () -> {
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
        });
    }
}
