package me.kzv.boardapi.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import me.kzv.boardapi.domain.User;
import me.kzv.boardapi.dto.CommonResultDto;
import me.kzv.boardapi.dto.ListResultDto;
import me.kzv.boardapi.dto.SingleResultDto;
import me.kzv.boardapi.exception.CustomUserNotFoundException;
import me.kzv.boardapi.persistence.UserRepository;
import me.kzv.boardapi.service.ResponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/v1")
public class UserController {

    private final UserRepository userRepository;
    private final ResponseService responseService;

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회합니다.")
    @GetMapping(value = "/user")
    public ListResultDto<User> findAllUser() {
        return responseService.getListResult(userRepository.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다")
    @GetMapping(value = "/user/{userId}")
    public SingleResultDto<User> findUserById(@ApiParam(value = "회원ID", required = true) @PathVariable Long userId) {
        // 결과데이터가 단일건인경우 getSingleResultDto를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userRepository.findById(userId).orElseThrow(CustomUserNotFoundException::new));
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력합니다.")
    @PostMapping(value = "/user")
    public SingleResultDto<User> save(
            @ApiParam(value = "회원아이디", required = true) @RequestParam String id,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name
    ) {
        User user = User.builder()
                .id(id)
                .name(name)
                .build();
        return responseService.getSingleResult(userRepository.save(user));

    }

    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResultDto<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam long idx,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String id,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .idx(idx)
                .id(id)
                .name(name)
                .build();
        return responseService.getSingleResult(userRepository.save(user));
    }
    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{idx}")
    public CommonResultDto delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable long idx) {
        userRepository.deleteById(idx);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}