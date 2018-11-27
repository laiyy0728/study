package com.laiyy.batch.listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * @author laiyy
 * @date 2018/11/15 17:29
 * @description
 *
 * 注解实现监听
 */
public class MyChunkListener {

    @BeforeChunk
    public void before(ChunkContext chunkContext){
        System.out.println("Step 执行之前，Step 名称：" + chunkContext.getStepContext().getStepName());
    }

    @AfterChunk
    public void after(ChunkContext chunkContext){
        System.out.println("Step 执行之后，Step 名称：" + chunkContext.getStepContext().getStepName());
    }

}
