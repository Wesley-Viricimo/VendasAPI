package io.github.WesleyViricimo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration //Indicando que a classe é uma classe de configuração
public class InternacionalizacaoConfig {

    @Bean //Métodos com a anotação Bean irão subir juntamente da aplicação quando o projeto for compilado
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); //Indicando o caminho e nome do arquivo. OBS: Não é necessário adicionar a extenção do arquivo pois já é por padrão .properties
        messageSource.setDefaultEncoding("ISO-8859-1"); //Indicando que será encodado para iso 8859 1 o que permite palavras com acentuação
        messageSource.setDefaultLocale(Locale.getDefault());
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}