package me.iyk.project.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.iyk.project.common.dto.basic.result.R;
import me.iyk.project.common.dto.basic.result.Result;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Health check
 *
 * @author Echos
 * @date 2020/09/18
 */
@Slf4j
@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class ServerHealthController {
    private final VersionInfo versionInfo;


    /**
     * 服务心跳检测接口
     *
     * @return
     */
    @GetMapping(value = "/ping")
    public Result<String> ping() {
        return R.ok("pong");
    }


    @GetMapping
    public Result healthController() {
        return R.ok(new Date());
    }

    @GetMapping("version")
    public Result<VersionInfo> buildVersion() {
        return R.ok(versionInfo);
    }


    @Data
    @Component
    @ConfigurationProperties(prefix = "project-build-version-info")
    public static class VersionInfo {
        private String version;
        private String buildTimestamp;
        private String scmVersion;
    }
}
