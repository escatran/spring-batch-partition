package com.tung;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * @author tung.tranduy
 * @version 09/12/2015
 */
@ContextConfiguration(
        locations = {
                "file:src/test/resources/applicationContext-batch.xml"
        })
public class FilePartitionStepTest extends AbstractTestNGSpringContextTests {

        @Autowired
        @Qualifier("xmlLoader")
        private Job xmlLoader;

        @Autowired
        @Qualifier("syncJobLauncher")
        private JobLauncher syncJobLauncher;

        @Test
        public void test() throws Exception {
                JobParameters jobParams = new JobParametersBuilder()
                        .addDate("date", new Date())
                        .toJobParameters();
                JobExecution jobExecution = syncJobLauncher.run(xmlLoader, jobParams);
        }
}
