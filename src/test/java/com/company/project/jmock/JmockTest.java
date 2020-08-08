package com.company.project.jmock;

//import com.company.project.demoUser.domain.DemoUser;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author： jkli
 * @Date： 2020/8/8 5:20 下午
 * @Description：
 **/
public class JmockTest {

    private Logger log = LoggerFactory.getLogger(JmockTest.class);

    @Test
    public void basicTypeTest(){
        //基本类型模拟
        int intNum = JMockData.mock(int.class);
        int[] intArray = JMockData.mock(int[].class);
        Integer integer = JMockData.mock(Integer.class);
        Integer[] integerArray = JMockData.mock(Integer[].class);
       //常用类型模拟
        BigDecimal bigDecimal = JMockData.mock(BigDecimal.class);
        BigInteger bigInteger = JMockData.mock(BigInteger.class);
        Date date = JMockData.mock(Date.class);
        String str = JMockData.mock(String.class);
        log.info(str);
    }
    @Test
    public void basicBeanTest(){
//        DemoUser demoUser = JMockData.mock(DemoUser.class);
//        Assert.assertNotNull(demoUser);
    }

    @Test
    //******注意TypeReference要加{}才能模拟******
    public void testTypeRefrence() {
        //模拟基础类型，不建议使用这种方式，参考基础类型章节直接模拟。
        Integer integerNum = JMockData.mock(new TypeReference<Integer>(){});
        Integer[] integerArray = JMockData.mock(new TypeReference<Integer[]>(){});
        //模拟集合
        List<Integer> integerList = JMockData.mock(new TypeReference<List<Integer>>(){});
        //模拟数组集合
        List<Integer[]> integerArrayList = JMockData.mock(new TypeReference<List<Integer[]>>(){});
        //模拟集合数组
        List<Integer>[] integerListArray = JMockData.mock(new TypeReference<List<Integer>[]>(){});
        //模拟集合实体
//        List<DemoUser> basicBeanList = JMockData.mock(new TypeReference<List<DemoUser>>(){});
        //各种组合忽略。。。。map同理。下面模拟一个不知道什么类型的map
        Map<List<Map<Integer, String[][]>>, Map<Set<String>, Double[]>> some = JMockData.mock(new TypeReference<Map<List<Map<Integer, String[][]>>, Map<Set<String>, Double[]>>>(){});
    }
    @Test
    public void configTest(){
        MockConfig mockConfig = new MockConfig()
                // 全局配置
                .globalConfig()
                .sizeRange(1,1)
                .charSeed((char) 97, (char) 98)
                .byteRange((byte) 0, Byte.MAX_VALUE)
                .shortRange((short) 0, Short.MAX_VALUE)

                // 某些字段（名等于integerNum的字段、包含float的字段、double开头的字段）配置
                .subConfig("integerNum","*float*","double*")
                .intRange(10, 11)
                .floatRange(1.22f, 1.50f)
                .doubleRange(1.50,1.99)

                // 某个类的某些字段（long开头的字段、date结尾的字段、包含string的字段）配置。
                .subConfig(BasicBean.class,"long*","*date","*string*")
                .longRange(12, 13)
                .dateRange("2018-11-20", "2018-11-30")
                .stringSeed("SAVED", "REJECT", "APPROVED")
                .sizeRange(1,1)

                // 全局配置
                .globalConfig()
                // 排除所有包含list/set/map字符的字段。表达式不区分大小写。
                .excludes("*List*","*Set*","*Map*")
                // 排除所有Array开头/Boxing结尾的字段。表达式不区分大小写。
                .excludes(BasicBean.class,"*Array","Boxing*");
        BasicBean basicBean = JMockData.mock(BasicBean.class, mockConfig);
        Assert.assertNotNull(basicBean);
    }
    @Test
    public void testDecimalScaleMock() {
        MockConfig mockConfig = new MockConfig()
                .doubleRange(-1.1d,9999.99999d)
                .floatRange(-1.11111f,9999.99999f)
                .decimalScale(3) // 设置小数位数为3，默认是2
                .globalConfig();
        BigDecimal mock = JMockData.mock(BigDecimal.class, mockConfig);
        Assert.assertNotNull(mock);
    }


    /**
     * 根据正则模拟数据
     * 正则优先于其他规则
     */
    @Test
    public void testRegexMock() {
//        MockConfig mockConfig = new MockConfig()
                // 随机段落字符串
//                .stringRegex("I'am a nice man\\.And I'll just scribble the characters, like：[a-z]{2}-[0-9]{2}-[abc123]{2}-\\w{2}-\\d{2}@\\s{1}-\\S{1}\\.?-.")
//                // 邮箱
//                .subConfig(RegexTestDataBean.class,"userEmail")
//                .stringRegex("[a-z0-9]{5,15}\\@\\w{3,5}\\.[a-z]{2,3}")
//                // 用户名规则
//                .subConfig(RegexTestDataBean.class,"userName")
//                .stringRegex("[a-zA-Z_]{1}[a-z0-9_]{5,15}")
//                // 年龄
//                .subConfig(RegexTestDataBean.class,"userAge")
//                .numberRegex("[1-9]{1}\\d?")
//                // 用户现金
//                .subConfig(RegexTestDataBean.class,"userMoney")
//                .numberRegex("[1-9]{2}\\.\\d?")
//                // 用户的得分
//                .subConfig(RegexTestDataBean.class,"userScore")
//                .numberRegex("[1-9]{1}\\d{1}")
//                // 用户身价
//                .subConfig(RegexTestDataBean.class,"userValue")
//                .numberRegex("[1-9]{1}\\d{3,8}")
//                .globalConfig();

    }
}
