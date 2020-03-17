package me.wony.demobootweb;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;



    @Test
    public void hello() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/hello/wony"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("hello wony"));
    }

    @Test
    public void requestHello() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/requestHello").param("name","wony"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string("request wony"));
    }

    @Test
    public void helloStatic() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/mob/index.html"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Test")));
    }

    @Test
    public void stringMessage() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/message")
                .content("hello"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));
    }

    @Test
    public void jsonMessage() throws Exception{

        Person person = new Person();
        person.setId(2019L);
        person.setName("keesun");

        String jsonString = objectMapper.writeValueAsString(person);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/jsonMessage")
                    .contentType(MediaType.APPLICATION_JSON_UTF8) //내가 본문에 담을 타입
                    .accept(MediaType.APPLICATION_JSON_UTF8) // 응답을 바라는 타입
                    .content(jsonString))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
