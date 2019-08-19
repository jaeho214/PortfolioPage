package com.dev.portfolio.controller;

import com.dev.portfolio.model.dto.CertificateDto;
import com.dev.portfolio.service.CertificateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portfolio/main/certificate")
public class CertificateController {
    private CertificateService certificateService;

    public CertificateController(CertificateService certificateService){
        this.certificateService = certificateService;
    }

    // 자격 사항
    @ApiOperation("자격증 조회")
    @GetMapping
    public List<CertificateDto> getCertificates(@RequestHeader(name = "Authorization") String token){
        return certificateService.getCertificates(token);
    }

    @ApiOperation("자격증 추가")
    @PostMapping
    public void saveCertificate(@RequestHeader(name = "Authorization") String token, @RequestBody CertificateDto certificateDto){
        certificateService.saveCertificate(token, certificateDto);
    }

    @ApiOperation("자격증 수정")
    @PutMapping
    public void updateCertificate(@RequestHeader(name = "Authorization") String token, @RequestBody CertificateDto certificateDto){
        certificateService.updateCertificate(token, certificateDto);
    }

    @ApiOperation("자격증 삭제")
    @DeleteMapping("/{certificateNo}")
    public void deleteCertificate(@RequestHeader(name = "Authorization") String token, @PathVariable Long certificateNo){
        certificateService.deleteCertificate(token, certificateNo);
    }

}
