package com.junit5;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 需要导入ObjectMapper的三个jar包
 */

public class TestYaml {

    /* methodsource 字符串 参数化*/
    @ParameterizedTest
    @MethodSource
    public void  testddt(String raw){
        assertTrue(raw.length() > 3);
    }

    static Stream<String> testddt(){
        return Stream.of("1","12","123","1234");
    }



    /*methodsource yaml文件 参数化
    * import com.fasterxml.jackson.core.type.TypeReference; 这个包之前倒错了*/
    @ParameterizedTest()
    @MethodSource
    public void  testDDTFormYaml(User user){
        assertTrue(user.name.length() > 3);
    }


    //数据驱动
    static List<User> testDDTFormYaml() throws IOException{
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference  typeReference = new TypeReference<List<User>>(){

        };

        List<User> users = mapper.readValue(
                TestYaml.class.getResourceAsStream("/user.yaml"),
                typeReference
        );
        return users;
    }


    /**
     * /*methodsource json文件参数化  驱动
     * @param user
     */
    @ParameterizedTest
    @MethodSource
    public  void testDDTFromJson(User user){
        assertTrue(user.name.length() > 3);
    }

    static  List<User> testDDTFromJson() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        TypeReference typeReference = new TypeReference<List<User>>(){
        };

        List<User> users = mapper.readValue(
               TestYaml.class.getResourceAsStream("/user.json"),
                typeReference
        );
        return users;
    }

}
