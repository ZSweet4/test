package com.luckyboy.ui.cucumber.features;

import com.luckyboy.ui.cucumber.steps.LotterySteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.thucydides.core.annotations.ClearCookiesPolicy;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;

import java.io.IOException;

public class LotteryFeature {
    @Managed( uniqueSession = true, clearCookies = ClearCookiesPolicy.BeforeEachTest )

    @Steps
    LotterySteps lotterySteps;

    @Given( "user navigates to the {string} lottery page" )
    public void userNavigatesToLotteryPage(String url) {
        lotterySteps.navigateToLotteryPage(url);
    }

    @And("user is on Lottery page")
    public void validateLotteryPage(){
        lotterySteps.validateLotteryPage();
    }

    @Then("page displays winning numbers")
    public void capturesWinningNumbers() throws IOException {
        lotterySteps.capturesWinningNumbers();
    }
}
