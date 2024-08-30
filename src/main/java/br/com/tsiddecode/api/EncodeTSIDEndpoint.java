package br.com.tsiddecode.api;

import io.hypersistence.tsid.TSID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class EncodeTSIDEndpoint extends BaseTSIDEndpoint {

    public record LongRequest(Long tsidLong) { }

    @PostMapping("/encode")
    public ResponseEntity<?> encodeTsid(@RequestBody LongRequest request) {
        try {
            TSID tsid = TSID.from(request.tsidLong());
            String encodedValue = tsid.toString();
            return ResponseEntity.ok(encodedValue);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid TSID long value: " + request.tsidLong());
        }
    }
}
