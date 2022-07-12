package lamb.key.spring.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoinYang
 * @date 2022/5/16 14:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "student")
public class Student {
    private String stuName;
    private Integer stuAge;
    private String stuSex;
    private List<String> stuCourses;
    private String stuSchool;
    // 类中引用一个内部类，需要在该属性上加上注解
    @NestedConfigurationProperty
    private City city;
}
