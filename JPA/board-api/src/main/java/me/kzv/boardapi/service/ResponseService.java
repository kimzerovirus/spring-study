package me.kzv.boardapi.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.kzv.boardapi.dto.CommonResultDto;
import me.kzv.boardapi.dto.ListResultDto;
import me.kzv.boardapi.dto.SingleResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 해당 Class가 Service임을 명시합니다.
public class ResponseService {

    // enum으로 api 요청 결과에 대한 code, message를 정의합니다.
    @Getter
    @AllArgsConstructor
    public enum CommonResponse {
        SUCCESS(0, "성공하였습니디."),
        FAIL(-1, "실패하였습니다.");

        int code;
        String msg;

//        CommonResponse(int code, String msg) {
//            this.code = code;
//            this.msg = msg;
//        }

//        public int getCode() {
//            return code;
//        }
//
//        public String getMsg() {
//            return msg;
//        }
    }

    // 단일건 결과를 처리하는 메소드
    public <T> SingleResultDto<T> getSingleResult(T data) {
        SingleResultDto<T> result = new SingleResultDto<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    // 다중건 결과를 처리하는 메소드
    public <T> ListResultDto<T> getListResult(List<T> list) {
        ListResultDto<T> result = new ListResultDto<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

    // 성공 결과만 처리하는 메소드
    public CommonResultDto getSuccessResult() {
        CommonResultDto result = new CommonResultDto();
        setSuccessResult(result);
        return result;
    }

    // 실패 결과만 처리하는 메소드
    public CommonResultDto getFailResult() {
        CommonResultDto result = new CommonResultDto();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }

    // 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}