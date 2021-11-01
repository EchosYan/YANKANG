package me.iyk.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

/**
 * Health check
 *
 * @author Echos
 * @date 2020/09/18
 */
@Slf4j
@Api(tags = "ServerVersionController", value = "系统版本查询")
@RestController
@RequestMapping("/sys")
@RequiredArgsConstructor
public class ServerVersionController {
    private final VersionInfo versionInfo;

    @ApiOperation(value = "version")
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
