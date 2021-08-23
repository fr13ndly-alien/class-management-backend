package com.cm.core;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer customizer()
	{
		return builder -> builder.serializerByType(ObjectId.class,new ToStringSerializer());
	}

//	@Bean
//	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory,
//									   MongoMappingContext context) {
//
//		MappingMongoConverter converter =
//				new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
//		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//
//		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
//
//		return mongoTemplate;
//	}

}
