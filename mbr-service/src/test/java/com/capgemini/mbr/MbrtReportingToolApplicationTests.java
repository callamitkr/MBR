package com.capgemini.mbr;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
//@SpringBootTest
class MbrtReportingToolApplicationTests {

	@Test
	void contextLoads() {

	}
	@Test
	public void applicationMainTest() {
		MbrToolApplication.main(new String[] {});
	}
}
