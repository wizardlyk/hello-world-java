package zz;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lyk
 * @Date 2019/7/3 21:01
 * @Version 1.0
 **/
@RestController
@RequestMapping("/puppy")
public class PuppyController {
    @PostMapping
    public Puppy create(@Valid @RequestBody Puppy puppy) {
        System.out.println(puppy.getName());
        System.out.println(puppy.getAge());
        puppy.setAge(10);
        return puppy;
    }

    @PostMapping("/v1")
    public Puppy jsonTest(@RequestBody Puppy puppy) {
        System.out.println(puppy.getName());
        System.out.println(puppy.getAge());
        System.out.println(puppy.getEndTime());
        System.out.println(puppy);
        return puppy;
    }
}
