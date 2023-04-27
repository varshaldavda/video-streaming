package com.stream.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class StreamingController {

    public static final String LOCATION = "classpath:video/%s.mp4";

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/stream/{name}", produces = "video/mp4")
    public Mono<Resource> streamVideo(@PathVariable String name, @RequestHeader("Range") String range) {
        System.out.println(range);
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(LOCATION, name)));
    }
}
