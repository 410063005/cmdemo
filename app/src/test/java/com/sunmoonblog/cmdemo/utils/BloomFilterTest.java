package com.sunmoonblog.cmdemo.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// https://www.baeldung.com/guava-bloom-filter
// https://www.cnblogs.com/liyulong1982/p/6013002.html
public class BloomFilterTest {

    @Test
    public void bloom() {
        BloomFilter<String> filter = BloomFilter.create(
                Funnels.stringFunnel(Charset.forName("UTF-8")),
                500,
                0.01);
        filter.put("程");
        filter.put("明白");
        filter.put("是的");
        filter.put("哈哈");

        assertTrue(filter.mightContain("程"));
        assertTrue(filter.mightContain("明白"));
        assertTrue(filter.mightContain("是的"));
        assertTrue(filter.mightContain("哈哈"));

        assertFalse(filter.mightContain("我"));
    }
}
