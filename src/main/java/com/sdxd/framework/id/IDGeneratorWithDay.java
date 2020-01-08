package com.sdxd.framework.id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * 返回的ID的时间戳信息支持到天级别
 */
public class IDGeneratorWithDay extends IDGeneratorWithTimestamp {
    private Logger log = LoggerFactory.getLogger(IDGeneratorWithDay.class);

    public IDGeneratorWithDay() {
        super("", IDDatePattern.PATTERN_DAY, 11);
    }

    public IDGeneratorWithDay(String prefix) {
        super(prefix, IDDatePattern.PATTERN_DAY, 11);
    }

    public IDGeneratorWithDay(String prefix, int bits) {
        super(prefix, IDDatePattern.PATTERN_DAY, bits);
    }

}
