package com.luckyboy.ui.cucumber.pages;

import com.luckyboy.ui.cucumber.pages.framework.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LotteryPage extends BasePage {

    @FindBy(xpath = "//a[text()='Home']")
    private WebElementFacade lotteryPage;

    public boolean isLotteryPageDisplayed(){
        return doesElementExist(lotteryPage);
    }

    public void capturesWinningNumbers() throws IOException {
        List<WebElementFacade> searchResults = findAll("//section[@class = 'results']/ul/li");

        List<String> strings = new ArrayList<String>();
        for (WebElementFacade e : searchResults) {
            strings.add(e.getText());
        }
        String[]nums = new String[strings.size()];
        strings.toArray(nums);
        int[]numbers = new int[nums.length];
        int[] used = new int[nums.length];
        int done = -1;
        int index = 0;

        for(int a = 0; a < nums.length; a++){
            try{
                numbers[a] = Integer.parseInt(nums[a]);
                index++;
            } catch (NumberFormatException nfe){
                System.out.print("Executing...");
            }
        }
        for (int i = 0; i < numbers.length; i++){
            int count = 1;
            for(int j = i+1; j < numbers.length; j++){
                if(numbers[i]==numbers[j]){
                    count++;
                    used[j] = done;
                }
            }
            if(used[i]!=done){
                used[i]= count;
            }
        }
        FileWriter writer = new FileWriter("lottery.txt");

        writer.write("Winning Number " + " | " + " Frequency" + System.lineSeparator());
        for (int i = 0; i < used.length; i++){
            if(used[i]!=done){
                writer.write(nums[i] + " | " +used[i] + System.lineSeparator());
            }
        }
        writer.close();
    }
}
