package quartz.clustered;

/**
 * @author cheng_mboy
 */

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.jdbcjobstore.JobStoreTX;
import org.quartz.impl.jdbcjobstore.PostgreSQLDelegate;
import org.quartz.simpl.SimpleThreadPool;
import org.quartz.spi.JobStore;
import org.quartz.spi.ThreadExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * This Example will demonstrate how to start and shutdown the Quartz scheduler and how to schedule a job to run in
 * Quartz.
 *
 * @author Bill Kratzer
 */
public class SimpleExample {

    private void run() throws Exception {
        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job;
        Trigger trigger;
        sched.clear();
        for (int i = 0; i < 20; i++) {
            JobKey jobKey = new JobKey(i + "", "test");
            TriggerKey triggerKey = new TriggerKey(i + "", "test");
            if (!(sched.checkExists(jobKey) && sched.checkExists(triggerKey))) {
                job = newJob(HelloJob.class).withIdentity(jobKey).build();
                trigger = newTrigger().withIdentity(triggerKey).startNow().build();
                sched.scheduleJob(job, trigger);
            }
        }
        sched.start();
    }

    public static void main(String[] args) throws Exception {
        SimpleExample example = new SimpleExample();
        example.run();
    }

}

