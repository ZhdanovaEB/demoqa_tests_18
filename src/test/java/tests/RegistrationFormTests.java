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
                userNumber = "9" + faker.phoneNumber(),
                // проще поменять заполнение поля даты, чем писать зависимость дней от месяцев
                userBirthDay = String.valueOf(faker.number().numberBetween(1, 28)),
                userMonthOfBirth = getRandomItemFromArray(months),
                userYearOfBirth = String.valueOf(faker.number().numberBetween(1900, 2020)),
                currentAddress = faker.address().fullAddress();


        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(userGender)
                .setMobile(userNumber)
                .setBirthDate(userBirthDay, userMonthOfBirth, userYearOfBirth)
                .setSubjects("Math")
                .setHobbies("Music")
                .uploadPicture("avatar.png")
                .setAddress(currentAddress)
                .setState("Select State", "NCR")
                .setCity("Select City", "Delhi")
                .clickSubmit();

        //$("#uploadPicture").uploadFile(new File("src/test/resources/avatar.png"));

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", userEmail)
                .verifyResult("Gender", userGender)
                .verifyResult("Mobile", userNumber)
                .verifyResult("Date of Birth", userBirthDay + " " + userMonthOfBirth + "," + userYearOfBirth)
                .verifyResult("Subjects", "Maths")
                .verifyResult("Hobbies", "Music")
                .verifyResult("Picture", "avatar.png")
                .verifyResult("Address", currentAddress)
                .verifyResult("State and City", "NCR Delhi");
    }
}
