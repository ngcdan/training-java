package training.java.app.server.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1.0.0/demo")
public class DemoController {

  @GetMapping("hello")
  public @ResponseBody String hello() {
    return "hello";
  };
}