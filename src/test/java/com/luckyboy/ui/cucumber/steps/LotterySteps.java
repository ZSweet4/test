package com.luckyboy.ui.cucumber.steps;
import com.luckyboy.ui.cucumber.pages.LotteryPage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.io.IOException;

public class LotterySteps {
    LotteryPage lotteryPage;

    @Step
    public void navigateToLotteryPage(String url) {
        lotteryPage.getDriver().get(url);
    }

    @Step
    public void validateLotteryPage(){
        Assert.assertTrue("Aquinas College should display", lotteryPage.isLotteryPageDisplayed());
    }

    @Step
    public void capturesWinningNumbers() throws IOException {
        lotteryPage.capturesWinningNumbers();
    }
}
