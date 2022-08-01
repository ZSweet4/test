package com.luckyboy.ui.cucumber.pages.framework;

//import net.serenitybdd.core.pages.TestObject;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This abstract class is extended to represent Pages for an app under test.
 * <p>
 * This package is an implementation of the Page Object Design Pattern: https://martinfowler.com/bliki/PageObject.html,
 * https://github.com/SeleniumHQ/selenium/wiki/PageObjects
 * <p>
 * All WebElements and their helper methods are to be handled in the scope of Page and its children
 * <p>
 */
public abstract class BasePage extends PageObject {
    private static final Logger logger = LoggerFactory.getLogger( BasePage.class );
    private static final String ELEMENT_STALE = "Element was stale. Trying to find it again...";

    /**
     * Framework method for typing text
     *
     * @param element
     * @param text
     */
    public void typeOnElement( WebElementFacade element, String text ) {
        element.waitUntilVisible().waitUntilEnabled();
        element.clear();
        element.type( text );
    }

    public void typeKeys( WebElementFacade element, Keys key ) {
        element.waitUntilVisible().waitUntilEnabled();
        element.type(String.valueOf(key));
    }

    /**
     * Wrapper method for getting element text
     *
     * @param element
     * @return
     */
    public String getElementText( WebElementFacade element ) {
        element.waitUntilPresent().waitUntilVisible();
        return element.getText();
    }

    /**
     * Wrapper method for getting element text
     *
     * @param element
     * @return
     */
    public String getElementTextContent( WebElementFacade element ) {
        element.waitUntilPresent().waitUntilVisible();
        return element.getTextContent();
    }

    /**
     * Wrapper method for checking if an element exists
     *
     * @param element
     * @return
     */
    public Boolean doesElementExist( WebElementFacade element ) {
        try {
            return element.waitUntilVisible().isPresent();
        } catch ( NoSuchElementException e ) {
            return false;
        }
    }


    public boolean waitForElementText( By textLocator, String text ) {
        long timeout = getTimeout();
        return waitForElementText( textLocator, text, timeout );
    }

    public boolean waitForElementText( WebElement webElement, String text ) {
        long timeout = getTimeout();
        return waitForElementText( webElement, text, timeout );
    }

    public boolean waitForElementText( By textLocator, String text, long timeout ) {
        WebDriverWait wait = new WebDriverWait( getDriver(), timeout, getRetryInterval() );
        logger.info( "Checking whether or not " + textLocator.toString() + " is has text '" + text + "'..." );
        try {
            wait.until( ExpectedConditions.textToBePresentInElementLocated( textLocator, text ) );
            logger.debug( String.format( "Element with selector: %s now has text: %s",
                    textLocator.toString(), text ) );
            return true;
        } catch ( TimeoutException e ) {
            logger.debug( String.format( "Element with selector: %s never got requested text after waiting %d seconds!",
                    textLocator.toString(), timeout ) );
            return false;
        } catch ( StaleElementReferenceException exception ) {
            logger.debug( ELEMENT_STALE );
            return waitForElementText( textLocator, text );
        }
    }

    public boolean waitForElementText( WebElement webElement, String text, long timeout ) {
        WebDriverWait wait = new WebDriverWait( getDriver(), timeout, getRetryInterval() );
        logger.info( "Checking whether or not " + webElement.getText() + " has text '" + text + "'..." );
        try {
            wait.until( ExpectedConditions.textToBePresentInElement( webElement, text ) );
            logger.debug( String.format( "Element with selector: %s now has text: %s",
                    webElement.getText(), text ) );
            return true;
        } catch ( TimeoutException e ) {
            logger.debug( String.format( "Element with selector: %s never got requested text after waiting %d seconds!",
                    webElement.getText(), timeout ) );
            return false;
        } catch ( StaleElementReferenceException exception ) {
            logger.debug( ELEMENT_STALE );
            return waitForElementText( webElement, text );
        }
    }

    protected boolean waitForElementToBePresent( By locator, long timeout ) {
        try {
            WebDriverWait wait = new WebDriverWait( getDriver(), timeout, getRetryInterval() );
            logger.info( "Waiting for element '" + locator.toString() + "' to appear..." );
            wait.until( ExpectedConditions.visibilityOf( element( locator ) ) );
            logger.debug( "Element '" + locator.toString() + "' disappeared!" );
            return true;
        } catch ( NoSuchElementException e ) {
            logger.debug( "No such element '" + locator.toString() + "' exists!" );
            return true;
        } catch ( TimeoutException e ) {
            logger.debug(
                    String.format( "Element with selector: %s didn't disappeared after waiting %d seconds!",
                            locator.toString(),
                            timeout )
            );
            return false;
        }
    }

    protected boolean waitForElementNotPresentAnymore( By locator, long timeout ) {
        try {
            WebDriverWait wait = new WebDriverWait( getDriver(), timeout, getRetryInterval() );
            logger.info( "Waiting for element '" + locator.toString() + "' to disappear..." );
            wait.until( ExpectedConditions.invisibilityOfElementLocated( locator ) );
            logger.debug( "Element '" + locator.toString() + "' disappeared!" );
            return true;
        } catch ( NoSuchElementException e ) {
            logger.debug( "No such element '" + locator.toString() + "' exists!" );
            return true;
        } catch ( TimeoutException e ) {
            logger.debug(
                    String.format( "Element with selector: %s didn't disappeared after waiting %d seconds!",
                            locator.toString(),
                            timeout )
            );
            return false;
        }
    }

    private long getRetryInterval() {
        return (long) 2000;
    }

    private long getTimeout() {
        return (long) 2000;
    }

    public boolean waitForUrl( String url ) {
        long timeout = getTimeout();
        return waitForUrl( url, timeout );
    }

    public boolean waitForUrlContains( String url ) {
        long timeout = getTimeout();
        return waitForUrlContains( url, timeout );
    }

    public boolean waitForUrl( String url, long timeout ) {
        WebDriverWait wait = new WebDriverWait( getDriver(), timeout, getRetryInterval() );
        logger.info( "Checking whether or not URL matches '{}'...", url );
        try {
            wait.until( ExpectedConditions.urlMatches( url ) );
            logger.debug( "URL '{}' matches '{}'.", getDriver().getCurrentUrl(), url );
            return true;
        } catch ( TimeoutException e ) {
            logger.debug( "URL '{}' does not match requested URL '{}' after waiting {} seconds!.",
                    getDriver().getCurrentUrl(), url, timeout );
            return false;
        }
    }

    public boolean waitForUrlContains( String url, long timeout ) {
        WebDriverWait wait = new WebDriverWait( getDriver(), timeout, getRetryInterval() );
        logger.info( "Checking whether or not URL contains '" + url + "'..." );
        try {
            wait.until( ExpectedConditions.urlContains( url ) );
            logger.debug( "URL '{}' contains: '{}'", getDriver().getCurrentUrl(), url );
            return true;
        } catch ( TimeoutException e ) {
            logger.debug( "URL '{}' does not contain requested text '{}' after waiting {} seconds!",
                    getDriver().getCurrentUrl(), url, timeout );
            return false;
        }
    }

}
