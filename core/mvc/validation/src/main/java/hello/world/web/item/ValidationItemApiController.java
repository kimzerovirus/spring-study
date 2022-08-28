package hello.world.web.item;

import hello.world.web.item.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    /**
     * @ModelAttribute : 각각의 필드 단위로 세밀하게 적용됨
     *
     * @RequestBody : HttpMessageConverter 단계에서 JSON 데이터를 객체로 변환하지 못하면 이후 단계가 진행 되지 않고 예외가 발생하므로 bindingResult 로 처리해 줘야함
     */

    @PostMapping("/add")
    public Object addItem(@RequestBody ItemSaveForm form, BindingResult bindingResult) {
        log.info("API 컨트롤러 호출");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        return form;
    }
}
