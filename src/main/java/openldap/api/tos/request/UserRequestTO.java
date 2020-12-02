package openldap.api.tos.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UserRequestTO {

    @NotBlank(message = "The user id is mandatory")
    @ApiModelProperty(required = true)
    private String uid;

    @NotBlank(message = "The common name is mandatory")
    @ApiModelProperty(required = true)
    private String cn;

    @NotBlank(message = "The surname is mandatory")
    @ApiModelProperty(required = true)
    private String sn;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
