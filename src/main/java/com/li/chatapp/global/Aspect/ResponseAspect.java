package com.li.chatapp.global.Aspect;

import com.li.chatapp.global.rsData.RsData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ResponseAspect {
    private final HttpServletResponse response;

    @Around("""
            (
                within
                (
                    @org.springframework.web.bind.annotation.RestController *
                )
                &&
                (
                    @annotation(org.springframework.web.bind.annotation.GetMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.PostMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.PutMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.DeleteMapping)
                    ||
                    @annotation(org.springframework.web.bind.annotation.RequestMapping)
                )
            )
            ||
            @annotation(org.springframework.web.bind.annotation.ResponseBody)
            """)

    public Object handleResponse(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("===========================시작===========================");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("===========================종료===========================");
        if (proceed instanceof RsData<?> rsData) {
            response.setStatus(rsData.getStatusCode());
        }

        return proceed;
    }
}
