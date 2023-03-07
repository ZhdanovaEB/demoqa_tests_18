package tests;

import org.junit.jupiter.api.Test;

public class RegistrationFormTests extends TestBase {

    @Test
    void successfulRegistration() {
        registrationPage.openPage()
                .setFirstName("Ekaterina")
                .setLastName("Zhdanova")
                .setEmail("katya@gmail.com")
                .setGender("Female")
                .setMobile("9069069966")
                .setBirthDate("30", "May", "1986")
                .setSubjects("Math")
                .setHobbies("Music")
                .uploadPicture("avatar.png")
                .setAddress("Tomsk")
                .setState("Select State", "NCR")
                .setCity("Select City", "Delhi")
                .clickSubmit();

        //$("#uploadPicture").uploadFile(new File("src/test/resources/avatar.png"));

        registrationPage.verifyResultsModalAppears()
                .verifyResult("Student Name", "Ekaterina Zhdanova")
                .verifyResult("Student Email", "katya@gmail.com")
                .verifyResult("Gender", "Female")
                .verifyResult("Mobile", "9069069966")
                .verifyResult("Date of Birth", "30 May,1986")
                .verifyResult("Subjects", "Maths")
                .verifyResult("Hobbies", "Music")
                .verifyResult("Picture", "avatar.png")
                .verifyResult("Address", "Tomsk")
                .verifyResult("State and City", "NCR Delhi");
    }
}
