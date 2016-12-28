package com.sdxd.framework.id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * 返回的ID的时间戳信息支持到小时级别
 */
@Service()
@Qualifier("idGeneratorWithHour")
public class IDGeneratorWithHour extends IDGeneratorWithTimestamp {
    private Logger log = LoggerFactory.getLogger(IDGeneratorWithHour.class);

    public IDGeneratorWithHour() {
        super("", IDDatePattern.PATTERN_HOURS, 8);
    }

    public IDGeneratorWithHour(String prefix) {
        super(prefix, IDDatePattern.PATTERN_HOURS, 8);
    }

    public IDGeneratorWithHour(String prefix, int bits) {
        super(prefix, IDDatePattern.PATTERN_HOURS, bits);
    }

}
