package rishi.tadavarthi.Ecommerce_Clone.io;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {


    private String categoryId;
    private String name;
    private String description;
    private String bgColor;
    private String createdAt;
    private String updatedAt;
    private String imgUrl;

}
