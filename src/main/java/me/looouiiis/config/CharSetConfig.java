package me.looouiiis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CharSetConfig {
    @Bean
    public StringHttpMessageConverter charSet(){
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(new MediaType("text","html", UTF_8));
        mediaTypes.add(new MediaType("text","json",UTF_8));
        stringHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        return stringHttpMessageConverter;
    }
}
