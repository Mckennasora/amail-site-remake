package cn.harusora.amailsite.common.exception;

import cn.harusora.amailsite.common.result.ResultCodeEnum;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AmailException extends RuntimeException {
    private Integer code;

    public AmailException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public AmailException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public AmailException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.code = resultCodeEnum.getCode();
    }
}
