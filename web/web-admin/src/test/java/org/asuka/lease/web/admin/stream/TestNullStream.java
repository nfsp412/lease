package org.asuka.lease.web.admin.stream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class TestNullStream {
    @Test
    public void test1() {
        List<Integer> l1 = null;
        List<Integer> l2 = l1.stream().map((x) -> x + 1).collect(Collectors.toList());
        System.out.println(l2);
    }
}
