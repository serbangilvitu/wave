package com.sgilvitu.wave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {

    Memory m;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "<html><body><table>"+
                "<tr><td>Endpoints</td></tr>" +
                "<tr><td>/actuator</td></tr>" +
                "<tr><td>/gc</td></tr>" +
                "<tr><td>/memory</td></tr>" +
                "</body></html>";
    }

    @GetMapping("/memory")
    public String memory(@RequestParam(value = "size", defaultValue = "0") int size) {
        if ((size > 0) || (m == null)) m = new Memory(size);
        return Integer.toString(m.getSize());
    }

    @RequestMapping("/gc")
    public String gc() {
        System.gc();
        return "GC";
    }

}
