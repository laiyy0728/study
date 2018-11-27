package com.laiyy.batch.restart;

import com.laiyy.batch.itemreadermultifile.City;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * @author laiyy
 * @date 2018/11/26 11:13
 * @description
 */
//@Component
public class RestartReader implements ItemStreamReader<City> {

    /**
     * 文件读取
     */
    private FlatFileItemReader<City> reader = new FlatFileItemReader<>();

    /**
     * 当前读到第几行
     */
    private Long curLine = 0L;

    /**
     * 是否重启
     */
    private boolean restart = false;

    /**
     * 执行的上下文
     */
    private ExecutionContext executionContext;

    public RestartReader() {
        reader.setResource(new ClassPathResource("file1.txt"));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name", "countryCode", "district", "population");
        // 解析后的数据映射为对象
        DefaultLineMapper<City> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);

        mapper.setFieldSetMapper(new FieldSetMapper<City>() {
            @Override
            public City mapFieldSet(FieldSet fieldSet) throws BindException {
                City city = new City();
                city.setCountryCode(fieldSet.readString("countryCode"));
                city.setDistrict(fieldSet.readString("district"));
                city.setId(fieldSet.readInt("id"));
                city.setName(fieldSet.readString("name"));
                city.setPopulation(fieldSet.readLong("population"));
                return city;
            }
        });
        // 数据校验
        mapper.afterPropertiesSet();
        // 绑定映射
        reader.setLineMapper(mapper);
    }

    @Override
    public City read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        City city = null;
        this.curLine++;

        if (restart) {
            reader.setLinesToSkip(this.curLine.intValue() - 1);
            restart = false;
            System.out.println("Start reading from line: " + this.curLine);
        }
        reader.open(this.executionContext);

        city = reader.read();

//        if (city != null && this.curLine == 100) {
//            throw new RuntimeException("Something Wrong!");
//        }
        return city;
    }

    /**
     * 在开始读取之前调用
     */
    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        this.executionContext = executionContext;
        if (executionContext.containsKey("cutLine")) {
            this.curLine = executionContext.getLong("curLine");
            this.restart = true;
        } else {
            this.curLine = 0L;
            executionContext.put("curLine", 0L);
            System.out.println("Start reading from line: " + (this.curLine + 1));
        }
    }

    /**
     * 在每次读取 chunk 条数据后调用
     */
    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        executionContext.put("curLine", this.curLine);
        System.out.println("Reading line: " + (this.curLine + 1));

    }

    @Override
    public void close() throws ItemStreamException {

    }
}
