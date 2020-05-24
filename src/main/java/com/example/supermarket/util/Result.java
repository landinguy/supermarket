package com.example.supermarket.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    @Builder.Default
    private int code = 0;
    @Builder.Default
    private String msg = "success";
    private Object data;
}
