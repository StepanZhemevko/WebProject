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

class RegistrServletTest {

    @Test
    void doPost() {
        RegistrServlet servlet = new RegistrServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test100");
        when(request.getParameter("name_and_surname")).thenReturn("edit edit");
        when(request.getParameter("email")).thenReturn("edit");
        when(request.getParameter("telephone")).thenReturn("0991234444");

        try {
            servlet.doPost(request,response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}