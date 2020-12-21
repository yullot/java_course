package qa.pkg.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GeoIpServiceTests {
  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("46.53.249.183");
    assertTrue(ipLocation.contains("BY"));
  }
}
