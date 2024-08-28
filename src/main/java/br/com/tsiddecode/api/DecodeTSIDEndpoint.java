package br.com.tsiddecode.api;

import io.hypersistence.tsid.TSID;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
@RestController
public class DecodeTSIDEndpoint extends BaseTSIDEndpoint {

    public record Request(String tsidString) { }

    @PostMapping("/decode")
    public ResponseEntity<?> decodeTsid(@RequestBody Request request) {
        try {
            TSID tsid = TSID.from(request.tsidString());
            return ResponseEntity.ok(tsid.toLong());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid TSID string: " + request.tsidString());
        }
    }
}