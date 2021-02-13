package chen.vamdawn.binflare.presentation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Test Api
 *
 * @author chen
 */
@Api(tags = "Test")
@RequestMapping("/api/test")
@RestController
public class TestController {

    @ApiOperation(value = "Connection test")
    @GetMapping("ping")
    public String ping() {
        return "Pong!";
    }
}
