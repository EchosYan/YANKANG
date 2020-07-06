package me.iyk.project.common.dto.basic;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

/**
 * Created on 2019/12/27.
 *
 * @author Echos
 */
public interface VoConverter<SOURCE, TARGET> {
    @Mappings({})
    @InheritConfiguration
    TARGET to(SOURCE source);

    @InheritConfiguration
    List<TARGET> to(Collection<SOURCE> source);

    @InheritInverseConfiguration
    SOURCE from(TARGET source);

    @InheritInverseConfiguration
    List<SOURCE> from(Collection<TARGET> source);
}
