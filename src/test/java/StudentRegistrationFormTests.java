import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void successfulRegistration() {
        open("/automation-practice-form");

        $("#firstName").setValue("Ekaterina");
        $("#lastName").setValue("Zhdanova");
        $("#userEmail").setValue("katya@gmail.com");
        $x("//input[@value = 'Female']/following-sibling::label").click();
        $("#userNumber").setValue("9069069966");
//        $("#dateOfBirthInput").setValue("30 May 1986");
        $("#subjectsInput").setValue("java");
        $x("//label[text() = 'Music']").click();
        $("#currentAddress").setValue("Tomsk");
        $(byText("Select State")).click();
        $(byText("NCR")).click();
        $(byText("Select City")).click();
        $(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
//        $(".modal-header").shouldHave(text("Thanks for submitting the form"),
//        text("alex@egorov.com"),
//                text("Some address 1"), text("Another address 1"));
    }
}
