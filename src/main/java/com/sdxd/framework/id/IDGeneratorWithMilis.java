package com.sdxd.framework.id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * 返回的ID的时间戳信息支持到毫秒级别
 */
public class IDGeneratorWithMilis extends IDGeneratorWithTimestamp {
    private Logger log = LoggerFactory.getLogger(IDGeneratorWithMilis.class);

    public IDGeneratorWithMilis() {
        super("", IDDatePattern.PATTERN_MILLIS, 2);
    }

    public IDGeneratorWithMilis(String prefix) {
        super(prefix, IDDatePattern.PATTERN_MILLIS, 2);
    }

    public IDGeneratorWithMilis(String prefix, int bits) {
        super(prefix, IDDatePattern.PATTERN_MILLIS, bits);
    }

}
