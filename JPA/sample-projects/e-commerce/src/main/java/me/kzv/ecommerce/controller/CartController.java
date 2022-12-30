package me.kzv.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.dto.CartDetailDto;
import me.kzv.ecommerce.dto.CartItemDto;
import me.kzv.ecommerce.dto.CartOrderDto;
import me.kzv.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @ResponseBody
    @PostMapping("/cart")
    public ResponseEntity order(@RequestBody @Valid CartItemDto cartItemDto, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                sb.append(fieldError.getDefaultMessage());
            }

            return new ResponseEntity(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long cartItemId;

        try {
            cartItemId = cartService.addCart(cartItemDto, email);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(cartItemId, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public String orderHist(Principal principal, Model model) {
        List<CartDetailDto> cartDetailDtoList = cartService.getCartList(principal.getName());
        model.addAttribute("cartItems", cartDetailDtoList);
        return "cart/cartList";
    }

    @ResponseBody
    @PatchMapping("/cartItem/{cartItemId}")
    public ResponseEntity updateCartItem(@PathVariable Long cartItemId, int count, Principal principal) {
        if (count <= 0) {
            return new ResponseEntity("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
        } else if(!cartService.validateCartItem(cartItemId, principal.getName())){
            return new ResponseEntity("수정 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        cartService.updateCartItemCount(cartItemId, count);
        return new ResponseEntity(cartItemId, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/cartItem/{cartItemId}")
    public ResponseEntity deleteCartItem(@PathVariable Long cartItemId, int count, Principal principal) {
        if(!cartService.validateCartItem(cartItemId, principal.getName())){
            return new ResponseEntity("수정 권한이 없습니다", HttpStatus.FORBIDDEN);
        }

        cartService.deleteCartItem(cartItemId);
        return new ResponseEntity(cartItemId, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/cart/orders")
    public ResponseEntity orderCartItem (@RequestBody CartOrderDto cartOrderDto, Principal principal) {
        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();
        if(cartOrderDtoList == null || cartOrderDtoList.size() == 0){
            return new ResponseEntity("수정 권한이 없습니다", HttpStatus.FORBIDDEN);
        }
        for (CartOrderDto cartOrder : cartOrderDtoList) {
            if (!cartService.validateCartItem(cartOrder.getCartItemId(), principal.getName())) {
                return new ResponseEntity("주문 권한이 없습니다.", HttpStatus.FORBIDDEN)
            }
        }

        Long orderId = cartService.orderCartItem(cartOrderDtoList, principal.getName());
        return new ResponseEntity(orderId, HttpStatus.OK);
    }
}
