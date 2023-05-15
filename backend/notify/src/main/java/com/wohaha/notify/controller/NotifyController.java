package com.wohaha.notify.controller;

import com.wohaha.notify.domain.Notify;
import com.wohaha.notify.repository.NotifyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/notify")
@Slf4j
//@RequiredArgsConstructor
public class NotifyController {
    @Autowired
    private NotifyRepository notifyRepository;

    // 특정 이벤트에 따른 알림 메세지 데이터 추가
    @CrossOrigin
    @PostMapping("/notify")
    public Mono<Notify> setMsg(@RequestBody Notify notify){

        return notifyRepository.save(notify).log(); //Object를 리턴하면 자동으로 JSON 변환 (MessageConverter)가 해줌
    }


    //[완료] 알림 리스트 중 특정 알림 클릭을 할 때 읽음 처리하기.
    @CrossOrigin
    @PutMapping()
    public Mono<Notify> readStateChange(@RequestBody NotifyDto notifyIdx){
        return notifyRepository.findById(notifyIdx.getNotifyIdx())
                .switchIfEmpty(Mono.error(new Exception("TASK_NOT_FOUND")))
                .map(b -> {
                    b.setReadState(true);
                    return b;
                })
                .flatMap(notifyRepository::save);
    }

    // 알림 버튼 눌렀을 때 알림 리스트들.
    @CrossOrigin
    @GetMapping(value = "/notify/{userIdx}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Notify> findByUser(@PathVariable Long userIdx) {

        return notifyRepository.findByUser(userIdx).subscribeOn(Schedulers.boundedElastic());
    }

    // 특정 이벤트에 따른 알림 메세지 데이터 추가
    @CrossOrigin
    @PostMapping("/notify")
    public Mono<Notify> setMsg(@RequestBody Notify notify){

        return notifyRepository.save(notify).log(); //Object를 리턴하면 자동으로 JSON 변환 (MessageConverter)가 해줌
    }

}
