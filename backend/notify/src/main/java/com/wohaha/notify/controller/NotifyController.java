package com.wohaha.notify.controller;

import com.wohaha.notify.domain.Notify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notify")
@Slf4j
//@RequiredArgsConstructor
public class NotifyController {

    // 특정 이벤트에 따른 알림 메세지 데이터 추가
    @CrossOrigin
    @PostMapping
    public Mono<Notify> saveNotify(@RequestBody Notify notify){


        return notifyRepository.save(notify).log(); //Object를 리턴하면 자동으로 JSON 변환 (MessageConverter)가 해줌
    }

}
