package me.wony.demobootweb;

import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") Person person){
        return "hello " + person.getName();
    }

    @GetMapping("/requestHello")
    public String reqeuestHello(@RequestParam("name") Person person){
        return "request " + person.getName() ;
    }

    //preHandle 1
    //preHandle 2
    //요청처리
    //postHandler 2
    //postHAndler 2
    //뷰랜더링
    //afterCompletion 2
    //afterCompletion 1

    @GetMapping("/message")
    @ResponseBody
    public String message(@RequestBody String body){
        //@RequestBody 본문의 내용을 Http message Converter를 이용해
        return body;
    }

    @GetMapping("/jsonMessage")
    public Person jsonMessage(@RequestBody Person person){
        return person;
    }


}
