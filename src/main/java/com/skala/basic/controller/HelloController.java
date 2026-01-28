package com.skala.basic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skala.basic.data.CourseResponse;
import com.skala.basic.data.HelloRequest;
import com.skala.basic.data.HelloResponse;
import com.skala.basic.service.CourseService;
import com.skala.basic.service.HelloService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j // 로그를 남길 수 있는 어노테이션
@RequiredArgsConstructor
public class HelloController {

  private final HelloService helloService;
  private final CourseService courseService;



  @GetMapping("/hello")
  public HelloResponse getHello(@RequestParam String name, @RequestParam int age) {
    // log.debug("getHello: name={}, age={}, name, age");
    // log.info("getHello: name={}, age={}, name, age");
    // log.error("getHello: name={}, age={}, name, age");
    HelloResponse response = helloService.createMessage(name, age);
    System.out.print(response);  // Param으로 전달되기 때문에, 사전에 지정한 값들만 받아옴
    return response;
  }

  @PostMapping("/hello")
  public HelloResponse postHello(@RequestBody HelloRequest body) {
    HelloResponse response = helloService.createMessage(body.getName(), 25);
    return response;
  }

  //@GetMapping("/hello")
  //public HelloResponse hello(@RequestParam(defaultValue = "SKALA") String name) {
  //  // 서비스 호출하여 응답 객체 생성
  //  log.info("/hello: GET {}", name);
  //  return helloService.createMessage(name);
  //}

  //@PostMapping("/hello")
  //public HelloResponse postHello(@Valid @RequestBody HelloRequest body) {
  //  log.info("/hello: POST {}", body.getName());
  //  return helloService.createMessage(body.getName());
  //}

  @PostMapping("/courses/{name}")
  public CourseResponse getClassInfo(@PathVariable String name, @RequestParam List<String> topics) {
    // Service에서 CourseResponse 생성
    // log.info("/courses: {}", name);
    return courseService.createCourse(name, topics);
  }
}