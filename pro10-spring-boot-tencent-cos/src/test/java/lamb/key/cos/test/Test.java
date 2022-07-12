package lamb.key.cos.test;

import lamb.key.cos.utils.UploadUtils;
import net.seehope.crowd.util.JSONResult;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author JoinYang
 * @date 2022/6/9 16:49
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\砺锋\\第三阶段项目\\尚筹网\\pro10-spring-boot-tencent-cos\\QQ截图20220609152158.png");
        FileInputStream fileInputStream =new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(),file.getName(),null,fileInputStream);

        JSONResult<String> stringJSONResult = UploadUtils.doUpload("AKIDCE2bv0qQlqgJisnS3PWO0NVN0NVN0NVN", "f4GDvNaYKsBE3d1B6lBLGvLXUCeroVx9",
                "ap-guangzhou", "lambkey-1309051782", "https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com", multipartFile);
        System.out.println(stringJSONResult.getData());
    }
}
