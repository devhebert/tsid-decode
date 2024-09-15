package br.com.tsiddecode.api;

import io.hypersistence.tsid.TSID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@ControllerAdvice
@RestController
public class EncodeTSIDEndpoint extends BaseTSIDEndpoint {

    public record Request(Long tsidLong) { }

    @PostMapping("/encode")
    public ResponseEntity<?> encodeTsid(@RequestBody Request request) {
        try {
            TSID tsid = TSID.from(request.tsidLong());
            String encodedValue = tsid.toString();
            return ResponseEntity.ok(String.valueOf(encodedValue));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid TSID long value: " + request.tsidLong());
        }
    }
}
