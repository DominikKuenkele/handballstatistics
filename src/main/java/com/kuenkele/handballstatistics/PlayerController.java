package com.kuenkele.handballstatistics;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlayer(@RequestBody Player player){
        System.out.println(player);
    }
}
