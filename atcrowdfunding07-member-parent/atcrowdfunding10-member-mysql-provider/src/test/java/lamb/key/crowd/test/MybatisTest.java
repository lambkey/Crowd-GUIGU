package lamb.key.crowd.test;

import lamb.key.crowd.mapper.MemberPOMapper;
import lamb.key.crowd.po.MemberPO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author JoinYang
 * @date 2022/5/28 14:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class MybatisTest {
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private MemberPOMapper memberPOMapper;


    @Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        log.debug(connection.toString());
    }

    @Test
    public void testInsert(){
        MemberPO memberPO = new MemberPO();
        memberPO.setLoginacct("123");
        memberPO.setUserpswd("123");
        int insert = memberPOMapper.insert(memberPO);
        System.out.println(insert);
    }
}
