package wave;

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

    private String htmlWrapper(List<String> list) {
        String content = "";
        for (String s : list) {
            content = content + String.format("<p>%s</p>", s);
        }
        return "<html><body>" + content + "</body></html>";
    }

    @RequestMapping("/")
    public String home() {
        return "Hello";
    }

    @GetMapping("/memory")
    public String memory(@RequestParam(value = "size", defaultValue = "0") int size) {
        if ((size>0) || (m == null))  m = new Memory(size);
        return htmlWrapper(
                Arrays.asList("requestedSize[MiB]="+Integer.toString(m.getSize()),
                m.getHeapFreeSize(),
                m.getHeapSize(),
                m.getHeapMaxSize())
        );
    }

    @RequestMapping("/gc")
    public String gc() {
        System.gc();
        return htmlWrapper(Arrays.asList("GC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
