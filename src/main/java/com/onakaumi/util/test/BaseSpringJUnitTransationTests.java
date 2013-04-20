package com.onakaumi.util.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:resources/spring/applicationContext.xml")
public class BaseSpringJUnitTransationTests extends
		AbstractTransactionalJUnit4SpringContextTests {

}
