import com.javaweb.demo4.servlet.BookServlet;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServletTest {

    @Test
    public void testAddBook() throws IOException, ServletException {
        // 创建一个MockHttpServletRequest对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        // 添加请求参数
        request.setParameter("action", "add");
        request.setParameter("bookname", "Java Web Programming");
        request.setParameter("id", "1");
        request.setParameter("author", "John Smith");
        request.setParameter("publisher", "O'Reilly Media");
        request.setParameter("publishDate", "2021-01-01");
        request.setParameter("quantity", "10");
        request.setParameter("introduction", "This is a book about Java Web Programming.");
        request.setParameter("category", "Programming");



        // 创建一个MockHttpServletResponse对象
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建一个BookServlet对象
        BookServlet servlet = new BookServlet();

        // 调用doPost方法
        servlet.doPost(request, response);

        // 验证响应状态码
        assertEquals(302, response.getStatus());
    }

    @Test
    public void testDeleteBook() throws IOException, ServletException {
        // 创建一个MockHttpServletRequest对象
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        // 添加请求参数
        request.setParameter("action", "delete");
        request.setParameter("id", "1");

        // 创建一个MockHttpServletResponse对象
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建一个BookServlet对象
        BookServlet servlet = new BookServlet();

        // 调用doPost方法
        servlet.doPost(request, response);

        // 验证响应状态码
        assertEquals(302, response.getStatus());
    }

    @Test
    public void testUpdateBook() throws IOException, ServletException {
        // 创建一个MockHttpServletRequest对象
        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();
        request.setCharacterEncoding("UTF-8");

        // 添加请求参数
        request.setParameter("action", "update");
        request.setParameter("id", "1");
        request.setParameter("bookname", "Java Web Programming");
        request.setParameter("author", "John Smith");
        request.setParameter("publisher", "O'Reilly Media");
        request.setParameter("publishDate", "2021-01-01");
        request.setParameter("quantity", "10");
        request.setParameter("introduction", "This is a book about Java Web Programming.");
        request.setParameter("category", "Programming");

        // 添加文件上传参数
        Path path = Paths.get("src/test/resources/test.jpg");
        byte[] data = Files.readAllBytes(path);
        MockMultipartFile file = new MockMultipartFile("fileToUpload", "test.jpg", "image/jpeg", data);
        request.addFile(file);

        // 创建一个MockHttpServletResponse对象
        MockHttpServletResponse response = new MockHttpServletResponse();

        // 创建一个BookServlet对象
        BookServlet servlet = new BookServlet();

        // 调用doPost方法
        servlet.doPost(request, response);

        // 验证响应状态码
        assertEquals(302, response.getStatus());
    }
}
