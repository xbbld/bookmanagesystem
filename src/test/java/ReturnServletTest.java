import com.javaweb.demo4.servlet.ReturnServlet;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturnServletTest {

    @Test
    public void testReturnBook() throws ServletException, IOException {
        // 创建一个MockHttpServletRequest对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        // 添加请求参数
        request.setParameter("borrow_id", "1");
        request.setParameter("book_id", "1");

        // 创建一个MockHttpServletResponse对象
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建一个ReturnServlet对象
        ReturnServlet servlet = new ReturnServlet();

        // 调用doPost方法
        servlet.doPost(request, response);

        // 验证响应状态码
        assertEquals(200, response.getStatus());
    }
}
