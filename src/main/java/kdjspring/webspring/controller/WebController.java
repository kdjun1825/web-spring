package kdjspring.webspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//annotation
@Controller
public class WebController {

    @GetMapping("hello")
    public String hello(Model model) {

        model.addAttribute("data", "spring!!!");
        return "hello";

    }

    
    //HTML방식
    //parameter 정보 ctrl + p
    //hello-mvc?name=ㅎㅇㅎㅇ!!
    //?name=   <==Get방식 http
    //뒤에 ㅎㅇㅎㅇ넣으면 String name 이 ㅎㅇㅎㅇ가 되고 html파일에서
    // key값이 name 인 것을 가져오니 ㅎㅇㅎㅇ가 온다
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {

        model.addAttribute("name", name);
        return "hello-template";
    }



    //API방식
    @GetMapping("hello-string")
    @ResponseBody //http 바디 부에 이 데이터를 그대로 직접 넣는다!
    public String helloString(@RequestParam("name2") String nameValue) {
        return "hello " + nameValue;
    }

    
    //Json방식으로 나오ㅁ
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}