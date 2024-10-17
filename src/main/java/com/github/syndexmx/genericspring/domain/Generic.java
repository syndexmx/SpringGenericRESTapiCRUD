package com.github.syndexmx.genericspring.domain;

import com.github.syndexmx.genericspring.annotations.CopyCatClass;
import com.github.syndexmx.genericspring.annotations.CopyCatDataField;
import com.github.syndexmx.genericspring.annotations.CopyCatIdField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@CopyCatClass
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Generic {

    @CopyCatIdField
    private String genericId;

    @CopyCatDataField
    private String genericString;

}
