package com.qingjiang.common;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationBaseTest {

    @Before
    public void before() {
        log.info("===== 测试开始 =====");
    }


    @After
    public void after() {
        log.info("===== 测试结束 =====");
    }

}
