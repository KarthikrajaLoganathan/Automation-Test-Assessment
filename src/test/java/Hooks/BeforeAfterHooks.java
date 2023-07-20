package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import static Pages.APIAction.connection;

public class BeforeAfterHooks {


    @Before
    public void initialization()  {
        System.out.println("API test starts...!");

    }

    @After
    public void tearDown() throws Exception {
        connection.disconnect();
        System.out.println("TEAR DOWN");
    }
    
}
