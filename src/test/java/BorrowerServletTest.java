import com.javaweb.demo4.servlet.BorrowerServlet;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowerServletTest {

    @Test
    public void testDeleteUser() throws ServletException, IOException {
        // 创建一个MockHttpServletRequest对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        // 添加请求参数
        request.setParameter("action", "delete");
        request.setParameter("id", "1");

        // 创建一个MockHttpServletResponse对象
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建一个BorrowerServlet对象
        BorrowerServlet servlet = new BorrowerServlet();

        // 调用doPost方法
        servlet.doPost(request, response);

        // 验证响应状态码
        assertEquals(302, response.getStatus());
    }

    @Test
    public void testUpdateUser() throws ServletException, IOException {
        // 创建一个MockHttpServletRequest对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        // 添加请求参数
        request.setParameter("action", "update");
        request.setParameter("id", "1");
        request.setParameter("username", "newUsername");
        request.setParameter("realname", "newRealname");
        request.setParameter("phone", "newPhone");
        request.setParameter("email", "newEmail");
        request.setParameter("address", "newAddress");

        // 创建一个MockHttpServletResponse对象
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建一个BorrowerServlet对象
        BorrowerServlet servlet = new BorrowerServlet();

        // 调用doPost方法
        servlet.doPost(request, response);

        // 验证响应状态码
        assertEquals(302, response.getStatus());
    }
}
