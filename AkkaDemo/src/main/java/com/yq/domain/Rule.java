package com.yq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Simple to Introduction
 * className: rule
 *
 * @author EricYang
 * @version 2018/12/21 16:50
 */
@Data
@NoArgsConstructor
public class Rule {
    private String functionContent;
    private String id;
    private String description;
}
