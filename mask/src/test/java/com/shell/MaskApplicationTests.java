package com.shell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
  
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    {
    "classpath:mvc-dispatcher-servlet.xml",
    "classpath:repository-context.xml"
})

@TransactionConfiguration(defaultRollback=true)
public class MaskApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("123");
	}

}
