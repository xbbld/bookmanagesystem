import com.javaweb.demo4.servlet.LoginServlet;
import com.javaweb.demo4.util.DButil;
import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class LoginServletTest {
    @Mock
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    @Mock
    private HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @Mock
    private HttpSession session = Mockito.mock(HttpSession.class);

    @Mock
    private Connection conn = Mockito.mock(Connection.class);

    @Mock
    private PreparedStatement pstmt;

    @Mock
    private ResultSet rs;
    @Mock
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    private LoginServlet loginServlet;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        loginServlet = new LoginServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("login.jsp")).thenReturn(dispatcher);
        when(conn.prepareStatement(anyString())).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(rs);
    }

    @Test
    public void testDoPost_UserExists() throws ServletException, IOException, SQLException {
        // 设置模拟的请求参数
        when(request.getParameter("username")).thenReturn("user12345");
        when(request.getParameter("password")).thenReturn("123");
        when(request.getParameter("role")).thenReturn("user");

        // 执行doPost方法
        loginServlet.doPost(request, response);

        // 验证Session中存储的角色和用户名
        verify(session).setAttribute("role", "user");
        verify(session).setAttribute("username", "user12345");
        verify(session).setAttribute("id", "3");

        // 验证重定向到用户首页
        verify(response).sendRedirect(anyString());
    }

    @Test
    public void testDoPost_UserDoesNotExist() throws ServletException, IOException, SQLException {
        // 设置模拟的请求参数
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("");
        when(request.getParameter("role")).thenReturn("user");

        // 设置模拟的数据库查询结果
        when(rs.next()).thenReturn(false);

        // 执行doPost方法
        loginServlet.doPost(request, response);

        // 验证错误信息
        verify(request).setAttribute("error", "用户名或密码或身份选择错误！");
    }
}
