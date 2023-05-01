package com.wj.train.member.req;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberLoginReq {

    @NotBlank(message = "【手机号】不能为空")
    @Pattern(regexp = "^1\\d{10}$" , message = "手机号格式错误")
    private  String mobile;

    @NotBlank(message = "【验证码】不能为空")
    private String code;

}
