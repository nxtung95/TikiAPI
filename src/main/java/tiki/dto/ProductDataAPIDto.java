package tiki.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDataAPIDto {
    private Long id;
    private String name;

    @SerializedName("brand_name")
    private String brandName;

    @SerializedName("thumbnail_url")
    private String image;

    private String price;

    @SerializedName("list_price")
    private String listPrice;

    @SerializedName("original_price")
    private String originalPrice;

    @SerializedName("short_description")
    private String shortDescription;
}
