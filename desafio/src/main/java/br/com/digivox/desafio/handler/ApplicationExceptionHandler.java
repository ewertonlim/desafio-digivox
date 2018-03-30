package br.com.digivox.desafio.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.digivox.desafio.exception.ClienteInexistenteException;
import br.com.digivox.desafio.exception.LancamentoInexistenteException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    protected MessageSource messageSource;
    
    @ExceptionHandler({ ClienteInexistenteException.class })
    public ResponseEntity<Object> handleContaInexistenteException(ClienteInexistenteException ex, WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request, "recurso.cliente.inexistente");
    }
    
    @ExceptionHandler({ LancamentoInexistenteException.class })
    public ResponseEntity<Object> handleContaInexistenteException(LancamentoInexistenteException ex, WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request, "recurso.lancamento.inexistente");
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
            WebRequest request) {
        return handleException(ex, HttpStatus.BAD_REQUEST, request, "recurso.operacao-invalida");
    }

    protected ResponseEntity<Object> handleException(Exception ex, HttpStatus httpStatus, WebRequest request,
            String sourceMessage) {
        String mensagem = messageSource.getMessage(sourceMessage, null, LocaleContextHolder.getLocale());
        return handleExceptionInternal(ex, Arrays.asList(mensagem), new HttpHeaders(), httpStatus, request);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> erros = obterListaErros(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    protected List<String> obterListaErros(BindingResult bindingResult) {
        List<String> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            erros.add(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
        }

        return erros;
    }
}
