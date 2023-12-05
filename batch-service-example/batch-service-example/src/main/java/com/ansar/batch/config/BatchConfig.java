package com.ansar.batch.config;

import com.ansar.batch.entity.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

    @Bean
    public Job jobBean(JobRepository jobRepository, JobCompletionNotificationImpl jobCompletionNotification, Step step) {
        return new JobBuilder("Job", jobRepository)
                .listener(jobCompletionNotification)
                .start(step)
                .build();
    }

    @Bean
    public Step steps(JobRepository jobRepository,
                      DataSourceTransactionManager transactionManager,
                      ItemReader<Product> reader,
                      ItemProcessor<Product, Product> processor,
                      ItemWriter<Product> writer) {

        return new StepBuilder("jobStep", jobRepository)
                .<Product, Product>chunk(5, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public FlatFileItemReader<Product> reader() {
        return new FlatFileItemReaderBuilder<Product>()
                .name("ItemReader")
                .resource(new ClassPathResource("product_data.csv"))
                .delimited()
                .names("productId", "title", "description", "price", "discount")
                .targetType(Product.class)
                .build();
    }

    //processor
    @Bean
    public ItemProcessor<Product, Product> itemProcessor() {
        return new CustomItemProcessor();

    }

    //writer
    @Bean
    public ItemWriter<Product> itemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Product>()
                .sql("INSERT INTO products(ID,title,description,price,discount,discounted_price) values(:product_id,:title" +
                        ":description,:price,:discount,:discounted_price)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
}
