package com.wohaha.dodamdodam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendTeacherSmsRequestDto {

    private String phone;
    private Long kindergartenSeq;
    private Long classSeq;

}
