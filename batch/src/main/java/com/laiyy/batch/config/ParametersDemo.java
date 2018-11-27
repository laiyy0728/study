//package com.laiyy.batch.parameters;
//
//import org.springframework.batch.core.ExitStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.StepExecution;
//import org.springframework.batch.core.StepExecutionListener;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Map;
//
///**
// * @author laiyy
// * @date 2018/11/16 9:22
// * @description
// */
//@Configuration
//public class ParametersDemo implements StepExecutionListener {
//
//    private final JobBuilderFactory jobBuilderFactory;
//
//    private final StepBuilderFactory stepBuilderFactory;
//
//    /**
//     * 存储 Job 的参数
//     */
//    private Map<String, JobParameter> parameterMap;
//
//    @Autowired
//    public ParametersDemo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//    }
//
//    @Bean
//    public Job parameterJob(){
//        return jobBuilderFactory.get("parameterJob")
//                .start(parameterStep())
//                .build();
//    }
//
//    /**
//     * Job 执行的是 Step，所以 Job 的参数是在 Step 中使用
//     * 所以需要给 Step 传递参数即可
//     * 可以使用监听的方式传递数据：即使用 Step 级别的监听在 Step 执行之前传递数据
//     */
//    @Bean
//    public Step parameterStep(){
//        return stepBuilderFactory.get("parameterStep")
//                .listener(this)
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                        System.out.println("【parameterStep】接收到的参数：" + parameterMap.get("info"));
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//
//    @Override
//    public void beforeStep(StepExecution stepExecution) {
//        System.out.println("在 Step 执行之前传入参数");
//        // stepExecution.getJobParameters().getParameters() 是在项目执行时传入的参数，即：项目运行参数
//        parameterMap = stepExecution.getJobParameters().getParameters();
////        parameterMap.put("info")
//    }
//
//    @Override
//    public ExitStatus afterStep(StepExecution stepExecution) {
//        System.out.println("在 Step 执行之后处理结果");
//        return null;
//    }
//}
