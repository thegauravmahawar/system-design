package urlshortenerapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseSuccess extends ApiResponse {
    private Object data;

    public ApiResponseSuccess() {
        this.status = "success";
    }

    public ApiResponseSuccess(Object data) {
        this.status = "success";
        this.data = data;
    }
}
