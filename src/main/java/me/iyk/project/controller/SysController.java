package me.iyk.project.controller;

import me.iyk.project.common.dto.basic.result.R;
import me.iyk.project.common.dto.basic.result.Result;
import me.iyk.project.common.exception.BizException;
import me.iyk.project.common.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020/6/29.
 *
 * @author Echos
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @GetMapping("/isLive")
    public Result<String> isLive() {
        return R.ok("ok");
    }

    @GetMapping("/timestamp")
    public Result<Long> timestamp() {
        return R.ok(System.currentTimeMillis());
    }

    @GetMapping("/exception")
    public Result<String> exception() {
        throw new BizException(ErrorCode.EXCEPTION);
    }
}
