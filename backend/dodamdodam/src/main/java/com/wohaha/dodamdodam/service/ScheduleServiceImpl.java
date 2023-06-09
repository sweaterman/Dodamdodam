package com.wohaha.dodamdodam.service;

import com.wohaha.dodamdodam.domain.Schedule;
import com.wohaha.dodamdodam.domain.ScheduleType;
import com.wohaha.dodamdodam.dto.request.CreateScheduleRequestDto;
import com.wohaha.dodamdodam.dto.response.ClassScheduleListResponseDto;
import com.wohaha.dodamdodam.dto.response.ClassScheduleResponseDto;
import com.wohaha.dodamdodam.exception.BaseException;
import com.wohaha.dodamdodam.exception.BaseResponseStatus;
import com.wohaha.dodamdodam.repository.KindergartenRepository;
import com.wohaha.dodamdodam.repository.ScheduleRepository;
import com.wohaha.dodamdodam.repository.ScheduleTypeRepository;
import com.wohaha.dodamdodam.security.CustomAuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final KindergartenRepository kindergartenRepository;

    private final ScheduleRepository scheduleRepository;

    private final ScheduleTypeRepository scheduleTypeRepository;

    @Override
    public boolean createClassSchedule(Long classSeq, CreateScheduleRequestDto createScheduleRequestDto) {

        Long kindergartenSeq = kindergartenRepository.findKindergartenSeqByClassSeq(classSeq)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.UNREGISTERED_KINDERGARTEN));
        ScheduleType st = scheduleTypeRepository.findById(createScheduleRequestDto.getScheduleTypeSeq())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.SCHEDULE_TYPE_NULL_FAIL));
        Schedule schedule = Schedule.builder()
                .kindergartenSeq(kindergartenSeq)
                .content(createScheduleRequestDto.getContent())
                .date(createScheduleRequestDto.getDate())
                .scheduleType(st)
                .classSeq(classSeq)
                .build();
        scheduleRepository.save(schedule);
        return true;
    }

    @Override
    public List<ClassScheduleResponseDto> getDayScheduleList(Long classSeq, String year, String month, String day) {

        Long kindergartenSeq = kindergartenRepository.findKindergartenSeqByClassSeq(classSeq)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.UNREGISTERED_KINDERGARTEN));

        return scheduleRepository.findClassScheduleByClassSeq(kindergartenSeq, classSeq, Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
    }

    @Override
    public ClassScheduleListResponseDto getMonthScheduleList(Long classSeq, String year, String month) {
        Long userSeq = ((CustomAuthenticatedUser)SecurityContextHolder.getContext().getAuthentication()).getUserSeq();
        Long kindergartenSeq = kindergartenRepository.findKindergartenSeqByUserSeq(userSeq)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.UNREGISTERED_KINDERGARTEN));
        ClassScheduleListResponseDto classScheduleListResponseDto = new ClassScheduleListResponseDto(year, month);
        // 행사가 있는 dateNumber가져옴
        List<Integer> dateNumber = scheduleRepository.findScheduleDateList(kindergartenSeq, classSeq, year, month);
        classScheduleListResponseDto.setDateNumber(dateNumber);
        // 행사가 있는 날의 행사 List가져옴
        Map<Integer, List<ClassScheduleResponseDto>> schedule = new HashMap<>();
        for(Integer date : dateNumber) {
            List<ClassScheduleResponseDto> scheduleList = scheduleRepository.findClassScheduleByClassSeq(kindergartenSeq,classSeq, Integer.valueOf(year), Integer.valueOf(month), date);
            schedule.put(date, scheduleList);
        }
        classScheduleListResponseDto.setSchedule(schedule);

        return classScheduleListResponseDto;
    }
}
