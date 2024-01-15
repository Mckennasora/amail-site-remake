package cn.harusora.amailsite.common.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author sora
 */
@Data
@ToString
public class IdsDeleteDto {
    @NotNull
    @Pattern(regexp = "^[0-9，]+$", message = "只能包含字母和数字")
    public String ids;
}
