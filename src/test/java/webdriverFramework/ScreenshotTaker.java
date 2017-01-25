package webdriverFramework;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by roman.pipchenko on 1/24/2017.
 */public class ScreenshotTaker extends TestWatcher {
    private final TakesScreenshot takesScreenshot;

    public ScreenshotTaker(TakesScreenshot takesScreenshot) {
        this.takesScreenshot = takesScreenshot;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        File screenshot = new File("target" + File.separator +  description.getClassName() + "-" + description.getMethodName() + new SimpleDateFormat("ddHHmmss").format(new Date()) + ".png");

        try {
            FileUtils.moveFile(takesScreenshot.getScreenshotAs(OutputType.FILE), screenshot);
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        }

        System.err.println("taken " + screenshot);
    }
}