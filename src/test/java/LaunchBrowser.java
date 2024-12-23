import org.testng.annotations.Test;

public class LaunchBrowser extends Base {

    @Test
    public void setUp(){
        launchBrowser("https://www.google.com","firefox");
    }

}
