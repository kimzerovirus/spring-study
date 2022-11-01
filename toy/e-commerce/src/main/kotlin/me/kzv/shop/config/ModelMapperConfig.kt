package me.kzv.shop.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig {

    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.isFieldMatchingEnabled = true // 필드 이름이 같은 것끼리 매칭
        modelMapper.configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE // private 필드도 접근 (엔티티를 private 으로 만든다면 접근할 수 없기 때문)

        return modelMapper
    }
}