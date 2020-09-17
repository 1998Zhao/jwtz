package com.zhao.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ah
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<V> {
    private int code;

    private String msg;

    private V data;
}
