package org.asuka.lease.web.admin.schedule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ScheduledTasksTest {
    @Autowired
    private ScheduledTasks scheduledTasks;

    @Test
    public void test1() {
        scheduledTasks.checkLeaseStatus();
    }
}