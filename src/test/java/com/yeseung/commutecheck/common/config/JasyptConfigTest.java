package com.yeseung.commutecheck.common.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class JasyptConfigTest {

    @Autowired
    private StringEncryptor jasyptStringEncryptor;

    @Test
    @DisplayName("")
    void encrypt_test () {
        // given
        String text = "seunggulee";
        // when
        String encrypt = jasyptStringEncryptor.encrypt(text);
        log.error("encrypt :: {}", encrypt);
        // then
        assertThat(text).isEqualTo(jasyptStringEncryptor.decrypt(encrypt));

    }

}