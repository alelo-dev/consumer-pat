package br.com.alelo.consumer.consumerpat.config;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
		@ApiImplicitParam(
				name = "page",
				dataTypeClass = Integer.class,
				defaultValue = "0",
				paramType = "query"
		),
		@ApiImplicitParam(
				name = "size",
				dataTypeClass = Integer.class,
				defaultValue = "10",
				paramType = "query"
		),
		@ApiImplicitParam(
				name = "sort",
				allowMultiple = true,
				dataTypeClass = String.class,
				paramType = "query"
		)
})
public @interface ApiPageable {

}
