package ru.StepDefinitions;

import cucumber.api.java.en.And;
import ru.PageObjects.CommonPage;

/**
 * Общие шаги для всех тестов по навигации в браузере и основном меню приложения.
 * Created by Vladimir V. Klochkov on 27.07.2017.
 * Updated by Vladimir V. Klochkov on 25.02.2021.
 */
public class NavigationStepDefinitions extends AbstractStepDefinitions {
    /*******************************************************************************************************************
     * Поля класса.
     ******************************************************************************************************************/
    private final CommonPage commonPage = new CommonPage(driver);

    /*******************************************************************************************************************
     * Методы класса.
     ******************************************************************************************************************/
    @And("'Пользователь' обновляет страницу")
    public void userPerformsPageRefresh() throws Throwable {
        String stepName = "'Пользователь' обновляет страницу";
        this.logStepName(stepName);

        timer.start();

        commonPage.refreshPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' перемещается к началу страницы$")
    public void userGoesToTheTopOfPage() throws Throwable {
        String stepName = "'Пользователь' перемещается к началу страницы";
        this.logStepName(stepName);

        timer.start();

        commonPage.goToPageTop();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' перемещается к концу страницы$")
    public void userScrollsDownToTheEndOfPage() throws Throwable {
        String stepName = "'Пользователь' перемещается к концу страницы";
        this.logStepName(stepName);

        timer.start();

        String version = commonPage.getCurrentServerVersion();
        System.out.printf("[ИНФОРМАЦИЯ]: произведен переход к концу страницы [%s].%n", version);

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' сохраняет указатель на главное окно приложения$")
    public void userStoresMainApplicationWindowHandle() {
        String stepName = "'Пользователь' сохраняет указатель на главное окно приложения";
        this.logStepName(stepName);

        timer.start();

        config.setMainWindowHandle(driver.getWindowHandle());

        timer.printStepTotalTime(stepName);
    }

    @And("'Пользователь' переключается на новую вкладку в браузере")
    public void userSwitchesToNewTabInBrowser() throws Throwable {
        String stepName = "'Пользователь' переключается на новую вкладку в браузере";
        this.logStepName(stepName);

        timer.start();

        config.switchToNewWindowInBrowser();
        commonPage.waitForPageLoad();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' возращается на предыдущую страницу приложения$")
    public void userGoesBackToPreviousPage() throws Throwable {
        String stepName = "'Пользователь' возращается на предыдущую страницу приложения";
        this.logStepName(stepName);

        timer.start();

        commonPage.goBackToPreviousPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' возращается на \"([^\"]*)\" предыдущих страниц приложения$")
    public void userGoesBackToPreviousPages(String numberOfPages) throws Throwable {
        String stepName =
                String.format("'Пользователь' возращается на '%s' предыдущих страниц приложения", numberOfPages);
        this.logStepName(stepName);

        timer.start();

        int number = Integer.parseInt(numberOfPages);
        for (int i = 0; i < number; i++)
            commonPage.goBackToPreviousPage();

        timer.printStepTotalTime(stepName);
    }

    @And("^'Пользователь' закрывает все дополнительные окна в приложении$")
    public void userClosesAllExtraWindowsInApplication() throws Throwable {
        String stepName = "'Пользователь' закрывает все дополнительные окна в приложении";
        this.logStepName(stepName);

        timer.start();

        config.closeActiveWindowsAndSwitchToMainWindowInBrowser(driver);

        timer.printStepTotalTime(stepName);
    }
}
