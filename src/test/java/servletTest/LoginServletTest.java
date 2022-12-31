package servletTest;

import com.example.registration.servletUser.RegistrServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServletTest {


    @Test
    void doPost() {
        RegistrServlet servlet = new RegistrServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test100");
        try {
            servlet.doPost(request,response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(request.getAttribute("status"),"success");

    }
}