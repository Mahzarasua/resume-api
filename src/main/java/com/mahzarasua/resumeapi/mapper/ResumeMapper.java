package com.mahzarasua.resumeapi.mapper;

import com.mahzarasua.resumeapi.domain.GetResumeResponse;
import com.mahzarasua.resumeapi.domain.ResumeRequest;
import com.mahzarasua.resumeapi.domain.ResumeResponse;
import com.mahzarasua.resumeapi.model.ResumeModel;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ResumeMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory){
        factory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        configureMapper(factory);
    }

    public MapperFactory configureMapper(MapperFactory factory){
        factory.classMap(ResumeModel.class, GetResumeResponse.class)
                .byDefault().mapNulls(false).register();
        factory.classMap(ResumeModel.class, ResumeResponse.class)
                .byDefault().mapNulls(false).register();
        factory.classMap(ResumeRequest.class, ResumeModel.class)
                .byDefault().mapNulls(false).register();
        return factory;
    }
}
