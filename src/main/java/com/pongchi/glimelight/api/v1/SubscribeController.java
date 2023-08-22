package com.pongchi.glimelight.api.v1;

import static com.pongchi.glimelight.api.v1.dto.ResponseDto.createResponseEntity;
import static com.pongchi.glimelight.api.v1.dto.ResponsesDto.createResponsesEntity;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.service.SubscribeService;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscribeController {

    private final SubscribeService subscribeService;
    
    @GetMapping("/api/v1/subscribe")
    public ResponseEntity<?> findAllByToMember(@NotBlank @RequestParam UUID toMemberId, Pageable pageable, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }
        
        return createResponsesEntity(
            ResponseCode.SUCCESS,
            subscribeService.findAllByToMember(toMemberId, pageable).getContent()
        );
    }

    @PostMapping("/api/v1/subscribe")
    public ResponseEntity<?> addSubscribe(@NotBlank @RequestBody UUID from, @NotBlank @RequestBody UUID to, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            subscribeService.addSubscribe(from ,to)
        );
    }

    @DeleteMapping("/api/v1/subscribe")
    public ResponseEntity<?> subSubscribe(@NotBlank @RequestBody UUID from, @NotBlank @RequestBody UUID to, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }

        return createResponseEntity(
            ResponseCode.SUCCESS,
            subscribeService.subSubscribe(from ,to)
        );
    }

    @GetMapping("/api/v1/subscribe/check")
    public ResponseEntity<?> check(@NotBlank @RequestParam UUID fromMemberId, @NotBlank @RequestParam UUID toMemberId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return createResponsesEntity(
                ResponseCode.INVALID_PARAMETER, 
                bindingResult.getAllErrors()
            );
        }
        return createResponseEntity(
            ResponseCode.SUCCESS,
            subscribeService.check(fromMemberId, toMemberId)
        );
    }
}
