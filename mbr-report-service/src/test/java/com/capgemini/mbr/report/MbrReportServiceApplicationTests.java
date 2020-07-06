package com.capgemini.mbr.report;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class MbrReportServiceApplicationTests {

 	 @Test
	void contextLoads() {
	}
	@Test
	public void applicationMainTest() {
		MbrReportServiceApplication.main(new String[] {});
	}
}
