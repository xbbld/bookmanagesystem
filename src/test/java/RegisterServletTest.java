import com.javaweb.demo4.servlet.RegisterServlet;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterServletTest {

    @Test
    public void testRegister() throws ServletException, IOException {
        // 创建一个MockHttpServletRequest对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        // 添加请求参数
        request.setParameter("username", "testUser");
        request.setParameter("password", "testPassword");
        request.setParameter("confirmPassword", "testPassword");
        request.setParameter("realname", "Test User");
        request.setParameter("phone", "1234567890");
        request.setParameter("email", "test@example.com");
        request.setParameter("address", "123 Test Street");

        // 创建一个MockHttpServletResponse对象
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建一个RegisterServlet对象
        RegisterServlet servlet = new RegisterServlet();

        // 调用doPost方法
        servlet.doPost(request, response);

        // 验证响应状态码
        assertEquals(302, response.getStatus());
    }
}
