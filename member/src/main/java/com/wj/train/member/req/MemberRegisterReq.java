package com.wj.train.member.req;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString
public class MemberRegisterReq {


    @NotBlank(message = "【手机号】不能为空")
    public String mobile;

}
