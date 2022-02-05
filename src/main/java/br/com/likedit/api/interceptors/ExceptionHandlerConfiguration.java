package br.com.likedit.api.interceptors;

import br.com.likedit.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ControllerAdvice
@Controller
public class ExceptionHandlerConfiguration {

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ResponseEntity exception(final RuntimeException exception) {
        log.info(exception.getMessage(), exception);
        return new ResponseEntity<>(buildBodyResponse((BusinessException) exception),
                getHttpCode(((BusinessException) exception).getCodeEnum()));
    }

    private Map<String, Object> buildBodyResponse(final BusinessException exception) {
        final Map<String, Object> responseMap = new ConcurrentHashMap<>();
        responseMap.put("code", exception.getCode());
        responseMap.put("message", exception.getMessage());
        return responseMap;
    }

    private HttpStatus getHttpCode(final BusinessException.Codes errorCode) {
        switch (errorCode) {
            case USER_NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
