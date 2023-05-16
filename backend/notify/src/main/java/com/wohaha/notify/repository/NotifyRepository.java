package com.wohaha.notify.repository;


import com.wohaha.notify.domain.Notify;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface NotifyRepository extends ReactiveMongoRepository<Notify, String> {

  @Tailable
  @Query("{ $and: [ { receiveUserSeq: ?0 }, { readState: { $eq: false } } ] }")  //알림을 읽지 않았을때만
  Flux<Notify> findByReceiveUserSeq (Long receiveUserSeq);
  

}
