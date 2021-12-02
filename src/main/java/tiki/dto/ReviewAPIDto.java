package tiki.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tiki.entity.Review;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewAPIDto {
    private Long id;
    private String title;
    private String content;
    private int rating;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("created_by")
    private ReviewCreateBy reviewCreateBy;
}
