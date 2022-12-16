package com.javatechie.spring.batch.config;

import com.javatechie.spring.batch.entity.Cliente;
import com.javatechie.spring.batch.repository.ClienteRepository;

import lombok.AllArgsConstructor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private ClienteRepository clienteRepository;

	@Bean
    public FlatFileItemReader<Cliente> reader() {
        FlatFileItemReader<Cliente> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/clientes.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Cliente> lineMapper() {
        DefaultLineMapper<Cliente> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "nombre", "apellido", "email", "genero", "contacto", "pais", "dob");

        BeanWrapperFieldSetMapper<Cliente> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Cliente.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public ClienteProcessor processor() {
        return new ClienteProcessor();
    }

    @Bean
    public RepositoryItemWriter<Cliente> writer() {
        RepositoryItemWriter<Cliente> writer = new RepositoryItemWriter<>();
        writer.setRepository(clienteRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<Cliente, Cliente>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runJob() {
        return jobBuilderFactory.get("importCliente")
                .flow(step1()).end().build();

    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

}
