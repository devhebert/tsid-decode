package br.com.tsiddecode.api;

import io.hypersistence.tsid.TSID;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@CrossOrigin(value = "*")
@ControllerAdvice
@RestController
public class DecodeTSIDEndpoint extends BaseTSIDEndpoint {

    public record Request(String tsidString) { }

    @PostMapping("/decode")
    public ResponseEntity<?> decodeTsid(@RequestBody Request request) {
        try {
            TSID tsid = TSID.from(request.tsidString());
            long decodedValue = tsid.toLong();
            return ResponseEntity.ok(String.valueOf(decodedValue));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid TSID string: " + request.tsidString());
        }
    }
}