import com.javaweb.demo4.servlet.SavePersonalInfoServlet;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SavePersonalInfoServletTest {

    @Test
    public void testSavePersonalInfo() throws ServletException, IOException {
        // 创建一个MockHttpServletRequest对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        // 添加请求参数
        request.setParameter("phone", "1234567890");
        request.setParameter("email", "test@example.com");
        request.setParameter("address", "123 Test Street");
        request.setParameter("username", "testUser");

        // 创建一个MockHttpSession对象
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", "1");

        // 将session添加到request中
        request.setSession(session);

        // 创建一个MockHttpServletResponse对象
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建一个SavePersonalInfoServlet对象
        SavePersonalInfoServlet servlet = new SavePersonalInfoServlet();

        // 调用doPost方法
        servlet.doPost(request, response);

        // 验证响应状态码
        assertEquals(302, response.getStatus());
    }
}
