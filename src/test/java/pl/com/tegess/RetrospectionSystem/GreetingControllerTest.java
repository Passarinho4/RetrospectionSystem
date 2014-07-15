package pl.com.tegess.RetrospectionSystem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class GreetingControllerTest {

    private static final String greetingView = "/WEB-INF/templates/greeting.html";


    MockMvc mockMvc;

    @InjectMocks
    GreetingController controller;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller).setViewResolvers(viewResolver()).build();
    }

    private InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/templates/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Test
    public void testGreeting() throws Exception {
        mockMvc.perform(get("/greeting")).andExpect(forwardedUrl(greetingView));
    }
}