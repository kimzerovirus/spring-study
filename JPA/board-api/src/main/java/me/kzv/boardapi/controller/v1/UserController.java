package me.kzv.boardapi.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import me.kzv.boardapi.domain.User;
import me.kzv.boardapi.dto.ListResultDto;
import me.kzv.boardapi.persistence.UserRepository;
import me.kzv.boardapi.service.ResponseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController // 결과값을 JSON으로 출력합니다.
@RequestMapping(value = "/v1")
public class UserController {

    private final ResponseService responseService;
    private final UserRepository userRepository;

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회합니다.")
    @GetMapping(value = "/user")
    public ListResultDto<User> findAllUser() {
        return responseService.getListResult(userRepository.findAll());
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력합니다.")
    @PostMapping(value = "/user")
    public User save(
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name
    ) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return userRepository.save(user);
    }
}