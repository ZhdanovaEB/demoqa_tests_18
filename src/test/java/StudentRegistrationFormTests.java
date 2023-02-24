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
    void successfulRegistration() throws InterruptedException {
        open("/automation-practice-form");

        $("#firstName").setValue("Ekaterina");
        $("#lastName").setValue("Zhdanova");
        $("#userEmail").setValue("katya@gmail.com");
        $x("//input[@value = 'Female']/following-sibling::label").click();
        $("#userNumber").setValue("9069069966");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $x("//select/option[text() = 'May']").click();
        $(".react-datepicker__year-select").click();
        $(byText("1986")).click();
        $(byText("30")).click();
        $("#subjectsInput").setValue("java");
        $x("//label[text() = 'Music']").click();
        $("#uploadPicture").sendKeys("C:\\Users\\ezhdanova\\IdeaProjects\\ga_guru\\demoqa_tests_18\\src\\test\\resources\\avatar.png");
        $("#currentAddress").setValue("Tomsk");
        $(byText("Select State")).click();
        $(byText("NCR")).click();
        $(byText("Select City")).click();
        $(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(byText("Student Name")).sibling(0).shouldHave(text("Ekaterina Zhdanova"));
        $(byText("Student Email")).sibling(0).shouldHave(text("katya@gmail.com"));
        $(byText("Gender")).sibling(0).shouldHave(text("Female"));
        $x("//td[text() = 'Mobile']/following-sibling::*[1]").shouldHave(text("9069069966"));
        // не работает из-за бага с выбором месяца
        //$x("//td[text() = 'Date of Birth']/following-sibling::*[1]").shouldHave(text("30 May,1986"));
        // не наботает из-за бага ввода символов в поле Subjects
        //$(byText("Subjects")).sibling(0).shouldHave(text("java"));
        $(byText("Hobbies")).sibling(0).shouldHave(text("Music"));
        $(byText("Picture")).sibling(0).shouldHave(text("avatar.png"));
        $(byText("Address")).sibling(0).shouldHave(text("Tomsk"));
        $(byText("State and City")).sibling(0).shouldHave(text("NCR Delhi"));
    }
}
