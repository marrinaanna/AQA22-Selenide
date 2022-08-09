package ru.netology.card;

import org.apache.hc.client5.http.auth.KerberosCredentials;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CardDeliveryTest {

    public String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }


    @Test
    void shouldCallBack() {
        String planningDate = generateDate(5);
        open("http://localhost:9999");
        $(By.cssSelector("[data-test-id='city'] input")).setValue("Йошкар-Ола");
        $(By.cssSelector("[data-test-id='date'] input")).click();
        $(By.cssSelector("[data-test-id='date'] input")).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        $(By.cssSelector("[data-test-id='date'] input")).setValue(planningDate);
        $(By.cssSelector("[data-test-id='name'] input")).setValue("Мамин-Сибиряк Валентин");
        $(By.cssSelector("[data-test-id='phone'] input")).setValue("+77777777777");
        $(By.cssSelector("[data-test-id='agreement']")).click();
        $x("//*[text()='Забронировать']").click();
        $x("//*[contains(text(),'Успешно!')]").shouldBe(visible, Duration.ofSeconds(15));
        $(By.cssSelector("[data-test-id='notification']")).shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(visible);

    }

}


