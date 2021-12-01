package borman.war;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    /*
    TODO -> return results as flux somehow ?
     */

    @Autowired
    WarSimulator warSimulator;

    @GetMapping
    public ResponseEntity<Void> playWar() {
        warSimulator.playWar();
        return ResponseEntity.ok().build();
    }

}