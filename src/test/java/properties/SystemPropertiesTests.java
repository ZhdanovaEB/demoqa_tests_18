package properties;

import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
    @Test
    public void simplePropertyTest() {
        String browser = System.getProperty("browser");
    }
}
